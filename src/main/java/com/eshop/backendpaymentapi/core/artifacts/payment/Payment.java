package com.eshop.backendpaymentapi.core.artifacts.payment;

import com.eshop.backendpaymentapi.core.artifacts.payment.constant.PaymentMethod;
import com.eshop.backendpaymentapi.core.artifacts.payment.constant.PaymentStatus;
import com.eshop.backendpaymentapi.lib.domain.AggregateRoot;

import java.time.Instant;

public class Payment extends AggregateRoot<PaymentID> {
  private final double value;
  private final PaymentStatus status;
  private final PaymentMethod method;
  private final Instant paidIn;
  private final String orderId;
  private final String customerId;

  public Payment(
    final PaymentID paymentID,
    final double value,
    final PaymentStatus status,
    final PaymentMethod method,
    final Instant paidIn,
    final String orderId,
    final String customerId
  ) {
    super(paymentID, true, Instant.now(), Instant.now());
    this.value = value;
    this.status = status;
    this.method = method;
    this.paidIn = paidIn;
    this.orderId = orderId;
    this.customerId = customerId;
  }

  public static Payment factory(
    final double value,
    final PaymentStatus status,
    final PaymentMethod method,
    final Instant paidIn,
    final String orderId,
    final String customerId
  ) {
    return new Payment(
      PaymentID.unique(),
      value,
      status,
      method,
      paidIn,
      orderId,
      customerId
    );
  }

  public static Payment emptyFactory() {
    return new Payment(
      PaymentID.unique(),
      0.0,
      null,
      null,
      null,
      null,
      null
    );
  }

  public PaymentID getId() {
    return id;
  }

  public double getValue() {
    return value;
  }

  public PaymentStatus getStatus() {
    return status;
  }

  public PaymentMethod getMethod() {
    return method;
  }

  public Instant getPaidIn() {
    return paidIn;
  }

  public String getOrderId() {
    return orderId;
  }

  public String getCustomerId() {
    return customerId;
  }
}