package com.eshop.backendpaymentapi.core.artifacts.payment.usecase.create;

import com.eshop.backendpaymentapi.core.artifacts.payment.constant.PaymentMethod;
import com.eshop.backendpaymentapi.core.artifacts.payment.constant.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record CreatePaymentCommand(
  double value,
  PaymentStatus status,
  PaymentMethod method,
  @JsonProperty("paid_in")
  Instant paidIn,
  @JsonProperty("order_id")
  String orderId,
  @JsonProperty("customer_id")
  String customerId
) {

	public static CreatePaymentCommand with(
    double value,
    final PaymentStatus status,
    final PaymentMethod method,
    final Instant paidIn,
    final String orderId,
    final String customerId
	) {
		return new CreatePaymentCommand(
      value,
      status,
      method,
      paidIn,
      orderId,
      customerId
    );
	}
}
