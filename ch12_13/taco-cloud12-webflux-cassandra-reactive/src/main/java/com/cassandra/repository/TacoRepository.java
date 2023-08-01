package com.cassandra.repository;

import com.cassandra.model.Taco;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface TacoRepository extends ReactiveCrudRepository<Taco, UUID> {

}
