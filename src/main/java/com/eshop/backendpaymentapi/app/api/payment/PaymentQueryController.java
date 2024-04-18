package com.eshop.backendpaymentapi.app.api.payment;

import com.eshop.backendpaymentapi.app.api.payment.contract.PaymentQueryAPIContract;
import com.eshop.backendpaymentapi.lib.Pagination;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentQueryController implements PaymentQueryAPIContract {
  @Override
  public Pagination<?> listPayments(String search, int page, int perPage, String sort, String direction) {
    return null;
  }
}
