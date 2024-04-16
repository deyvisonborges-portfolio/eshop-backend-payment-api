package com.eshop.backendpaymentapi.app.persistence;

import com.eshop.backendpaymentapi.RepositoryAnnotation;
import com.eshop.backendpaymentapi.app.persistence.payment.PaymentJPARepository;
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
}
