package com.mach.machorderrestapi.common.base;

public abstract class ServiceContract<I, O> {
	public abstract O execute(I input);
}
