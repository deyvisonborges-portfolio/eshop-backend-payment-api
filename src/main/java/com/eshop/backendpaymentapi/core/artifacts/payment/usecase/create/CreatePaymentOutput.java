package com.eshop.backendpaymentapi.core.artifacts.payment.usecase.create;

import com.mach.core.artifact.payment.Payment;
import com.mach.core.artifact.payment.PaymentID;

public record CreatePaymentOutput(
	PaymentID id
) {
	public static CreatePaymentOutput from(final Payment payment) {
		return new CreatePaymentOutput(payment.getId());
	}
}
