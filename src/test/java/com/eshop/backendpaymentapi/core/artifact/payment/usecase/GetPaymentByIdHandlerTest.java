package com.eshop.backendpaymentapi.core.artifact.payment.usecase;

import com.eshop.backendpaymentapi.core.artifacts.payment.Payment;
import com.eshop.backendpaymentapi.core.artifacts.payment.PaymentID;
import com.eshop.backendpaymentapi.core.artifacts.payment.constant.PaymentMethod;
import com.eshop.backendpaymentapi.core.artifacts.payment.constant.PaymentStatus;
import com.eshop.backendpaymentapi.core.artifacts.payment.repository.PaymentRepositoryContract;
import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.retrieve.GetPaymentByIdCommand;
import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.retrieve.GetPaymentByIdHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class GetPaymentByIdHandlerTest {
  @Mock
  private PaymentRepositoryContract repository;

  @InjectMocks
  private GetPaymentByIdHandler handler;

  @BeforeEach
  void cleanUp() {
    Mockito.reset(repository);
  }

  @Test
  public void givenAValidCommand_whenCallsDeletePayment_shouldBeOk() {
    final var id = PaymentID.unique();

    final var payment = new Payment(
      0.0,
      PaymentStatus.OPEN,
      PaymentMethod.DEBIT_CARD,
      Instant.now(),
      id.getValue(),
      id.getValue()
    );

    payment.setId(id);

    final var expectedCommand = new GetPaymentByIdCommand(id.getValue());

    Mockito.when(repository.findById(Mockito.eq(expectedCommand.id())))
      .thenReturn(Optional.of(payment.clone()));

    final var output = this.handler.execute(expectedCommand);

    Assertions.assertEquals(output.getValue(), payment.getValue());
    Assertions.assertEquals(output.getStatus(), payment.getStatus());
    Assertions.assertEquals(output.getMethod(), payment.getMethod());
    Assertions.assertEquals(output.getPaidIn(), payment.getPaidIn());
    Assertions.assertEquals(output.getOrderId(), payment.getOrderId());
    Assertions.assertEquals(output.getCustomerId(), payment.getCustomerId());

    Assertions.assertDoesNotThrow(() -> this.handler.execute(expectedCommand));
    Mockito.verify(repository, Mockito.times(2)).findById(expectedCommand.id());
  }

  @Test
  public void givenAValidCommand_whenCallsDeletePayment_shouldNotFound() {

  }
}
