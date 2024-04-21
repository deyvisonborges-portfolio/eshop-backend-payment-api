package com.eshop.backendpaymentapi.app.api.payment;

import com.eshop.backendpaymentapi.app.api.payment.contract.PaymentQueryAPIContract;
import com.eshop.backendpaymentapi.core.artifacts.payment.PaymentSearchQuery;
import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.retrieve.get.GetPaymentByIdOutput;
import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.retrieve.get.GetPaymentByIdQueryHandler;
import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.retrieve.list.ListPaymentsQueryHandler;
import com.eshop.backendpaymentapi.lib.Pagination;
import com.eshop.backendpaymentapi.lib.SearchDirection;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentQueryController implements PaymentQueryAPIContract {
  private final GetPaymentByIdQueryHandler queryHandler;
  private final ListPaymentsQueryHandler listPaymentsQueryHandler;

	public PaymentQueryController(GetPaymentByIdQueryHandler queryHandler, ListPaymentsQueryHandler listPaymentsQueryHandler) {
		this.queryHandler = queryHandler;
    this.listPaymentsQueryHandler = listPaymentsQueryHandler;
  }

	@Override
  public Pagination<?> listPayments(
    final String search,
    final int page,
    final int perPage,
    final String sort,
    final String direction
  ) {
    return this.listPaymentsQueryHandler.execute(new PaymentSearchQuery(
      page, perPage, search, sort, SearchDirection.from(direction)
    ));
  }

  @Override
  public GetPaymentByIdOutput getPaymentById(final String id) {
    return this.queryHandler.execute(id);
  }
}
