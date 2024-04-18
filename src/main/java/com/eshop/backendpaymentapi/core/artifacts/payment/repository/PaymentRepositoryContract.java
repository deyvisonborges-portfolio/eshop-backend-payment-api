package com.eshop.backendpaymentapi.core.artifacts.payment.repository;

import com.eshop.backendpaymentapi.core.artifacts.payment.Payment;
import com.eshop.backendpaymentapi.core.artifacts.payment.PaymentSearchQuery;
import com.eshop.backendpaymentapi.lib.Pagination;
import com.eshop.backendpaymentapi.lib.domain.repository.BaseRepositoryContract;

import java.util.List;

public interface PaymentRepositoryContract extends BaseRepositoryContract<Payment> {
  void saveAll(List<Payment> payments);
  Pagination<Payment> findAll(PaymentSearchQuery query);
  List<Payment> findAll();
}
