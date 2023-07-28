package com.messaging.repository;

import com.messaging.model.Taco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TacoRepository extends ReactiveCrudRepository<Taco, Long> {

}
