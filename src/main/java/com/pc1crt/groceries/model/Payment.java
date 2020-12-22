package com.pc1crt.groceries.model;

import javax.persistence.GeneratedValue;

import java.time.LocalDate;
import java.util.UUID;

import javax.annotation.Generated;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
public class Payment {
	@Id
	@GeneratedValue
	private UUID transactionId;

	private double amount;
	@Embedded
	@OneToOne
	private Orders order;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate paymentDate;
	@Embedded
	private CardDetails cardDetails;

	public Payment() {

	}


	/**
	 * @param amount
	 * @param order
	 * @param paymentDate
	 * @param paymentType
	 */
	public Payment(double amount, Orders order,
			@NotBlank(message = "the date must not be left blank") LocalDate paymentDate, CardDetails cardDetails) {
		this.amount = amount;
		this.order = order;
		this.paymentDate = paymentDate;
		this.cardDetails = cardDetails;
	}


	public UUID getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(UUID transactionId) {
		this.transactionId = transactionId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}


	public CardDetails getCardDetails() {
		return cardDetails;
	}


	public void setCardDetails(CardDetails cardDetails) {
		this.cardDetails = cardDetails;
	}
	


}
