package com.eshop.backendpaymentapi.core.artifacts.payment.constant;

public enum PaymentStatus {
	OPEN("ABERTO"),
	CANCELED("CANCELADO"),
	PENDING("PENDENTE"),
	AUTHORIZED("AUTORIZADO"),
	EXPIRED("EXPIRADO"),
	FAILED("FALHOU"),
	PAID("PAGO");

	private final String value;

	PaymentStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

  public static PaymentStatus fromString(String text) {
    for (PaymentStatus status : PaymentStatus.values()) {
      if (status.getValue().equalsIgnoreCase(text)) {
        return status;
      }
      throw new IllegalArgumentException("Status inválido: " + status);
    }
    return null;
  }
}
