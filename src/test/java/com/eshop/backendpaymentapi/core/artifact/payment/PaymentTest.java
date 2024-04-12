package com.eshop.backendpaymentapi.core.artifact.payment;

import com.eshop.backendpaymentapi.core.artifacts.payment.Payment;
import com.eshop.backendpaymentapi.core.artifacts.payment.PaymentID;
import com.eshop.backendpaymentapi.core.artifacts.payment.constant.PaymentMethod;
import com.eshop.backendpaymentapi.core.artifacts.payment.constant.PaymentStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;

public class PaymentTest {
  @Test
  void validateEntityInitialization() {
    final var payment = Payment.emptyFactory();
    Assertions.assertNotNull(payment);
  }

  @Test
  void validatePaymentCreationWithValidParameters() {
    final PaymentID paymentId = PaymentID.unique();
    final double value = 100.00;
    final PaymentStatus status = PaymentStatus.PENDING;
    final PaymentMethod method = PaymentMethod.CREDIT_CARD;
    final Instant paidIn = Instant.now();
    final String orderId = "123456";
    final String customerId = "789";

    final var payment = new Payment(value, status, method, paidIn, orderId, customerId);

    Assertions.assertNotNull(payment);
    Assertions.assertEquals(value, payment.getValue());
    Assertions.assertEquals(status, payment.getStatus());
    Assertions.assertEquals(method, payment.getMethod());
    Assertions.assertEquals(paidIn, payment.getPaidIn());
    Assertions.assertEquals(orderId, payment.getOrderId());
    Assertions.assertEquals(customerId, payment.getCustomerId());
  }

  @Test
  void validateIsEmptyFactory() {
    final var payment = Payment.emptyFactory();
    Assertions.assertEquals(0.0, payment.getValue());
		Assertions.assertNull(payment.getPaidIn());
		Assertions.assertNull(payment.getCustomerId());
		Assertions.assertNull(payment.getMethod());
		Assertions.assertNull(payment.getStatus());
		Assertions.assertNull(payment.getOrderId());
  }

  @Test
  void validateUniqueIdGeneratedByFactory() {
    final var payment1 = Payment.factory(100.00, PaymentStatus.PENDING, PaymentMethod.CREDIT_CARD, Instant.now(), "123", "456");
    final var payment2 = Payment.factory(200.00, PaymentStatus.PENDING, PaymentMethod.CREDIT_CARD, Instant.now(), "789", "101");

    Assertions.assertNotEquals(payment1.getId(), payment2.getId());
  }
}
