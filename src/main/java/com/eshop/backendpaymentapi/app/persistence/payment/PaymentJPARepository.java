package com.eshop.backendpaymentapi.app.persistence.payment;

import com.eshop.backendpaymentapi.core.artifacts.payment.Payment;
import com.eshop.backendpaymentapi.core.artifacts.payment.repository.PaymentRepositoryContract;
import com.eshop.backendpaymentapi.lib.domain.Identifier;
import com.eshop.backendpaymentapi.lib.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Optional;

@Service
public class PaymentJPARepository implements PaymentRepositoryContract {
  private final Logger logger = LoggerFactory.getLogger(PaymentJPARepository.class);
  private final PaymentJPARepository repository;

	public PaymentJPARepository(PaymentJPARepository repository) {
		this.repository = repository;
	}

	@Override
  public void save(Payment payment) {
    try {
      this.repository.save(payment);
    } catch (Exception e) {
      this.logger.info("Repository -> Error while saving payment", e);
      throw e;
    }
  }

  @Override
  public void update(Payment payment) {
    this.repository.save(payment);
  }

  @Override
  public void delete(Identifier id) {
    try {
      final var payment = this.repository.findById(id);
      this.repository.delete(payment.get().getId());
    } catch (Exception e) {
      this.logger.info(MessageFormat.format("Error while delete Payment with id: {0}", id), e);
      throw e;
    }
  }

  @Override
  public Optional<Payment> findById(Identifier id) {
    try {
      return Optional.ofNullable(this.repository.findById(id)
        .orElseThrow(() -> new NotFoundException(MessageFormat.format("Not found Payment with id: {0}", id))));
    } catch (Exception e) {
      this.logger.info(MessageFormat.format("Error while fetching Payment with id: {0}", id), e);
      throw e;
    }
  }
}
