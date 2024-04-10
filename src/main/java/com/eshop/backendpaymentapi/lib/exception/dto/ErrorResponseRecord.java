package com.eshop.backendpaymentapi.lib.exception.dto;

import org.springframework.http.HttpStatus;

public record ErrorResponseRecord(
	String message,
	HttpStatus httpStatus,
	Integer statusCode
) {}
