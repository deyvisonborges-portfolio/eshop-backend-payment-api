package com.eshop.backendpaymentapi.core.artifacts.payment.usecase;

public abstract class UseCaseContract<I, O> {
	public abstract O execute(I input);
}
