package com.eshop.backendpaymentapi.lib.exception;

public class UnauthorizedException extends NoStackTracingException{
	public UnauthorizedException(String message) {
		super(message);
	}
}
