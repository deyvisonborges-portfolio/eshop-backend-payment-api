package com.eshop.backendpaymentapi.core.artifact.payment.usecase;

import com.eshop.backendpaymentapi.core.artifacts.payment.Payment;
import com.eshop.backendpaymentapi.core.artifacts.payment.PaymentID;
import com.eshop.backendpaymentapi.core.artifacts.payment.constant.PaymentMethod;
import com.eshop.backendpaymentapi.core.artifacts.payment.constant.PaymentStatus;
import com.eshop.backendpaymentapi.core.artifacts.payment.repository.PaymentRepositoryContract;
import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.retrieve.get.GetPaymentByIdQueryHandler;
import com.eshop.backendpaymentapi.lib.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.MessageFormat;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class GetPaymentByIdQueryHandlerTest {
  @Mock
  private PaymentRepositoryContract repository;

  @InjectMocks
  private GetPaymentByIdQueryHandler handler;

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
      id.getValue(),
      id.getValue()
    );

    payment.setId(id);

    final var expectedQueryId = id.getValue();

    Mockito.when(repository.findById(Mockito.eq(expectedQueryId)))
      .thenReturn(Optional.of(payment.clone()));

    final var output = this.handler.execute(expectedQueryId);

    Assertions.assertEquals(output.value(), payment.getValue());
    Assertions.assertEquals(output.status(), payment.getStatus());
    Assertions.assertEquals(output.method(), payment.getMethod());
    Assertions.assertEquals(output.orderId(), payment.getOrderId());
    Assertions.assertEquals(output.customerId(), payment.getCustomerId());

    Assertions.assertDoesNotThrow(() -> this.handler.execute(expectedQueryId));
    Mockito.verify(repository, Mockito.times(2)).findById(expectedQueryId);
  }

  @Test
  public void givenAValidCommand_whenCallsDeletePayment_shouldNotFound() {
    final var id = PaymentID.unique();
    final var expectedQueryId = id.getValue();

    Mockito.when(this.repository.findById(Mockito.anyString()))
      .thenReturn(Optional.empty());

    final var expectedExceptionMessage = MessageFormat.format("Not found Payment with id: {0}", expectedQueryId);
    final var actualException = Assertions.assertThrows(
      NotFoundException.class,
      () -> this.handler.execute(expectedQueryId)
    );

    Assertions.assertEquals(expectedExceptionMessage, actualException.getMessage());
  }
}
