package com.pc1crt.groceries.repository;

import java.util.List;
import java.util.UUID;

import javax.persistence.criteria.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pc1crt.groceries.model.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer>{

	public Orders findByOrdersId(Integer id);

	public List<Orders> findByCustomerCustomerId(Integer id);

}
