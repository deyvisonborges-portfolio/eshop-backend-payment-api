package com.eshop.backendpaymentapi.core.artifacts.payment.usecase.commands.create;

import com.eshop.backendpaymentapi.core.artifacts.payment.constant.PaymentMethod;
import com.eshop.backendpaymentapi.core.artifacts.payment.constant.PaymentStatus;

public record CreatePaymentCommand(
  double value,
  PaymentStatus status,
  PaymentMethod method,
  String orderId,
  String customerId
) {

	public static CreatePaymentCommand with(
    double value,
    final PaymentStatus status,
    final PaymentMethod method,
    final String orderId,
    final String customerId
	) {
		return new CreatePaymentCommand(
      value,
      status,
      method,
      orderId,
      customerId
    );
	}
}
