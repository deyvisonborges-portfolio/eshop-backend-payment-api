package com.eshop.backendpaymentapi.app.api.payment;

import com.eshop.backendpaymentapi.app.api.payment.contract.PaymentQueryAPIContract;
import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.retrieve.get.GetPaymentByIdOutput;
import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.retrieve.get.GetPaymentByIdQueryHandler;
import com.eshop.backendpaymentapi.lib.Pagination;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentQueryController implements PaymentQueryAPIContract {
  private final GetPaymentByIdQueryHandler queryHandler;

	public PaymentQueryController(GetPaymentByIdQueryHandler queryHandler) {
		this.queryHandler = queryHandler;
	}

	@Override
  public Pagination<?> listPayments(String search, int page, int perPage, String sort, String direction) {
    return null;
  }

  @Override
  public GetPaymentByIdOutput getPaymentById(final String id) {
    return this.queryHandler.execute(id);
  }
}
