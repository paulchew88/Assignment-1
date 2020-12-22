package com.pc1crt.groceries.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pc1crt.groceries.model.Cart;
import com.pc1crt.groceries.model.Customer;
import com.pc1crt.groceries.model.Orders;
import com.pc1crt.groceries.model.Product;
import com.pc1crt.groceries.repository.OrderRepository;
import com.pc1crt.groceries.repository.ProductRepository;
@Service
public class OrdersService {
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ProductRepository productRepository;
	 private static final String SESSION_KEY_ORDER = "order";

	public Orders findById(Integer id) {
		return this.orderRepository.findByOrdersId(id);
	}

	public void save(Orders orders) {
		this.orderRepository.save(orders);
	}

	public List<Orders> findAll() {
		return this.orderRepository.findAll();
	}

	public List<Orders> findByCustomerId(Integer id) {

		return this.orderRepository.findByCustomerCustomerId(id);
	}
	public Product findByProductProductId(Integer id){
		return this.productRepository.findByProductId(id);
	}
	public Orders getOrders(HttpSession session){
        Orders order = (Orders) session.getAttribute(SESSION_KEY_ORDER);

        if(order == null){
        	order = new Orders();
            setOrders(session, order);
        }

        return order;
    }
	public void setOrders(HttpSession session, Orders order){
        session.setAttribute(SESSION_KEY_ORDER, order);
    }

	public void delete(Orders order) {
		this.orderRepository.delete(order);
	}
}
