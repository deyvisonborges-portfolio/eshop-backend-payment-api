package com.eshop.backendpaymentapi.lib.exception;

public class InternalErrorException extends NoStackTracingException{
	public InternalErrorException(String message) {
		super(message);
	}
}
