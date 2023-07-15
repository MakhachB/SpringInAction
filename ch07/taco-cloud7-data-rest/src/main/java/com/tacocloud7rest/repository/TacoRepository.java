package com.tacocloud7rest.repository;

import com.tacocloud7rest.model.Taco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(rel = "tacos", path = "tacos")
public interface TacoRepository extends JpaRepository<Taco, Long> {

}
