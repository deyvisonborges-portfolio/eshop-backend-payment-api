package com.eshop.backendpaymentapi.app.api.payment;

import com.eshop.backendpaymentapi.app.api.payment.contract.PaymentCommandAPIContract;
import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.commands.create.CreatePaymentCommand;
import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.commands.create.CreatePaymentCommandHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentCommandController implements PaymentCommandAPIContract {
  private final CreatePaymentCommandHandler createPaymentCommandHandler;

	public PaymentCommandController(CreatePaymentCommandHandler createPaymentCommandHandler) {
		this.createPaymentCommandHandler = createPaymentCommandHandler;
	}

	public ResponseEntity<?> health() {
    return ResponseEntity.ok().body("Health check");
  }

  @Override
  public ResponseEntity<?> createPayment(final CreatePaymentCommand command) {
    this.createPaymentCommandHandler.execute(command);
    return ResponseEntity.status(HttpStatus.CREATED).body(null);
  }
}
