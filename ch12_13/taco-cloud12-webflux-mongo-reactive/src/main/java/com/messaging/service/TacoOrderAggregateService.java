package com.messaging.service;

import com.messaging.model.Taco;
import com.messaging.model.TacoOrder;
import com.messaging.repository.OrderRepository;
import com.messaging.repository.TacoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TacoOrderAggregateService {

    private final OrderRepository orderRepo;
    private final TacoRepository tacoRepo;

    public Mono<TacoOrder> save(TacoOrder tacoOrder) {
        return Mono.just(tacoOrder)
                .flatMap(order -> {
//                    TODO проверить потоки
                    List<Taco> tacos = order.getTacos();
                    order.setTacos(new ArrayList<>());
                    return tacoRepo.saveAll(tacos)
                            .map(taco -> {
                                order.addTaco(taco);
                                return order;
                            })
                            .last();
                })
                .flatMap(orderRepo::save);
    }

    public Mono<TacoOrder> findById(Long id) {
        return orderRepo
                .findById(id)
                .flatMap(order -> tacoRepo
                        .findAllById(order.getTacoIds())
                        .map(taco -> {
                            order.addTaco(taco);
                            return order;
                        }).last());
    }
}








