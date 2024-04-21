package com.eshop.backendpaymentapi.core.artifacts.payment.usecase.retrieve.get;

import com.eshop.backendpaymentapi.core.artifacts.payment.repository.PaymentRepositoryContract;
import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.UseCaseContract;
import com.eshop.backendpaymentapi.lib.exception.NotFoundException;

import java.text.MessageFormat;
import java.util.Objects;

public class GetPaymentByIdQueryHandler extends UseCaseContract<String, GetPaymentByIdOutput> {
  private final PaymentRepositoryContract repository;

	public GetPaymentByIdQueryHandler(PaymentRepositoryContract repository) {
		this.repository = Objects.requireNonNull(repository);
	}

	@Override
  public GetPaymentByIdOutput execute(final String id) {
    final var paymentId = id;
    return this.repository.findById(paymentId)
      .map(GetPaymentByIdOutput::from)
      .orElseThrow(() -> new NotFoundException(
        MessageFormat.format("Not found Payment with id: {0}", paymentId))
      );
  }
}
