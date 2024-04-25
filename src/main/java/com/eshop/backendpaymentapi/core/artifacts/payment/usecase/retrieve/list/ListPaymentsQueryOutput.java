package com.eshop.backendpaymentapi.core.artifacts.payment.usecase.retrieve.list;

import com.eshop.backendpaymentapi.core.artifacts.payment.Payment;
import com.eshop.backendpaymentapi.core.artifacts.payment.PaymentID;
import com.eshop.backendpaymentapi.core.artifacts.payment.constant.PaymentMethod;
import com.eshop.backendpaymentapi.core.artifacts.payment.constant.PaymentStatus;

import java.time.Instant;

public record ListPaymentsQueryOutput(
  PaymentID id,
  boolean active,
  Instant createdAt,
//  Instant updatedAt, Ignore temporally
  double value,
  PaymentStatus status,
  PaymentMethod method,
  String orderId,
  String customerId
) {
  public static ListPaymentsQueryOutput from(final Payment payment) {
    return new ListPaymentsQueryOutput(
      payment.getId(),
      payment.getActive(),
      payment.getCreatedAt(),
      payment.getValue(),
      payment.getStatus(),
      payment.getMethod(),
      payment.getOrderId(),
      payment.getCustomerId()
    );
  }
}
