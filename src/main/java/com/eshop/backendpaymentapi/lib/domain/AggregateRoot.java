package com.eshop.backendpaymentapi.lib.domain;

import java.time.Instant;

public abstract class AggregateRoot<ID extends Identifier> extends Entity<ID>{
	protected AggregateRoot(
    final ID id,
    final Boolean active,
    final Instant createdAt,
    final Instant updatedAt

  ) {
		super(id, active, createdAt, updatedAt);
	}
}

