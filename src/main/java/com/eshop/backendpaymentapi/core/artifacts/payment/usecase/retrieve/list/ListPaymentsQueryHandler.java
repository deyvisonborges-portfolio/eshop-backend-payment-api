package com.eshop.backendpaymentapi.core.artifacts.payment.usecase.retrieve.list;

import com.eshop.backendpaymentapi.core.artifacts.payment.PaymentSearchQuery;
import com.eshop.backendpaymentapi.core.artifacts.payment.repository.PaymentRepositoryContract;
import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.UseCaseContract;
import com.eshop.backendpaymentapi.lib.Pagination;

import java.util.Objects;

public class ListPaymentsQueryHandler
  extends UseCaseContract<PaymentSearchQuery, Pagination<ListPaymentsQueryOutput>> {
  private final PaymentRepositoryContract repository;

	public ListPaymentsQueryHandler(PaymentRepositoryContract repository) {
		this.repository = Objects.requireNonNull(repository);
	}

	@Override
  public Pagination<ListPaymentsQueryOutput> execute(final PaymentSearchQuery query) {
    return this.repository.findAll(query)
      .map(ListPaymentsQueryOutput::from);
  }
}
