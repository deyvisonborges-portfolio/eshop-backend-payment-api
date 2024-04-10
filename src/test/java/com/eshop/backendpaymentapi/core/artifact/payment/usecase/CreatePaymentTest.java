package com.eshop.backendpaymentapi.core.artifact.payment.usecase;

import com.eshop.backendpaymentapi.core.artifacts.payment.constant.PaymentMethod;
import com.eshop.backendpaymentapi.core.artifacts.payment.constant.PaymentStatus;
import com.eshop.backendpaymentapi.core.artifacts.payment.repository.PaymentRepositoryContract;
import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.create.CreatePaymentCommand;
import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.create.CreatePaymentHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Instant;
import java.util.UUID;

public class CreatePaymentTest {
  @Test
  public void givenAValidCommand_whenCallsCreatePayment_shouldReturnPaymentId() {
    final var id = UUID.randomUUID().toString();
    final var command = CreatePaymentCommand.with(
      0.0,
      PaymentStatus.OPEN,
      PaymentMethod.DEBIT_CARD,
      Instant.now(),
      id,
      id
    );

    final var repository = Mockito.mock(PaymentRepositoryContract.class);
    final var handler = new CreatePaymentHandler(repository);

    final var output = handler.execute(command);
    Assertions.assertNotNull(output);
    System.out.println(output.id());
  }
}
