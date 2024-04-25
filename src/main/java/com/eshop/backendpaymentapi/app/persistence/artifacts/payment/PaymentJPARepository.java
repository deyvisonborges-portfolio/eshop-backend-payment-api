package com.eshop.backendpaymentapi.app.persistence.artifacts.payment;

import com.eshop.backendpaymentapi.app.persistence.utils.SpecificationUtils;
import com.eshop.backendpaymentapi.core.artifacts.payment.Payment;
import com.eshop.backendpaymentapi.core.artifacts.payment.PaymentSearchQuery;
import com.eshop.backendpaymentapi.core.artifacts.payment.repository.PaymentRepositoryContract;
import com.eshop.backendpaymentapi.lib.Pagination;
import com.eshop.backendpaymentapi.lib.exception.InternalErrorException;
import com.eshop.backendpaymentapi.lib.exception.NotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PaymentJPARepository implements PaymentRepositoryContract {
  private final PaymentJPARepositoryContract repositoryContract;

	public PaymentJPARepository(PaymentJPARepositoryContract repositoryContract) {
		this.repositoryContract = repositoryContract;
	}

	@Override
  public Payment save(Payment payment) {
    try {
      final var jpaPayment = this.repositoryContract.save(PaymentJPAEntity.from(payment));
      return PaymentJPAEntity.toAggregate(jpaPayment);
    } catch (Exception e) {
      throw new InternalErrorException(e.getMessage());
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
      this.repositoryContract.deleteById(id);
    } catch (Exception e) {
      throw new InternalErrorException(e.getMessage());
    }
  }

  @Override
  public Optional<Payment> findById(String id) {
    try {
      final var paymentOpt = this.repositoryContract.findById(id)
        .orElseThrow(
          () -> new NotFoundException(
            MessageFormat.format("Not found Payment with id: {0}", id)
          )
        );
      return Optional.of(PaymentJPAEntity.toAggregate(paymentOpt));
    } catch (Exception e) {
      throw new InternalErrorException(e.getMessage());
    }
  }

  @Override
  public void saveAll(List<Payment> payments) {
    try {
      final var entities = payments.stream()
      .map(PaymentJPAEntity::from)
      .collect(Collectors.toList());
    this.repositoryContract.saveAll(entities);
    } catch (Exception e) {
      throw new InternalErrorException(e.getMessage());
    }

  }

  @Override
  public Pagination<Payment> findAll(final PaymentSearchQuery query) {
    try {
      /*
    * Pagination
    * */
    final var page = PageRequest.of(
      query.page(),
      query.perPage(),
      Sort.by(Sort.Direction.fromString(String.valueOf(query.direction())), query.sort())
    );

    /*
    * Dynamic Query
    * */
    final var specifications = Optional.ofNullable(query.terms())
      .filter(str -> str.isBlank())
      .map(str ->
        SpecificationUtils.<PaymentJPAEntity>like("value", str)
          .or(SpecificationUtils.like("status", str))
          .or(SpecificationUtils.like("method", str))
      )
      .orElseGet(null);

    final var pageResult = this.repositoryContract.findAll(Specification.where(specifications), page);

    return new Pagination<>(
      pageResult.getNumber(),
      pageResult.getSize(),
      pageResult.getTotalElements(),
      pageResult.map(PaymentJPAEntity::toAggregate).toList()
    );
    } catch (Exception e) {
      throw new InternalErrorException(e.getMessage());
    }
  }

  @Override
  public List<Payment> findAll() {
    try {
      return this.repositoryContract.findAll().stream().map(PaymentJPAEntity::toAggregate).toList();
    } catch (Exception e) {
      throw new InternalErrorException(e.getMessage());
    }
  }
}
