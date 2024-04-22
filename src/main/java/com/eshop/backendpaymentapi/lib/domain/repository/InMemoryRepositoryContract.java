package com.eshop.backendpaymentapi.lib.domain.repository;

import com.fasterxml.jackson.core.JsonProcessingException;

@SuppressWarnings("rawtypes")
public interface InMemoryRepositoryContract extends BaseRepositoryContract {
  <T> T save(String key, T value) throws JsonProcessingException;
}
