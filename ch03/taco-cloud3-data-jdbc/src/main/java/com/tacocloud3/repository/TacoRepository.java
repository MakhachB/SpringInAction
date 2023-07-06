package com.tacocloud3.repository;

import com.tacocloud3.model.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {

}
