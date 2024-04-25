package com.eshop.backendpaymentapi.app.persistence.artifacts.payment;

import com.eshop.backendpaymentapi.core.artifacts.payment.Payment;
import com.eshop.backendpaymentapi.core.artifacts.payment.constant.PaymentMethod;
import com.eshop.backendpaymentapi.core.artifacts.payment.constant.PaymentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "payments")
public class PaymentJPAEntity {
  @Id
  @Column(nullable = false)
  private String id;

  private Boolean active;

  @Column(name = "created_at")
  private Instant createdAt;

  @Column(name =  "updated_at")
  private Instant updatedAt;

  @Column(name = "price", nullable = false)
  private double value;

  @Column(nullable = false)
  private String status;

  @Column(nullable = false)
  private String method;

  @Column(nullable = false, name = "order_id")
  private String orderId;

  @Column(nullable = false, name = "customer_id")
  private String customerId;

  public PaymentJPAEntity() {
  }

  public PaymentJPAEntity(
    final String id,
    final Boolean active,
    final Instant createdAt,
    final Instant updatedAt,
    final double value,
    final String status,
    final String method,
    final String orderId,
    final String customerId
  ) {
    this.id = id;
    this.active = active;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.value = value;
    this.status = status;
    this.method = method;
    this.orderId = orderId;
    this.customerId = customerId;
  }

  public static PaymentJPAEntity from(final Payment payment) {
    return new PaymentJPAEntity(
      payment.getId().getValue(),
      payment.getActive(),
      payment.getCreatedAt(),
      payment.getUpdatedAt(),
      payment.getValue(),
      payment.getStatus().getValue().toString(),
      payment.getMethod().getValue().toString(),
      payment.getOrderId(),
      payment.getCustomerId()
    );
  }

  public static Payment toAggregate(final PaymentJPAEntity entity) {
    return Payment.factory(
      entity.getId(),
      entity.getActive(),
      entity.getCreatedAt(),
      entity.getUpdatedAt(),
      entity.getValue(),
      PaymentStatus.fromString(entity.getStatus()),
      PaymentMethod.fromString(entity.getMethod()),
      entity.getOrderId(),
      entity.getCustomerId()
    );
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Instant updatedAt) {
    this.updatedAt = updatedAt;
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }
}
