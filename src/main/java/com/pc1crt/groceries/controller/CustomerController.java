package com.pc1crt.groceries.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pc1crt.groceries.error.UnavailableException;
import com.pc1crt.groceries.error.FormErrorException;
import com.pc1crt.groceries.model.Address;
import com.pc1crt.groceries.model.Categories;
import com.pc1crt.groceries.model.Customer;
import com.pc1crt.groceries.model.Product;
import com.pc1crt.groceries.repository.CustomerRepository;
import com.pc1crt.groceries.security.UserPrincipal;
import com.pc1crt.groceries.service.CustomerService;

@Controller
public class CustomerController {
	@Autowired
	CustomerService customerService;

	@GetMapping("/register")
	public String newCustomer(Model model) {

		model.addAttribute(new Customer());
		return "/newForms/NewCustomerForm";

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String submitCategory(@ModelAttribute @Valid Customer customer, BindingResult result, ModelMap model)
			throws Exception {
		if (result.hasErrors()) {
			return "newForms/NewCustomerForm";
		}
		if (customerService.findByEmail(customer.getEmail()) == null) {
			if (customer.getPassword().contains(" ")) {

				throw new FormErrorException("password must not contain spaces user not created");
			}
			if (customer.getPassword() == "") {

				throw new FormErrorException("password was left blank and was not created");

			} else {
				 BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				customer.setRoles("USER");
				customer.setActive(true);
				customer.setPassword(encoder.encode(customer.getPassword()));
				customerService.save(customer);

				return "redirect:/";

			}

		}
		throw new FormErrorException("Email is already registered");
	}

	@RequestMapping("/customer")
	public String findAll(Model model) {
		model.addAttribute("customer", customerService.getAllCustomers());
		return "/lists/ListCustomer";
	}

	@GetMapping("/edit/customer/{id}")
	public String edit(@PathVariable Integer id, @AuthenticationPrincipal UserPrincipal user, Model model) {
		if (user.getCustomerId() == id || user.getAuthorities().toString().contains("ADMIN")) {
			Customer customer = customerService.findById(id);

			model.addAttribute(customer);

			return "/updateForms/editCustomer";
		}
		else { throw new FormErrorException("you do not have access to manage this account");}
	}

	@RequestMapping(value = "/edit/customer/{id}", method = RequestMethod.POST)
	public String updateCustomer(@PathVariable Integer id, @ModelAttribute @Valid Customer customer,
			BindingResult result, ModelMap model) throws Exception {
		if (result.hasErrors()) {
			return "updateForms/editCustomer";
		} else {
			 BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			Customer newCustomer = customerService.findById(id);
			newCustomer.setEmail(customer.getEmail());
			newCustomer.setBillingAddress(customer.getBillingAddress());
			newCustomer.setPassword(encoder.encode(customer.getPassword()));
			newCustomer.setPhoneNumber(customer.getPhoneNumber());

			customerService.save(newCustomer);

			return "home";
		}

	}

	@RequestMapping("/remove/customer/{id}")
	public String delete(@PathVariable Integer id) {
		try {
		customerService.delete(customerService.findById(id).getCustomerId());
		}catch (Exception e) {
			throw new FormErrorException("Could not delete " + customerService.findById(id).getEmail());
		}
		return "redirect:/list/customer";
	}

	@ExceptionHandler({ FormErrorException.class })
	public String getFormError(FormErrorException ex, Model model) {
		model.addAttribute("error", ex.getMessage());
		return "home";
	}

}
