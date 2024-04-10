package com.eshop.backendpaymentapi.app.persistence.payment;

import com.eshop.backendpaymentapi.core.artifacts.payment.PaymentID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentJPARepositoryContract extends JpaRepository<PaymentJPAEntity, PaymentID> { }
