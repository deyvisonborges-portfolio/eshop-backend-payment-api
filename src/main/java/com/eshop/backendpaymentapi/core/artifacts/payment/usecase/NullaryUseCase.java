package com.eshop.backendpaymentapi.core.artifacts.payment.usecase;

/*
* Use to return a payload to use case
* */
public abstract class NullaryUseCase<O> {
	public abstract O execute();
}
