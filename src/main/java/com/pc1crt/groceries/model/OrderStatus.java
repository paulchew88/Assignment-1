package com.pc1crt.groceries.model;

public enum OrderStatus {
	AWAITING_PAYMENT("Awaiting Payment"),
	PAYMENT_PROCESSED("Payment processed"),
	IN_TRANSIT("In transit"),
	COMPLETE("Order Completed");
	
	
	private final String string;
	private OrderStatus(String string) {
		this.string = string;
	}

}
