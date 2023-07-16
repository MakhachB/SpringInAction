package com.tacocloud7rest.repository;

import com.tacocloud7rest.model.Taco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TacoRepository extends JpaRepository<Taco, Long> {

}
