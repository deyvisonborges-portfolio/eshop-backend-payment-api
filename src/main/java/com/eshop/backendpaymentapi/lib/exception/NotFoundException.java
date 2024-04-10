package com.eshop.backendpaymentapi.lib.exception;

public class NotFoundException extends NoStackTracingException{
	public NotFoundException(String message) {
		super(message);
	}
}
