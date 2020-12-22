package com.pc1crt.groceries.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pc1crt.groceries.model.Payment;

public interface PaymentRepository  extends JpaRepository<Payment, UUID>{

}
