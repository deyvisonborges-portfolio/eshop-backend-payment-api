package com.eshop.backendpaymentapi.app.api.payment.contract;

import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.commands.create.CreatePaymentCommand;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/payments")
public interface PaymentCommandAPIContract {
  @PostMapping(
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  ResponseEntity<?>createPayment(@RequestBody final CreatePaymentCommand command);
}
