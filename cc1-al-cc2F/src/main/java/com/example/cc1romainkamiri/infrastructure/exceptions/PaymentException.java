package com.example.cc1romainkamiri.infrastructure.exceptions;

public class PaymentException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private PaymentException(String message) {
        super(message);
    }

    public static PaymentException error() {
        return new PaymentException(String.format("The payment was unable to be done"));
    }
}
