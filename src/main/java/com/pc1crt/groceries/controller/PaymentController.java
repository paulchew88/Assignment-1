package com.pc1crt.groceries.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pc1crt.groceries.error.UnavailableException;
import com.pc1crt.groceries.model.Cart;
import com.pc1crt.groceries.model.OrderStatus;
import com.pc1crt.groceries.model.Orders;
import com.pc1crt.groceries.model.Payment;
import com.pc1crt.groceries.service.CartManager;
import com.pc1crt.groceries.service.OrdersService;
import com.pc1crt.groceries.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class PaymentController {
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private CartManager cartManager;

	@RequestMapping("/payment")
	public String payment(HttpSession session, Model model) {
		try {

			model.addAttribute(session.getAttribute("order"));
			model.addAttribute(session.getAttribute("payment"));

			return "payment";
		} catch (Exception e) {
			Orders order = new Orders();
			order = ordersService.getOrders(session);
			ordersService.delete(order);
			throw new UnavailableException("Something went wrong, please try again");
		}

	}

	@RequestMapping("/payment/submit")
	public String submit(HttpSession session, @ModelAttribute("payment") Payment payment) {

		Orders order = new Orders();
		Payment newPayment = new Payment();
		try {
			order = ordersService.getOrders(session);
		} catch (Exception e) {
			ordersService.delete(order);
			throw new UnavailableException("No Order Found");
		}

		try {
			if (payment == null) {
				ordersService.delete(order);
				throw new UnavailableException("payment was null");

			} else {

				try {
					/*
					 * here is where payment verification would be used to ensure card details are
					 * correct assuming card details are correct for testing purposes.
					 */
					paymentService.save(payment);
					order.setOrderStatus(OrderStatus.PAYMENT_PROCESSED);
					ordersService.save(order);
					cartManager.setCart(session, new Cart());
				} catch (Exception e) {
					ordersService.delete(order);
					throw new UnavailableException("could not save payment");
				}

			}
		} catch (Exception e) {
			throw new UnavailableException("Something went wrong, please try again");
		}
		return "home";
	}

	@ExceptionHandler({ UnavailableException.class })
	public String getUnavailable(UnavailableException ex, Model model) {
		model.addAttribute("error", ex.getMessage());
		return "home";

	}

}
