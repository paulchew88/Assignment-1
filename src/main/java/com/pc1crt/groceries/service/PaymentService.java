package com.pc1crt.groceries.service;

import com.pc1crt.groceries.model.Orders;
import com.pc1crt.groceries.model.Payment;
import com.pc1crt.groceries.repository.PaymentRepository;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {
	@Autowired
	private PaymentRepository paymentRepository;
	 private static final String SESSION_KEY_PAYMENT = "order";

	public Payment getOrders(HttpSession session) {
		Payment payment = (Payment) session.getAttribute(SESSION_KEY_PAYMENT);

		if (payment == null) {
			payment = new Payment();
			setOrders(session, payment);
		}

		return payment;
	}

	public void setOrders(HttpSession session, Payment payment) {
		session.setAttribute(SESSION_KEY_PAYMENT, payment);
	}
	public void save(Payment payment) {
		this.paymentRepository.save(payment);
	}
}
