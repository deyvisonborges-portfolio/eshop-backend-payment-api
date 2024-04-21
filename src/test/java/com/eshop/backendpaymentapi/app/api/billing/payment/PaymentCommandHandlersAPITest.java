package com.eshop.backendpaymentapi.app.api.billing.payment;

import com.eshop.backendpaymentapi.ControllerTestAnnotation;
import com.eshop.backendpaymentapi.app.api.payment.PaymentCommandController;
import com.eshop.backendpaymentapi.core.artifacts.payment.PaymentID;
import com.eshop.backendpaymentapi.core.artifacts.payment.constant.PaymentMethod;
import com.eshop.backendpaymentapi.core.artifacts.payment.constant.PaymentStatus;
import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.commands.create.CreatePaymentCommand;
import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.commands.create.CreatePaymentCommandHandler;
import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.commands.delete.DeletePaymentCommandHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.Instant;
import java.util.UUID;

@ControllerTestAnnotation(controllers = PaymentCommandController.class)
public class PaymentCommandHandlersAPITest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper mapper;

  @MockBean
  private CreatePaymentCommandHandler createPaymentCommandHandler;

  @MockBean
  private DeletePaymentCommandHandler deletePaymentCommandHandler;

  @Test
  void givenAValidCommand_whenCallsCreatePayment_shouldReturnOk() throws Exception {
    final var id = UUID.randomUUID().toString();
    final var command = CreatePaymentCommand.with(
      0.0,
      PaymentStatus.OPEN,
      PaymentMethod.DEBIT_CARD,
      Instant.now(),
      id,
      id
    );

    final var request = MockMvcRequestBuilders.post("/payments")
      .contentType(MediaType.APPLICATION_JSON)
      .content(this.mapper.writeValueAsString(command));

    this.mvc.perform(request)
      .andDo(MockMvcResultHandlers.print());
  }

  @Test
  public void givenAValidCommand_whenCallsDeletePayment_shouldBeOk() throws Exception {
    final var expectedQueryId = PaymentID.unique().getValue();

    Mockito.doNothing().when(deletePaymentCommandHandler).execute(Mockito.any());

    final var request = MockMvcRequestBuilders.delete("/payments/{id}", expectedQueryId)
      .accept(MediaType.APPLICATION_JSON)
      .contentType(MediaType.APPLICATION_JSON);

    final var response = this.mvc.perform(request)
      .andDo(MockMvcResultHandlers.print());

    response
      .andExpect(MockMvcResultMatchers.status().isNoContent());

    Mockito.verify(deletePaymentCommandHandler, Mockito.times(1))
      .execute(Mockito.eq(expectedQueryId));
  }
}
