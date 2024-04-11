package com.eshop.backendpaymentapi.core.artifacts.payment.usecase.create;

import com.eshop.backendpaymentapi.core.artifacts.payment.Payment;
import com.eshop.backendpaymentapi.core.artifacts.payment.repository.PaymentRepositoryContract;
import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.UseCaseContract;
import com.eshop.backendpaymentapi.lib.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreatePaymentHandler extends UseCaseContract<CreatePaymentCommand, CreatePaymentOutput> {
  private final Logger logger = LoggerFactory.getLogger(CreatePaymentHandler.class);
	private final PaymentRepositoryContract repository;

	public CreatePaymentHandler(PaymentRepositoryContract repository) {
		this.repository = repository;
	}

	@Override
	public CreatePaymentOutput execute(final CreatePaymentCommand command) {
		try {
      final var value = command.value();
      final var status = command.status();
      final var method = command.method();
      final var paidIn = command.paidIn();
      final var orderId = command.orderId();
      final var customerId = command.customerId();

      final var payment = Payment.factory(value, status, method, paidIn, orderId, customerId);

      this.repository.save(payment);

      return CreatePaymentOutput.from(payment);
    } catch (Exception e) {
      this.logger.info("Error while saving the payment", e);
      throw new BadRequestException(e.getMessage());
    }
	}
}
