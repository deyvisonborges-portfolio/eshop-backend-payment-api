//package com.eshop.backendpaymentapi.core.artifacts.payment.usecase.update;
//
////import com.mach.core.artifact.payment.Payment;
//import com.mach.core.artifact.payment.PaymentGateway;
//import com.mach.core.common.exception.DomainException;
//import com.mach.core.common.validation.ValidationHandlerError;
//
//import java.util.Objects;
//
//public class UpdatePaymentUseCase extends UpdatePaymentHandler{
//	private final PaymentGateway gateway;
//
//	public UpdatePaymentUseCase(final PaymentGateway gateway) {
//		this.gateway = Objects.requireNonNull(gateway);
//	}
//
//	@Override
//	public UpdatePaymentOutput execute(final UpdatePaymentCommand command) {
//		final var id = command.id();
//		final var value = command.value();
//		final var status = command.status();
//		final var method = command.method();
//		final var orderId = command.orderId();
//		final var customerId = command.customerId();
//
//		this.gateway.findById(command.id())
//			.orElseThrow(() -> DomainException.with(
//				new ValidationHandlerError("Category with id wast not found".formatted(id.getValue()))
//			));
//
//		// Payment.update().validate()
//		return null;
//	}
//}
