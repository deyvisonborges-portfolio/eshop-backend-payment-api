package com.eshop.backendpaymentapi.app.api;

import com.eshop.backendpaymentapi.ControllerTestAnnotation;
import com.eshop.backendpaymentapi.app.api.payment.PaymentQueryController;
import com.eshop.backendpaymentapi.core.artifacts.payment.Payment;
import com.eshop.backendpaymentapi.core.artifacts.payment.PaymentID;
import com.eshop.backendpaymentapi.core.artifacts.payment.constant.PaymentMethod;
import com.eshop.backendpaymentapi.core.artifacts.payment.constant.PaymentStatus;
import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.retrieve.get.GetPaymentByIdOutput;
import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.retrieve.get.GetPaymentByIdQueryHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
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

@ControllerTestAnnotation(controllers = PaymentQueryController.class)
public class PaymentQueryHandlersAPITest {
  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper mapper;

  @MockBean
  private GetPaymentByIdQueryHandler getPaymentByIdQueryHandler;

  @Test
  public void givenAValidCommand_whenCallsDeletePayment_shouldBeOk() throws Exception {
    // given
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

    final var expectedQueryId = id.getValue();

    Mockito.when(getPaymentByIdQueryHandler.execute(Mockito.any()))
      .thenReturn(GetPaymentByIdOutput.from(payment));

    // when
    final var request = MockMvcRequestBuilders.get("/payments/{id}", expectedQueryId)
      .accept(MediaType.APPLICATION_JSON)
      .contentType(MediaType.APPLICATION_JSON);
    final var response = this.mvc.perform(request).andDo(MockMvcResultHandlers.print());

    response
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
      .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.equalTo(expectedQueryId)))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.status().isOk());

    // then
    Mockito.verify(getPaymentByIdQueryHandler, Mockito.times(1))
      .execute(Mockito.eq(expectedQueryId));
//
//    Assertions.assertEquals(output.value(), payment.getValue());
//    Assertions.assertEquals(output.status(), payment.getStatus());
//    Assertions.assertEquals(output.method(), payment.getMethod());
//    Assertions.assertEquals(output.paidIn(), payment.getPaidIn());
//    Assertions.assertEquals(output.orderId(), payment.getOrderId());
//    Assertions.assertEquals(output.customerId(), payment.getCustomerId());
//
//    Assertions.assertDoesNotThrow(() -> this.handler.execute(expectedQueryId));
//    Mockito.verify(repository, Mockito.times(2)).findById(expectedQueryId);
  }

  @Test
  public void givenAValidCommand_whenCallsDeletePayment_shouldNotFound() {
//    final var id = PaymentID.unique();
//    final var expectedQueryId = new GetPaymentByIdQuery(id.getValue());
//
//    Mockito.when(this.repository.findById(Mockito.anyString()))
//      .thenReturn(Optional.empty());
//
//    final var expectedExceptionMessage = MessageFormat.format("Not found Payment with id: {0}", expectedQueryId);
//    final var actualException = Assertions.assertThrows(
//      NotFoundException.class,
//      () -> this.handler.execute(expectedQueryId)
//    );
//
//    Assertions.assertEquals(expectedExceptionMessage, actualException.getMessage());
  }
}
