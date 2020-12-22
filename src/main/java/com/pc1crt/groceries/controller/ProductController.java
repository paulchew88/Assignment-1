package com.pc1crt.groceries.controller;

import com.pc1crt.groceries.model.Categories;
import com.pc1crt.groceries.model.Product;
import com.pc1crt.groceries.service.CartManager;
import com.pc1crt.groceries.service.ProductService;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	@Autowired
	private CartManager cartManager;

	@GetMapping("/add/product")
	public String newProduct(Model model) {

		model.addAttribute(new Product());
		model.addAttribute("categories", productService.addCategories());
		return "/newForms/NewProductForm";

	}

	@RequestMapping(value = "/new/product", method = RequestMethod.POST)
	public String submitCategory(@ModelAttribute @Valid Product product, BindingResult result,
			@ModelAttribute Categories categories, ModelMap model) throws Exception {
		if (result.hasErrors()) {
			return "newForms/NewProductForm";

		} else {
			List<Categories> cats = productService.addCategories();
			for (Categories cat : cats) {
				if (cat.equals(categories)) {
					product.setCategory(cat);
				}
			}

			productService.save(product);
		}
		return "redirect:/list/product";

	}

	@RequestMapping("/list/product")
	public String findAll(Model model) {
		model.addAttribute("product", productService.getAllProducts());
		return "/lists/ListProduct";
	}

	@RequestMapping("/remove/product/{id}")
	public String delete(@PathVariable Integer id) {
		productService.delete(productService.findById(id).getProductId());
		return "redirect:/list/product";
	}

	@RequestMapping("edit/product/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		Product product = productService.findById(id);
		model.addAttribute(product);
		model.addAttribute("categories", productService.addCategories());

		return "/updateForms/editProduct";
	}

	@PostMapping("edit/product/{id}")
	public String update(@PathVariable Integer id, @ModelAttribute @Valid Product product, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "/updateForms/editProduct";
		} else {

			Product newProduct = productService.findById(id);
			newProduct.setName(product.getName());
			newProduct.setSku(product.getSku());
			newProduct.setPrice(product.getPrice());
			newProduct.setWeight(product.getWeight());
			newProduct.setDescription(product.getDescription());
			newProduct.setDateCreated(product.getDateCreated());
			newProduct.setStockQuantity(product.getStockQuantity());
			List<Categories> cats = productService.addCategories();
			for (Categories cat : cats) {

				if (cat.equals(product.getCategory())) {

					newProduct.setCategory(cat);
				}
			}

			productService.save(newProduct);
			return "redirect:/list/product";
		}
	}

	@RequestMapping("/list/productByCategory/{id}")
	public String viewProductsByCategory(@PathVariable Integer id, Model model) {

		model.addAttribute("product", productService.findByProduct(id));

		return "/lists/ListProduct";
	}

	@RequestMapping("/product/view/{id}")
	public String viewProduct(@PathVariable Integer id, Model model) {
		model.addAttribute(productService.findById(id));
		return "/product";
	}

}
