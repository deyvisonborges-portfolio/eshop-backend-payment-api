package com.eshop.backendpaymentapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;

@SpringBootApplication
public class BackendPaymentApiApplication {

	public static void main(String[] args) {
    System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "dev");
		SpringApplication.run(BackendPaymentApiApplication.class, args);
	}
}
