//package com.eshop.backendpaymentapi.core.artifacts.payment.usecase.update;
//
//import com.mach.core.artifact.payment.PaymentID;
//import com.mach.core.artifact.payment.constant.PaymentMethod;
//import com.mach.core.artifact.payment.constant.PaymentStatus;
//
//public record UpdatePaymentCommand (
//	PaymentID id,
//	double value,
//	PaymentStatus status,
//	PaymentMethod method,
//	String orderId,
//	String customerId,
//	boolean active
//){
//	public static UpdatePaymentCommand with(
//		final PaymentID id,
//		final double value,
//		final PaymentStatus status,
//		final PaymentMethod method,
//		final String orderId,
//		final String customerId,
//		final boolean active
//	) {
//		return new UpdatePaymentCommand(
//			id,
//			value,
//			status,
//			method,
//			orderId,
//			customerId,
//			active
//		);
//	}
//}
