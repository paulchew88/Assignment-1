package com.pc1crt.groceries.controller;

import com.pc1crt.groceries.error.UnavailableException;
import com.pc1crt.groceries.model.*;
import com.pc1crt.groceries.security.UserPrincipal;
import com.pc1crt.groceries.service.CartManager;
import com.pc1crt.groceries.service.CustomerService;
import com.pc1crt.groceries.service.OrdersService;
import com.pc1crt.groceries.service.ProductService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
public class OrdersController {

	@Autowired
	private OrdersService ordersService;
	@Autowired
	private CartManager cartManager;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ProductService productService;

	Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	@GetMapping("/checkout")
	public String checkOut(HttpSession session, @AuthenticationPrincipal UserPrincipal name, Model model) {
		List<Product> outOfStock = new ArrayList<Product>();
		Cart cart = cartManager.getCart(session);
		Orders orders = new Orders();
		Payment payment = new Payment();
		Boolean itemsChanged = false;

		// check cart is not empty
		if (cart.isEmpty()) {
			throw new UnavailableException("Cart is empty, please add some products to checkout");
		}

		// check items are in stock
		Cart newCart = new Cart();
		for (CartItem checkoutCart : cart.getItems()) {
			if (ordersService.findByProductProductId(checkoutCart.getProduct().getProductId())
					.getStockQuantity() == 0) {
				// if not in stock or too many items add to out of stock list
				outOfStock.add(checkoutCart.getProduct());

			}
			// check enough quantity remains to complete order
			else if (ordersService.findByProductProductId(checkoutCart.getProduct().getProductId()).getStockQuantity()
					- checkoutCart.getQuantity() < 0) {
				CartItem item = new CartItem(checkoutCart.getProduct());
				item.setQuantity(ordersService.findByProductProductId(checkoutCart.getProduct().getProductId())
						.getStockQuantity());
				newCart.addItem(item);
				if (!itemsChanged) {
					itemsChanged = true;
				}

			}
			// adds in stock items to list
			else {
				newCart.addItem(checkoutCart);
			}

		}
		// return out of stock items so customer can remove them from basket
		if (!outOfStock.isEmpty() || itemsChanged) {
			cartManager.setCart(session, newCart);
			model.addAttribute("cart", outOfStock);
			model.addAttribute("error",
					"Some items are not in stock or too many items. the following items have been removed please check"
							+ "your basket before checking out");
			return "AmmendCart";
		}

		// add items to order
		List<Product> products = new ArrayList<Product>();

		for (CartItem checkOut : cart.getItems()) {
			for (int i = 0; i < checkOut.getQuantity(); i++) {
				products.add(checkOut.getProduct());

			}
		}
		orders.setProducts(products);

		// all items are in stock saving the order

		try {
			for (CartItem checkoutCart : cart.getItems()) {
				productService.decreaseQuantity(checkoutCart.getProduct().getProductId(), checkoutCart.getQuantity());
			}

			orders.setCustomer(customerService.findByEmail(name.getUsername()));
			orders.setOderDate(LocalDate.now());
			orders.setShippingAddress(orders.getCustomer().getBillingAddress());
			orders.setOrderStatus(OrderStatus.AWAITING_PAYMENT);
			ordersService.save(orders);
			payment.setOrder(orders);
			double total = 0;
			for (Product value : orders.getProducts()) {
				total += value.getPrice();
			}

			payment.setAmount(total);

			session.setAttribute("payment", payment);
			session.setAttribute("order", orders);

		} catch (Exception e) {
			throw new UnavailableException("Somewthing went wrong please try again");
		}
		return "redirect:/payment";

	}

	@RequestMapping("orders/view/{id}")
	public String viewList(@PathVariable Integer id, Model model, @AuthenticationPrincipal UserPrincipal name) {
		if (name.getAuthorities().toString().contains("ADMIN")) {
			model.addAttribute("order", ordersService.findAll());
		} else {
			model.addAttribute("order", ordersService.findByCustomerId(id));
		}
		return "/lists/ListOrders";
	}

	@RequestMapping("/view/order/{id}")
	public String view(@PathVariable Integer id, Model model) {
		model.addAttribute("order", ordersService.findById(id));
		return "order";
	}

	@ExceptionHandler({ UnavailableException.class })
	public String getUnavailable(UnavailableException ex, Model model) {
		model.addAttribute("error", ex.getMessage());
		return "home";

	}

}
