package com.pc1crt.groceries.repository;


import org.springframework.data.jpa.repository.JpaRepository;


import com.pc1crt.groceries.model.*;

public interface UserRepository extends JpaRepository<Customer,Integer>{

	Customer findByEmail(String email);

	Customer findByCustomerIdEquals(Integer id);



}
