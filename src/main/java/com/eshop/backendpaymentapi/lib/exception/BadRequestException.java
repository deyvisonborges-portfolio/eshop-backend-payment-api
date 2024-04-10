package com.eshop.backendpaymentapi.lib.exception;

public class BadRequestException extends NoStackTracingException{
	public BadRequestException(String message) {
		super(message);
	}
}
