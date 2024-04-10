package com.eshop.backendpaymentapi.app.persistence.payment;

import com.eshop.backendpaymentapi.core.artifacts.payment.Payment;
import com.eshop.backendpaymentapi.core.artifacts.payment.repository.PaymentRepositoryContract;
import com.eshop.backendpaymentapi.lib.domain.Identifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentJPARepository implements PaymentRepositoryContract {
  private final PaymentJPARepository repository;

	public PaymentJPARepository(PaymentJPARepository repository) {
		this.repository = repository;
	}

	@Override
  public void save(Payment payment) {
    this.repository.save(payment);
  }

  @Override
  public void update(Payment payment) {
    this.repository.save(payment);
  }

  @Override
  public void delete(Identifier id) {
    final var payment = this.repository.findById(id);
    this.repository.delete(payment.get().getId());
  }

  @Override
  public Optional<Payment> findById(Identifier id) {
    return Optional.empty();
  }
}
