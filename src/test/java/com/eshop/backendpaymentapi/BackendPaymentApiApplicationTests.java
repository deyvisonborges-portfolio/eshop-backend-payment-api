package com.eshop.backendpaymentapi;

import com.eshop.backendpaymentapi.core.artifacts.payment.Payment;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendPaymentApiApplicationTests {

	@Test
	void contextLoads() {
    var payment = Payment.emptyFactory();
    System.out.println(payment);
	}

}
