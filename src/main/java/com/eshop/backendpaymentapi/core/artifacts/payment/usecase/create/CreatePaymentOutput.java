package com.eshop.backendpaymentapi.core.artifacts.payment.usecase.create;

import com.eshop.backendpaymentapi.core.artifacts.payment.Payment;
import com.eshop.backendpaymentapi.core.artifacts.payment.PaymentID;

public record CreatePaymentOutput(
	PaymentID id
) {
	public static CreatePaymentOutput from(final Payment payment) {
		return new CreatePaymentOutput(payment.getId());
	}
}
