package com.eshop.backendpaymentapi.core.artifact.payment.usecase;

import com.eshop.backendpaymentapi.core.artifacts.payment.Payment;
import com.eshop.backendpaymentapi.core.artifacts.payment.constant.PaymentMethod;
import com.eshop.backendpaymentapi.core.artifacts.payment.constant.PaymentStatus;
import com.eshop.backendpaymentapi.core.artifacts.payment.repository.PaymentRepositoryContract;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.Instant;
import java.util.UUID;

public class DeletePaymentTest {
  @Mock
  private PaymentRepositoryContract repository;

  @InjectMocks
  private DeletePaymentHandler handler;

  @BeforeEach
  void cleanUp() {
    Mockito.reset(handler);
  }

  @Test
  public void givenAValidCommand_whenCallsDeletePayment_shouldBeOk() {
    final var id = UUID.randomUUID().toString();
    final var payment = Payment.factory(10.00, PaymentStatus.OPEN, PaymentMethod.DEBIT_CARD, Instant.now(), id, id);

    final var expectedId = payment.getId();

    /*
    * O que sera feito antes e depois no quando
    * */
    Mockito.doNothing().when(repository).delete(
        Mockito.eq(
          expectedId.getValue()
        )
      );

    Assertions.assertDoesNotThrow(() -> this.handler.execute(expectedId.getValue()));

    /*
    * Verifica se foi chamado pelo menos uma vez
    * */
    Mockito.verify(repository, Mockito.times(1)).delete(expectedId.getValue());
  }
}
