package com.eshop.backendpaymentapi.lib.exception.handler;

import com.eshop.backendpaymentapi.lib.exception.BadRequestException;
import com.eshop.backendpaymentapi.lib.exception.NotFoundException;
import com.eshop.backendpaymentapi.lib.exception.UnauthorizedException;
import com.eshop.backendpaymentapi.lib.exception.dto.ErrorResponseRecord;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorResponseRecord> notFoundException(NotFoundException n) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
			new ErrorResponseRecord(n.getMessage(), HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value())
		);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorResponseRecord> badRequestException(BadRequestException n) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
				new ErrorResponseRecord(n.getMessage(), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value())
		);
	}

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<ErrorResponseRecord> unauthorizedException(UnauthorizedException n) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
				new ErrorResponseRecord(n.getMessage(), HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value())
		);
	}

	@ExceptionHandler(InstantiationException.class)
	public ResponseEntity<ErrorResponseRecord> integrationException(InstantiationException n) {
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(
				new ErrorResponseRecord(n.getMessage(), HttpStatus.BAD_GATEWAY, HttpStatus.BAD_GATEWAY.value())
		);
	}
}
