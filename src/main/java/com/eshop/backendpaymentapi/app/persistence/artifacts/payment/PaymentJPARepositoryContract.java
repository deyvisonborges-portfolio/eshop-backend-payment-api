package com.eshop.backendpaymentapi.app.persistence.artifacts.payment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentJPARepositoryContract extends JpaRepository<PaymentJPAEntity, String> {
  Page<PaymentJPAEntity> findAll(Specification<PaymentJPAEntity> whereClause, Pageable page);
}
