package com.eshop.backendpaymentapi.core.artifacts.payment;

import com.eshop.backendpaymentapi.lib.SearchDirection;

public record PaymentSearchQuery(
	int page,
	int perPage,
	String terms,
	String sort,
	SearchDirection direction
) { }
