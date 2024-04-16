package com.eshop.backendpaymentapi.app.persistence;

import com.eshop.backendpaymentapi.RepositoryAnnotation;
import com.eshop.backendpaymentapi.app.persistence.payment.PaymentJPARepository;
import com.eshop.backendpaymentapi.core.artifacts.payment.Payment;
import com.eshop.backendpaymentapi.core.artifacts.payment.repository.PaymentRepositoryContract;
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
    final var paymentFixture = Payment.emptyFactory();
    final var actualPayment = this.jpaRepository.save(paymentFixture);

    Assertions.assertEquals(paymentFixture.getActive(), actualPayment.getActive());
    Assertions.assertEquals(paymentFixture.getValue(), actualPayment.getValue());
    Assertions.assertEquals(paymentFixture.getStatus(), actualPayment.getStatus());
    Assertions.assertEquals(paymentFixture.getMethod(), actualPayment.getMethod());
    Assertions.assertEquals(paymentFixture.getPaidIn(), actualPayment.getPaidIn());
    Assertions.assertEquals(paymentFixture.getOrderId(), actualPayment.getOrderId());
    Assertions.assertEquals(paymentFixture.getCustomerId(), actualPayment.getCustomerId());
  }
}
