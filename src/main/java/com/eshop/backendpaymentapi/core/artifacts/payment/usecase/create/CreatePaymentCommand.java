package com.eshop.backendpaymentapi.core.artifacts.payment.usecase.create;


public record CreatePaymentCommand(
	double value,
	PaymentStatus status,
	PaymentMethod method,
	String orderId,
	String customerId,
	boolean active
) {

	public static CreatePaymentCommand with(
		final double value,
		final PaymentStatus status,
		final PaymentMethod method,
		final String orderId,
		final String customerId,
		final boolean active
	) {
		return new CreatePaymentCommand(value, status, method, orderId, customerId, active);
	}
}
