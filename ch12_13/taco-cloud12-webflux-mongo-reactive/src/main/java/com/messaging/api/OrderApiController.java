package com.messaging.api;

import com.messaging.messaging.OrderMessagingService;
import com.messaging.model.TacoOrder;
import com.messaging.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/orders", produces = "application/json")
@CrossOrigin(origins = {"http://localhost:3000", "http://tacocloudTest.com"})
@RequiredArgsConstructor
public class OrderApiController {

    public static final int RESENT_ORDERS_SIZE = 5;

    private final OrderRepository repo;

    private final OrderMessagingService messagingService;


    @GetMapping(params = "recent")
    public Flux<TacoOrder> recentOrders() {
//        PageRequest page = PageRequest.of(0, RESENT_ORDERS_SIZE, Sort.by("placedAt"));
        return repo.findAll().take(RESENT_ORDERS_SIZE).take(12);
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public Mono<TacoOrder> putOrder(@PathVariable String id,
                                    @RequestBody Mono<TacoOrder> tacoOrder) {
        return tacoOrder
                .flatMap(to -> {
                    to.setId(id);
                    return Mono.just(to);
                })
                .flatMap(repo::save);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json")
    public Mono<TacoOrder> patchOrder(@PathVariable String id,
                                      @RequestBody TacoOrder patch) {
        return repo.findById(id).map(order -> {
            if (patch.getDeliveryName() != null) order.setDeliveryName(patch.getDeliveryName());
            if (patch.getDeliveryStreet() != null) order.setDeliveryStreet(patch.getDeliveryStreet());
            if (patch.getDeliveryCity() != null) order.setDeliveryCity(patch.getDeliveryCity());
            if (patch.getDeliveryState() != null) order.setDeliveryState(patch.getDeliveryState());
            if (patch.getDeliveryZip() != null) order.setDeliveryZip(patch.getDeliveryZip());
            if (patch.getCcNumber() != null) order.setCcNumber(patch.getCcNumber());
            if (patch.getCcExpiration() != null) order.setCcExpiration(patch.getCcExpiration());
            if (patch.getCcCVV() != null) order.setCcCVV(patch.getCcCVV());
            return order;
        }).flatMap(repo::save);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<TacoOrder> postOrder(@RequestBody TacoOrder order) {
//        messagingService.sendOrder(order);
        return repo.save(order);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable String id) {
        try {
            repo.deleteById(id).subscribe();
        } catch (EmptyResultDataAccessException ignored) {
        }
    }
}
