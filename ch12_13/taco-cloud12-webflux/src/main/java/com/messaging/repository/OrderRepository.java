package com.messaging.repository;

import com.messaging.model.TacoOrder;
import com.messaging.security.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<TacoOrder, Long> {
    List<TacoOrder> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
