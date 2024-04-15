package com.eshop.backendpaymentapi.core.artifacts.payment.usecase.commands.delete;

import com.eshop.backendpaymentapi.core.artifacts.payment.repository.PaymentRepositoryContract;
import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.UnitUseCase;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DeletePaymentCommandHandler extends UnitUseCase<DeletePaymentCommand> {
  private final PaymentRepositoryContract repository;

	public DeletePaymentCommandHandler(PaymentRepositoryContract repository) {
		this.repository = Objects.requireNonNull(repository);
	}

  @Override
  public void execute(final DeletePaymentCommand command) {
    this.repository.delete(command.id());
  }
}
