package com.eshop.backendpaymentapi.core.artifact.payment;

import com.eshop.backendpaymentapi.core.artifacts.payment.Payment;
import com.eshop.backendpaymentapi.core.artifacts.payment.constant.PaymentMethod;
import com.eshop.backendpaymentapi.core.artifacts.payment.constant.PaymentStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PaymentTest {
  @Test
  void validateEntityInitialization() {
    final var payment = Payment.emptyFactory();
    Assertions.assertNotNull(payment);
  }

  @Test
  void validatePaymentCreationWithValidParameters() {
    final double value = 100.00;
    final PaymentStatus status = PaymentStatus.PENDING;
    final PaymentMethod method = PaymentMethod.CREDIT_CARD;
    final String orderId = "123456";
    final String customerId = "789";

    final var payment = new Payment(value, status, method, orderId, customerId);

    Assertions.assertNotNull(payment);
    Assertions.assertEquals(value, payment.getValue());
    Assertions.assertEquals(status, payment.getStatus());
    Assertions.assertEquals(method, payment.getMethod());
    Assertions.assertEquals(orderId, payment.getOrderId());
    Assertions.assertEquals(customerId, payment.getCustomerId());
  }

  @Test
  void validateIsEmptyFactory() {
    final var payment = Payment.emptyFactory();
    Assertions.assertEquals(0.0, payment.getValue());
		Assertions.assertNotNull(payment.getMethod());
		Assertions.assertNotNull(payment.getStatus());
		Assertions.assertNotNull(payment.getOrderId());
  }

  @Test
  void validateUniqueIdGeneratedByFactory() {
    final var payment1 = Payment.factory(100.00, PaymentStatus.PENDING, PaymentMethod.CREDIT_CARD, "123", "456");
    final var payment2 = Payment.factory(200.00, PaymentStatus.PENDING, PaymentMethod.CREDIT_CARD, "789", "101");

    Assertions.assertNotEquals(payment1.getId(), payment2.getId());
  }
}
