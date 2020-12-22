package com.pc1crt.groceries.error;

public class UnavailableException extends RuntimeException {
	public UnavailableException(String message) {
		super(message);
	}
}
