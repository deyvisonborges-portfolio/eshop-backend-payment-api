package com.eshop.backendpaymentapi.app.controller.payment;

import com.eshop.backendpaymentapi.app.persistence.payment.PaymentJPARepository;
import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.create.CreatePaymentCommand;
import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.create.CreatePaymentHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
  private final CreatePaymentHandler createPaymentHandler;
  private final PaymentJPARepository paymentJPARepository;

	public PaymentController(CreatePaymentHandler createPaymentHandler, PaymentJPARepository paymentJPARepository) {
		this.paymentJPARepository = paymentJPARepository;
		this.createPaymentHandler = new CreatePaymentHandler(paymentJPARepository);
	}

	public ResponseEntity<?> health() {
    return ResponseEntity.ok().body("Health check");
  }

  @PostMapping
  public void create(@RequestBody CreatePaymentCommand command) {
    this.createPaymentHandler.execute(command);
  }
}
