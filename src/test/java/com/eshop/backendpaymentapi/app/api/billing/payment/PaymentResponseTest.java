package com.eshop.backendpaymentapi.app.api.billing.payment;

import com.eshop.backendpaymentapi.JacksonTestAnnotation;
import com.eshop.backendpaymentapi.core.artifacts.payment.Payment;
import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.commands.create.CreatePaymentOutput;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;

@JacksonTestAnnotation
public class PaymentResponseTest {
  @Autowired
  private JacksonTester<CreatePaymentOutput> json;

  @Test
  public void testMarshall() throws Exception {
    final var payment = Payment.emptyFactory();

    final var actualJson = this.json.write(CreatePaymentOutput.from(payment));

    Assertions.assertThat(actualJson)
      .hasJsonPathValue("$.id", payment.getId().getValue());
  }
}
