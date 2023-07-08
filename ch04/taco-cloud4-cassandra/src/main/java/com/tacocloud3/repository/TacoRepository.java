package com.tacocloud3.repository;

import com.tacocloud3.model.Taco;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TacoRepository extends CrudRepository<Taco, UUID> {

}
