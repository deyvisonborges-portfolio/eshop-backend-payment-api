package com.eshop.backendpaymentapi.app.persistence.payment;

import com.eshop.backendpaymentapi.core.artifacts.payment.Payment;
import com.eshop.backendpaymentapi.core.artifacts.payment.PaymentID;
import com.eshop.backendpaymentapi.core.artifacts.payment.repository.PaymentRepositoryContract;
import com.eshop.backendpaymentapi.lib.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PaymentJPARepository implements PaymentRepositoryContract {
  private final Logger logger = LoggerFactory.getLogger(PaymentJPARepository.class);
  private final PaymentJPARepositoryContract repositoryContract;

	public PaymentJPARepository(PaymentJPARepositoryContract repositoryContract) {
		this.repositoryContract = repositoryContract;
	}

	@Override
  public void save(Payment payment) {
    try {
      this.repositoryContract.save(PaymentJPAEntity.from(payment));
    } catch (Exception e) {
      this.logger.info("Repository -> Error while saving payment", e);
      throw e;
    }
  }

  @Override
  public void update(Payment payment) {
    this.repositoryContract.save(PaymentJPAEntity.from(payment));
  }

  @Override
  public void delete(String id) {
    try {
      this.findById(id);
      this.repositoryContract.deleteById(PaymentID.from(UUID.fromString(id)));
    } catch (Exception e) {
      this.logger.info(MessageFormat.format("Error while delete Payment with id: {0}", id), e);
      throw e;
    }
  }

  @Override
  public Optional<Payment> findById(String id) {
    try {
      final var paymentOpt = this.repositoryContract.findById(PaymentID.from(UUID.fromString(id)))
        .orElseThrow(
          () -> new NotFoundException(
            MessageFormat.format("Not found Payment with id: {0}", id)
          )
        );

      return Optional.of(
        Payment.factory(
          paymentOpt.getValue(),
          paymentOpt.getStatus(),
					paymentOpt.getMethod(),
          paymentOpt.getPaidIn(),
          paymentOpt.getOrderId(),
          paymentOpt.getCustomerId()
        )
      );
    } catch (Exception e) {
      this.logger.info(MessageFormat.format("Error while fetching Payment with id: {0}", id), e);
      throw e;
    }
  }
}
