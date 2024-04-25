package com.eshop.backendpaymentapi.core.artifacts.payment.usecase.commands.create;

import com.eshop.backendpaymentapi.core.artifacts.payment.Payment;
import com.eshop.backendpaymentapi.core.artifacts.payment.repository.PaymentRepositoryContract;
import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.UseCaseContract;
import com.eshop.backendpaymentapi.lib.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CreatePaymentCommandHandler extends UseCaseContract<CreatePaymentCommand, CreatePaymentOutput> {
  private final Logger logger = LoggerFactory.getLogger(CreatePaymentCommandHandler.class);
	private final PaymentRepositoryContract repository;

	public CreatePaymentCommandHandler(PaymentRepositoryContract repository) {
		this.repository = Objects.requireNonNull(repository);
	}

	@Override
	public CreatePaymentOutput execute(final CreatePaymentCommand command) {
		try {
      final var value = command.value();
      final var status = command.status();
      final var method = command.method();
      final var orderId = command.orderId();
      final var customerId = command.customerId();

      final var payment = Payment.factory(value, status, method, orderId, customerId);

      this.repository.save(payment);

      return CreatePaymentOutput.from(payment);
    } catch (Exception e) {
      this.logger.info("Error while saving the payment", e);
      throw new BadRequestException(e.getMessage());
    }
	}
}
