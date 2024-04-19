package com.eshop.backendpaymentapi.app.api;

import com.eshop.backendpaymentapi.ControllerTestAnnotation;
import com.eshop.backendpaymentapi.app.api.payment.PaymentCommandController;
import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.commands.create.CreatePaymentCommandHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@ControllerTestAnnotation(controllers = PaymentCommandController.class)
public class PaymentAPITest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private CreatePaymentCommandHandler createPaymentCommandHandler;

  @Test
  void test() {

  }
}
