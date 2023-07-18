package com.tacocloud8rest.repository;

import com.tacocloud8rest.model.Taco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TacoRepository extends JpaRepository<Taco, Long> {

}
