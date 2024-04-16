package com.eshop.backendpaymentapi.app.persistence;

import com.eshop.backendpaymentapi.RepositoryAnnotation;
import com.eshop.backendpaymentapi.app.persistence.payment.PaymentJPARepository;
import com.eshop.backendpaymentapi.core.artifacts.payment.Payment;
import com.eshop.backendpaymentapi.core.artifacts.payment.repository.PaymentRepositoryContract;
import com.eshop.backendpaymentapi.lib.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@RepositoryAnnotation
public class PaymentJPARepositoryTest {
  @Autowired
  private PaymentJPARepository jpaRepository;
  @Autowired
  private  PaymentRepositoryContract contract;

  @Test
  void testInjectDependencies() {
    Assertions.assertNotNull(jpaRepository);
    Assertions.assertNotNull(contract);
  }

  @Test
  void givenAValidPayment_whenCallsCreate_shouldReturnANewPayment() {
    final var payment = Payment.emptyFactory();
    final var actualPayment = this.jpaRepository.save(payment);

    Assertions.assertEquals(payment.getActive(), actualPayment.getActive());
    Assertions.assertEquals(payment.getValue(), actualPayment.getValue());
    Assertions.assertEquals(payment.getStatus(), actualPayment.getStatus());
    Assertions.assertEquals(payment.getMethod(), actualPayment.getMethod());
    Assertions.assertEquals(payment.getPaidIn(), actualPayment.getPaidIn());
    Assertions.assertEquals(payment.getOrderId(), actualPayment.getOrderId());
    Assertions.assertEquals(payment.getCustomerId(), actualPayment.getCustomerId());
  }

  @Test
  void givenAPrePersistedPaymentAndValidPaymentId_whenTryToDeleteIt_shouldDeletePayment() {
    final var payment = Payment.emptyFactory();
    final var paymentId = payment.getId().getValue();

    final var actualPayment = this.jpaRepository.save(payment);
    final var savedPayment = this.jpaRepository.findById(paymentId).get();

    Assertions.assertNotNull(savedPayment);
    Assertions.assertEquals(actualPayment.getId(), savedPayment.getId());

    this.jpaRepository.delete(payment.getId().getValue());
    Assertions.assertThrows(NotFoundException.class, () -> this.jpaRepository.findById(paymentId));
  }
}
