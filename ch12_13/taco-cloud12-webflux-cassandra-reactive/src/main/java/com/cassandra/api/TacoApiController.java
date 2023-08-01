package com.cassandra.api;

import com.cassandra.model.Taco;
import com.cassandra.repository.TacoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/tacos", produces = "application/json")
@CrossOrigin(origins = {"http://localhost:3000", "http://tacocloudTest.com"})
@RequiredArgsConstructor
public class TacoApiController {

    public static final int RESENT_TACO_SIZE = 12;
    private final TacoRepository repo;

    @GetMapping(params = "recent")
    public Flux<Taco> recentTacos() {
        return repo.findAll().take(RESENT_TACO_SIZE);
    }

    @GetMapping("/{id}")
    public Mono<Taco> tacoById(@PathVariable("id") UUID id) {
        return repo.findById(id);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Taco> postTaco(@RequestBody Mono<Taco> taco) {
//        return repo.saveAll(taco).next();
//      OR
        return taco.flatMap(repo::save);
    }
}
