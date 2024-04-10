package com.eshop.backendpaymentapi.core.artifacts.payment.constant;

public enum PaymentMethod {
	CREDIT_CARD("CARTAO_DE_CREDITO"),
	DEBIT_CARD("CARTAO_DE_DEBITO"),
	PIX("PIX");

	private final String value;

	PaymentMethod(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
