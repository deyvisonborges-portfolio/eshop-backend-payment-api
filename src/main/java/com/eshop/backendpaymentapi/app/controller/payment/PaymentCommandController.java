package com.eshop.backendpaymentapi.app.controller.payment;

import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.create.CreatePaymentCommand;
import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.create.CreatePaymentHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "billing/payment")
public class PaymentCommandController {
  private final CreatePaymentHandler createPaymentHandler;

	public PaymentCommandController(CreatePaymentHandler createPaymentHandler) {
		this.createPaymentHandler = createPaymentHandler;
	}

	public ResponseEntity<?> health() {
    return ResponseEntity.ok().body("Health check");
  }

  @PostMapping
  public void create(@RequestBody CreatePaymentCommand command) {
    this.createPaymentHandler.execute(command);
  }
}
