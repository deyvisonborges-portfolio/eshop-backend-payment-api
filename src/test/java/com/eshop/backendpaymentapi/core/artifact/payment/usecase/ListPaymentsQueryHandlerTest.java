package com.eshop.backendpaymentapi.core.artifact.payment.usecase;

import com.eshop.backendpaymentapi.core.artifacts.payment.Payment;
import com.eshop.backendpaymentapi.core.artifacts.payment.PaymentSearchQuery;
import com.eshop.backendpaymentapi.core.artifacts.payment.repository.PaymentRepositoryContract;
import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.retrieve.list.ListPaymentsQueryHandler;
import com.eshop.backendpaymentapi.lib.Pagination;
import com.eshop.backendpaymentapi.lib.SearchDirection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ListPaymentsQueryHandlerTest {
  @Mock
  private PaymentRepositoryContract repository;

  @InjectMocks
  private ListPaymentsQueryHandler handler;

  @BeforeEach
  void cleanUp() {
    Mockito.reset(repository);
  }

  @Test
  void givenAValidQuery_whenCallsListPayments_thenShouldReturnPayments() {
    final var payments =List.of(
      Payment.emptyFactory(),
      Payment.emptyFactory()
    );

    final var expectedPage = 0;
    final var expectedPerPage = 10;
    final var expectedTerms = "";
    final var expectedSort = "createdAt";
    final var expectedDirection = SearchDirection.ASCENDANT;

    final var searchQuery = new PaymentSearchQuery(
      expectedPage,
      expectedPerPage,
      expectedTerms,
      expectedSort,
      expectedDirection
    );

    final var exptectedPagination = new Pagination<>(
      expectedPage,
      expectedPerPage,
      payments.size(),
      payments
    );

    final var expectedItemsCount = 2;
    final var expectedResult = exptectedPagination.map(ListPaymentOutput::from);

    Mockito.when(this.repository.findAll(searchQuery))
      .thenReturn(exptectedPagination);

    final var actualResult = this.handler.execute(searchQuery);

    Assertions.assertEquals(expectedItemsCount, actualResult.items.size());
    Assertions.assertEquals(expectedResult, actualResult.items.size());
    Assertions.assertEquals(expectedPage, actualResult.page());
    Assertions.assertEquals(expectedPerPage, actualResult.perPage());
    Assertions.assertEquals(payments.size(), actualResult.total());
  }

  @Test
  void givenAValidQuery_whenHasNoResults_thenShouldReturnEmptyPayments(){}

  @Test
  void givenAValidQuery_whenGatewayThrowsException_shouldReturnException(){}
}
