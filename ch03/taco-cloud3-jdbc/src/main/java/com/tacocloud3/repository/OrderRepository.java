package com.tacocloud3.repository;

import com.tacocloud3.model.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder order);
}
