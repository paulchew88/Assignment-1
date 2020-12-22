package com.pc1crt.groceries.repository;

import java.util.UUID;
import com.pc1crt.groceries.model.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	public Customer findByCustomerId(Integer id);

	public Customer findByEmail(String email);
}
