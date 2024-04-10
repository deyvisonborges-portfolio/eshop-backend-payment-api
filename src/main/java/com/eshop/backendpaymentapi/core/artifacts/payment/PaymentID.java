package com.eshop.backendpaymentapi.core.artifacts.payment;

import com.eshop.backendpaymentapi.lib.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

public class PaymentID extends Identifier {
	private final String value;

	public PaymentID(final String value) {
		Objects.requireNonNull(value);
		this.value = value;
	}

	public static PaymentID unique() {
		return PaymentID.from(UUID.randomUUID());
	}

	public static PaymentID from(final UUID anId) {
		return new PaymentID(anId.toString().toLowerCase());
	}

	public String getValue() {
		return this.value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PaymentID paymentID = (PaymentID) o;
		return Objects.equals(getValue(), paymentID.getValue());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getValue());
	}
}
