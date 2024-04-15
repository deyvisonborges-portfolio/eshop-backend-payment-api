package com.eshop.backendpaymentapi.app.controller.payment;

import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.commands.create.CreatePaymentCommand;
import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.commands.create.CreatePaymentCommandHandler;
import jdk.jfr.ContentType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "billing/payment")
public class PaymentCommandController {
  private final CreatePaymentCommandHandler createPaymentCommandHandler;

	public PaymentCommandController(CreatePaymentCommandHandler createPaymentCommandHandler) {
		this.createPaymentCommandHandler = createPaymentCommandHandler;
	}

	public ResponseEntity<?> health() {
    return ResponseEntity.ok().body("Health check");
  }

  @PostMapping
  public void create(@RequestBody CreatePaymentCommand command) {
    this.createPaymentCommandHandler.execute(command);
  }
}
