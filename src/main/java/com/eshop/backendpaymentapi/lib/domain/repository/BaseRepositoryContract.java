package com.eshop.backendpaymentapi.lib.domain.repository;

import com.eshop.backendpaymentapi.lib.domain.Identifier;

import java.util.Optional;

public interface BaseRepositoryContract<E> {
  void save(E e);
  void update(E e);
  void delete(Identifier id);
  Optional<E> findById(Identifier id);
}
