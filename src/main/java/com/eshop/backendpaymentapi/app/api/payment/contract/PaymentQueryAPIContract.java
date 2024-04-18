package com.eshop.backendpaymentapi.app.api.payment.contract;

import com.eshop.backendpaymentapi.lib.Pagination;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/payments")
public interface PaymentQueryAPIContract {
  @RequestMapping
  Pagination<?> listPayments(
    @RequestParam(name = "search", required = false, defaultValue = "") final String search,
    @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
    @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
    @RequestParam(name = "sort", required = false, defaultValue = "status") final String sort,
    @RequestParam(name = "direction", required = false, defaultValue = "ascendant") final String direction
  );
}
