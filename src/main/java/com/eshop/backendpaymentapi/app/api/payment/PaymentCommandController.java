package com.eshop.backendpaymentapi.app.api.payment;

import com.eshop.backendpaymentapi.app.api.payment.contract.PaymentCommandAPIContract;
import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.commands.create.CreatePaymentCommand;
import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.commands.create.CreatePaymentCommandHandler;
import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.commands.delete.DeletePaymentCommandHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class PaymentCommandController implements PaymentCommandAPIContract {
  private final CreatePaymentCommandHandler createPaymentCommandHandler;
  private final DeletePaymentCommandHandler deletePaymentCommandHandler;

	public PaymentCommandController(
    final CreatePaymentCommandHandler createPaymentCommandHandler,
    final DeletePaymentCommandHandler deletePaymentCommandHandler
  ) {
		this.createPaymentCommandHandler = Objects.requireNonNull(createPaymentCommandHandler);
		this.deletePaymentCommandHandler = Objects.requireNonNull(deletePaymentCommandHandler);
	}

	public ResponseEntity<?> health() {
    return ResponseEntity.ok().body("Health check");
  }

  @Override
  public ResponseEntity<?> createPayment(final CreatePaymentCommand command) {
    this.createPaymentCommandHandler.execute(command);
    return ResponseEntity.status(HttpStatus.CREATED).body(null);
  }

  @Override
  public void getPaymentById(String id) {
    this.deletePaymentCommandHandler.execute(id);
  }
}
