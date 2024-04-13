package com.eshop.backendpaymentapi.core.artifacts.payment.usecase.retrieve;

import com.eshop.backendpaymentapi.core.artifacts.payment.Payment;
import com.eshop.backendpaymentapi.core.artifacts.payment.constant.PaymentMethod;
import com.eshop.backendpaymentapi.core.artifacts.payment.constant.PaymentStatus;

import java.time.Instant;

public record GetPaymentByIdOutput(
  String id,
  boolean active,
  Instant createdAt,
  Instant updatedAt,
  double value,
  PaymentStatus status,
  PaymentMethod method,
  Instant paidIn,
  String orderId,
  String customerId
) {
  public static GetPaymentByIdOutput from(final Payment payment) {
    return new GetPaymentByIdOutput(
      payment.getId().getValue(),
      payment.getActive(),
      payment.getCreatedAt(),
      payment.getUpdatedAt(),
      payment.getValue(),
      payment.getStatus(),
      payment.getMethod(),
      payment.getPaidIn(),
      payment.getOrderId(),
      payment.getCustomerId()
    );
  }
}
