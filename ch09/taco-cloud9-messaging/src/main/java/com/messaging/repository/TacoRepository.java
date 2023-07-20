package com.messaging.repository;

import com.messaging.model.Taco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TacoRepository extends JpaRepository<Taco, Long> {

}
