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

  public static PaymentMethod fromString(String text) {
    for (PaymentMethod method : PaymentMethod.values()) {
      if (method.getValue().equalsIgnoreCase(text)) {
        return method;
      }
      throw new IllegalArgumentException("Method inválido: " + method);
    }
    return null;
  }
}
