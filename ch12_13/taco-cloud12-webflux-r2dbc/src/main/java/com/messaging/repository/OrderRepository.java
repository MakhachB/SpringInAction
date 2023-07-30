package com.messaging.repository;

import com.messaging.model.TacoOrder;
import com.messaging.security.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface OrderRepository extends ReactiveCrudRepository<TacoOrder, Long> {

}
