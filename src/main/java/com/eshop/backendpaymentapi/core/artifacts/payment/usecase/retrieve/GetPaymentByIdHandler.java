package com.eshop.backendpaymentapi.core.artifacts.payment.usecase.retrieve;

import com.eshop.backendpaymentapi.core.artifacts.payment.Payment;
import com.eshop.backendpaymentapi.core.artifacts.payment.repository.PaymentRepositoryContract;
import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.UseCaseContract;

import java.util.Optional;

public class GetPaymentByIdHandler extends UseCaseContract<GetPaymentByIdCommand, Payment> {
  private final PaymentRepositoryContract repository;

	public GetPaymentByIdHandler(PaymentRepositoryContract repository) {
		this.repository = repository;
	}

	@Override
  public Payment execute(GetPaymentByIdCommand input) {
    return this.repository.findById(input.id()).get();
  }
}
