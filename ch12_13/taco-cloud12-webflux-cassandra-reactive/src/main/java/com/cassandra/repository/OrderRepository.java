package com.cassandra.repository;

import com.cassandra.model.TacoOrder;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface OrderRepository extends ReactiveCrudRepository<TacoOrder, UUID> {

}
