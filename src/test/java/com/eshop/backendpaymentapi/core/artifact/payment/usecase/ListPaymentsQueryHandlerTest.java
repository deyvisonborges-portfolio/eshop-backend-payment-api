package com.eshop.backendpaymentapi.core.artifact.payment.usecase;

import com.eshop.backendpaymentapi.core.artifacts.payment.Payment;
import com.eshop.backendpaymentapi.core.artifacts.payment.PaymentSearchQuery;
import com.eshop.backendpaymentapi.core.artifacts.payment.constant.PaymentMethod;
import com.eshop.backendpaymentapi.core.artifacts.payment.constant.PaymentStatus;
import com.eshop.backendpaymentapi.core.artifacts.payment.repository.PaymentRepositoryContract;
import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.retrieve.list.ListPaymentsQueryHandler;
import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.retrieve.list.ListPaymentsQueryOutput;
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

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class ListPaymentsQueryHandlerTest {
  @InjectMocks
  private ListPaymentsQueryHandler handler;

  @Mock
  private PaymentRepositoryContract repository;

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

    Mockito.when(this.repository.findAll(searchQuery))
      .thenReturn(exptectedPagination);

    final var actualResult = this.repository.findAll(searchQuery);

    Assertions.assertEquals(expectedItemsCount, actualResult.items().size());
    Assertions.assertEquals(expectedPage, actualResult.currentPage());
    Assertions.assertEquals(expectedPerPage, actualResult.perPage());
    Assertions.assertEquals(payments.size(), actualResult.total());
  }

  @Test
  void givenAValidQuery_whenHasNoResults_thenShouldReturnEmptyPayments(){
    final var payments =List.<Payment>of();

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

    final var expectedItemsCount = 0;
    final var expectedResult = exptectedPagination.map(ListPaymentsQueryOutput::from);

    Mockito.when(this.repository.findAll(searchQuery))
      .thenReturn(exptectedPagination);

    final var actualResult = this.handler.execute(searchQuery);

    Assertions.assertEquals(expectedItemsCount, actualResult.items().size());
    Assertions.assertEquals(expectedResult, actualResult);
    Assertions.assertEquals(expectedPage, actualResult.currentPage());
    Assertions.assertEquals(expectedPerPage, actualResult.perPage());
    Assertions.assertEquals(payments.size(), actualResult.total());
  }

  @Test
  void givenAValidQuery_whenGatewayThrowsException_shouldReturnException(){
    final var expectedPage = 0;
    final var expectedPerPage = 10;
    final var expectedTerms = "";
    final var expectedSort = "createdAt";
    final var expectedDirection = SearchDirection.ASCENDANT;
    final var expectedErrorMessage = "";

    final var searchQuery = new PaymentSearchQuery(
      expectedPage,
      expectedPerPage,
      expectedTerms,
      expectedSort,
      expectedDirection
    );

    Mockito.when(this.repository.findAll(searchQuery))
      .thenThrow(new IllegalStateException(expectedErrorMessage));

    final var actualException = Assertions.assertThrows(
      IllegalStateException.class,
      () -> this.handler.execute(searchQuery)
    );

    Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
  }

  @Test
  void givenPrePersistPayments_whenCallsFindAll_shouldReturnPaginated() {
    final var id = UUID.randomUUID().toString();

    final var expectedPage = 0;
    final var expectedPerPage = 3;
    final var expectedTotal = 3;

    final var pix = Payment.factory(10.00, PaymentStatus.OPEN, PaymentMethod.PIX, id, id);
    final var credit = Payment.factory(10.00, PaymentStatus.OPEN, PaymentMethod.CREDIT_CARD, id, id);
    final var debit = Payment.factory(10.00, PaymentStatus.OPEN, PaymentMethod.DEBIT_CARD, id, id);

    Mockito.when(repository.findAll(Mockito.any())).thenReturn(
      new Pagination<>(expectedPage, expectedPerPage, expectedTotal, List.of(pix, credit, debit))
    );

    final var query = new PaymentSearchQuery(expectedPage, expectedPerPage, "", "", SearchDirection.ASCENDANT);
    final var actualResult = handler.execute(query);

    Assertions.assertEquals(expectedPage, actualResult.currentPage());
    Assertions.assertEquals(expectedPerPage, actualResult.perPage());
    Assertions.assertEquals(expectedTotal, actualResult.total());
    Assertions.assertEquals(expectedPerPage, actualResult.items().size());
    Assertions.assertEquals(pix.getId(), actualResult.items().get(0).id());

  }

  @Test
  void givenEmptyPaymentsTable_whenCallsFindAll_shouldReturnEmptyPage() {

  }

  @Test
  void givenFollowPagination_whenCallsFindAllWithPage1_shouldReturnPaginated() {

  }
}
