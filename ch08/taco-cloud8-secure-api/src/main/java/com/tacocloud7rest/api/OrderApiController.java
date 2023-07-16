package com.tacocloud7rest.api;

import com.tacocloud7rest.model.TacoOrder;
import com.tacocloud7rest.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/orders", produces = "application/json")
@CrossOrigin(origins = {"http://localhost:3000", "http://tacocloudTest.com"})
@RequiredArgsConstructor
public class OrderApiController {
    public static final int RESENT_ORDERS_SIZE = 5;
    private final OrderRepository repo;

    @GetMapping(params = "recent")
    public List<TacoOrder> recentOrders() {
        PageRequest page = PageRequest.of(0, RESENT_ORDERS_SIZE, Sort.by("placedAt"));
        return repo.findAll(page).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TacoOrder> orderById(@PathVariable Long id) {
        Optional<TacoOrder> order = repo.findById(id);
        if (order.isPresent()) {
            return new ResponseEntity<>(order.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public TacoOrder putOrder(@PathVariable Long id,
                              @RequestBody TacoOrder tacoOrder) {
        tacoOrder.setId(id);
        return repo.save(tacoOrder);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json")
    public TacoOrder patchOrder(@PathVariable Long id,
                                @RequestBody TacoOrder patch) {
        TacoOrder order = repo.findById(id).get();
        if (patch.getDeliveryName() != null) order.setDeliveryName(patch.getDeliveryName());
        if (patch.getDeliveryStreet() != null) order.setDeliveryStreet(patch.getDeliveryStreet());
        if (patch.getDeliveryCity() != null) order.setDeliveryCity(patch.getDeliveryCity());
        if (patch.getDeliveryState() != null) order.setDeliveryState(patch.getDeliveryState());
        if (patch.getDeliveryZip() != null) order.setDeliveryZip(patch.getDeliveryZip());
        if (patch.getCcNumber() != null) order.setCcNumber(patch.getCcNumber());
        if (patch.getCcExpiration() != null) order.setCcExpiration(patch.getCcExpiration());
        if (patch.getCcCVV() != null) order.setCcCVV(patch.getCcCVV());
        return repo.save(order);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long id) {
        try {
            repo.deleteById(id);
        } catch (EmptyResultDataAccessException ignored) {}
    }
}
