package com.tacocloud3.controller;

import com.tacocloud3.model.TacoOrder;
import com.tacocloud3.model.udt.utils.IngredientUDTUtils;
import com.tacocloud3.model.udt.utils.TacoUDTUtils;
import com.tacocloud3.repository.OrderRepository;
import com.tacocloud3.repository.TacoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;

    private final TacoRepository tacoRepository;

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors,
                               SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "orderForm";
        }

        TacoOrder saved = orderRepository.save(order);
        tacoRepository.saveAll(TacoUDTUtils.toTacos(saved.getTacos()));

        System.out.println(saved);
        log.info("Order submitted: {}", order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}