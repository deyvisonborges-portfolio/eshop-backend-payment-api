package com.eshop.backendpaymentapi.core.artifacts.payment;

import com.eshop.backendpaymentapi.core.artifacts.payment.constant.PaymentMethod;
import com.eshop.backendpaymentapi.core.artifacts.payment.constant.PaymentStatus;
import com.eshop.backendpaymentapi.lib.domain.AggregateRoot;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Payment extends AggregateRoot<PaymentID> {
  private final double value;
  private final PaymentStatus status;
  private final PaymentMethod method;
  private final String orderId;
  private final String customerId;

  public Payment(
    final double value,
    final PaymentStatus status,
    final PaymentMethod method,
    final String orderId,
    final String customerId
  ) {
    super(PaymentID.unique(), true, Instant.now(), Instant.now());
    this.value = Objects.requireNonNull(value);
    this.status = Objects.requireNonNull(status);
    this.method = Objects.requireNonNull(method);
    this.orderId = Objects.requireNonNull(orderId);
    this.customerId = Objects.requireNonNull(customerId);
  }

  public static Payment factory(
    final double value,
    final PaymentStatus status,
    final PaymentMethod method,
    final String orderId,
    final String customerId
  ) {
    return new Payment(
      value,
      status,
      method,
      orderId,
      customerId
    );
  }

  public static Payment factory(
    final String id,
    final boolean active,
    final Instant createdAt,
    final Instant updatedAt,
    final double value,
    final PaymentStatus status,
    final PaymentMethod method,
    final String orderId,
    final String customerId
  ) {
    final var payment = new Payment(
      value,
      status,
      method,
      orderId,
      customerId
    );
    payment.setId(PaymentID.from(UUID.fromString(id)));
    payment.setActive(active);
    payment.setCreatedAt(createdAt);
    payment.setUpdatedAt(updatedAt);
    return payment;
  }

  public static Payment emptyFactory() {
    return new Payment(
      0.0,
      PaymentStatus.OPEN,
      PaymentMethod.DEBIT_CARD,
      UUID.randomUUID().toString(),
      UUID.randomUUID().toString()
    );
  }

  public Payment clone() {
    return new Payment(
      this.value,
      this.status,
      this.method,
      this.orderId,
      this.customerId
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

  public String getOrderId() {
    return orderId;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setId(PaymentID id) {
    super.id = id;
  }

  public void setActive(boolean active) {
    super.active = active;
  }

  public void setCreatedAt(final Instant createdAt) {
    super.createdAt = createdAt;
  }

  public void setUpdatedAt(final Instant updatedAt) {
    super.updatedAt = updatedAt;
  }
}
