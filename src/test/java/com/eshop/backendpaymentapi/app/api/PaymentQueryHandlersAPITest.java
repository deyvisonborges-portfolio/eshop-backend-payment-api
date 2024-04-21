package com.eshop.backendpaymentapi.app.api;

import com.eshop.backendpaymentapi.ControllerTestAnnotation;
import com.eshop.backendpaymentapi.app.api.payment.PaymentQueryController;
import com.eshop.backendpaymentapi.core.artifacts.payment.Payment;
import com.eshop.backendpaymentapi.core.artifacts.payment.PaymentID;
import com.eshop.backendpaymentapi.core.artifacts.payment.constant.PaymentMethod;
import com.eshop.backendpaymentapi.core.artifacts.payment.constant.PaymentStatus;
import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.retrieve.get.GetPaymentByIdOutput;
import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.retrieve.get.GetPaymentByIdQueryHandler;
import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.retrieve.list.ListPaymentsQueryHandler;
import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.retrieve.list.ListPaymentsQueryOutput;
import com.eshop.backendpaymentapi.lib.Pagination;
import com.eshop.backendpaymentapi.lib.SearchDirection;
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
import java.util.List;
import java.util.Objects;

@ControllerTestAnnotation(controllers = PaymentQueryController.class)
public class PaymentQueryHandlersAPITest {
  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper mapper;

  @MockBean
  private GetPaymentByIdQueryHandler getPaymentByIdQueryHandler;

  @MockBean
  private ListPaymentsQueryHandler listPaymentsQueryHandler;

  @Test
  public void givenAValidCommand_whenCallsGetPaymentById_shouldReturnPayment() throws Exception {
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

    final var request = MockMvcRequestBuilders.get("/payments/{id}", expectedQueryId)
      .accept(MediaType.APPLICATION_JSON)
      .contentType(MediaType.APPLICATION_JSON);
    final var response = this.mvc.perform(request).andDo(MockMvcResultHandlers.print());

    response
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
      .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.equalTo(expectedQueryId)));

    Mockito.verify(getPaymentByIdQueryHandler, Mockito.times(1))
      .execute(Mockito.eq(expectedQueryId));
  }

  @Test
  void givenValidParams_whenCallsListPayments_shouldReturnPayments() throws Exception {
    final var payment = Payment.emptyFactory();

    final var expectedPage = 0;
    final var expectedPerPage = 10;
    final var expectedTerms = "";
    final var expectedSort = "status";
    final var expectedDirection = SearchDirection.ASCENDANT;
    final var expectedTotal = 1;
    final var expectedItemsCount = 1;

    final var expectedItems = List.of(ListPaymentsQueryOutput.from(payment));

    Mockito.when(listPaymentsQueryHandler.execute(Mockito.any()))
      .thenReturn(new Pagination<>(expectedPage, expectedPerPage, expectedTotal, expectedItems));

    final var request = MockMvcRequestBuilders.get("/payments")
      .queryParam("page", String.valueOf(expectedPage))
      .queryParam("perPage", String.valueOf(expectedPerPage))
      .queryParam("sort", expectedSort)
      .queryParam("dir", String.valueOf(expectedDirection))
      .queryParam("search", expectedTerms)
      .accept(MediaType.APPLICATION_JSON)
      .contentType(MediaType.APPLICATION_JSON);

    final var response = this.mvc.perform(request)
      .andDo(MockMvcResultHandlers.print());

    response
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$.currentPage", Matchers.equalTo(expectedPage)))
      .andExpect(MockMvcResultMatchers.jsonPath("$.perPage", Matchers.equalTo(expectedPerPage)))
      .andExpect(MockMvcResultMatchers.jsonPath("$.total", Matchers.equalTo(expectedTotal)))
      .andExpect(MockMvcResultMatchers.jsonPath("$.items", Matchers.hasSize(expectedItemsCount)))
      .andExpect(MockMvcResultMatchers.jsonPath("$.items[0].id", Matchers.equalTo(payment.getId().getValue())))
      .andExpect(MockMvcResultMatchers.jsonPath("$.items[0].status", Matchers.equalTo(payment.getStatus().getValue())))
    ;

    Mockito.verify(listPaymentsQueryHandler, Mockito.times(1)).execute(Mockito.argThat(query ->
      Objects.equals(expectedPage, query.page())
        && Objects.equals(expectedPerPage, query.perPage())
        && Objects.equals(expectedDirection, query.direction())
        && Objects.equals(expectedDirection, query.direction())
    ));
  }
}
