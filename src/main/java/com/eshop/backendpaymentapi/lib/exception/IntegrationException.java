package com.eshop.backendpaymentapi.lib.exception;

public class IntegrationException extends NoStackTracingException{
	public IntegrationException(String message) {
		super(message);
	}
}
