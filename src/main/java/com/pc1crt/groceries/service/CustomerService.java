package com.pc1crt.groceries.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pc1crt.groceries.model.Customer;
import com.pc1crt.groceries.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerRepository;

	public List<Customer> getAllCustomers() {

		return customerRepository.findAll();
	}

	public void save(Customer customer) {
		this.customerRepository.save(customer);
	}

	public Customer findByEmail(String email) {

		return customerRepository.findByEmail(email);
	}

	public Customer findById(Integer id) {
		
		return this.customerRepository.findByCustomerId(id);
	}

	public void delete(Integer customerId) {
	
		this.customerRepository.deleteById(customerId);
	}

}
