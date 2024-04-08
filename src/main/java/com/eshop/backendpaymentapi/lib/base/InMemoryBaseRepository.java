package com.mach.machorderrestapi.common.base;

import java.util.ArrayList;
import java.util.List;

public class InMemoryBaseRepository<M> {
    public final List<M> items = new ArrayList<>();

    public M createOne(M entity) {
        this.items.add(entity);
        return entity;
    }

    public List<M> findAll() {
        return this.items;
    }
}
