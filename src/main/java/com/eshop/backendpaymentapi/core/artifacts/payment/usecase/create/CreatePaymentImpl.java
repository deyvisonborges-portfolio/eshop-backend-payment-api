package com.eshop.backendpaymentapi.core.artifacts.payment.usecase.create;

import com.mach.core.artifact.payment.Payment;
import com.mach.core.artifact.payment.PaymentGateway;
import com.mach.core.common.validation.handler.Notification;

import java.util.Objects;

public class CreatePaymentImpl extends CreatePaymentHandler {
	private final PaymentGateway gateway;

	public CreatePaymentImpl(PaymentGateway gateway) {
		this.gateway = Objects.requireNonNull(gateway);
	}

	@Override
	public CreatePaymentOutput execute(final CreatePaymentCommand command) {
		final var value = command.value();
		final var status = command.status();
		final var method = command.method();
		final var orderId = command.orderId();
		final var customerId = command.customerId();

		final var notification = Notification.create();

		final var payment = Payment.newPayment(value, status, method, orderId, customerId, true);
		payment.validate(notification);

		if (notification.hasErrors())
			throw new RuntimeException("Errors");
		this.gateway.create(payment);
		return CreatePaymentOutput.from(payment);
	}
}
