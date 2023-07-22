package tacos.kitchen.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import tacos.kitchen.model.TacoOrder;
import tacos.kitchen.messaging.OrderReceiver;

import java.time.LocalDateTime;

@Profile({"jms-template", "rabbit"})
@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderReceiverController {

  private final OrderReceiver orderReceiver;

  @GetMapping("/receive")
  public String receiveOrder(Model model) {
    TacoOrder order = orderReceiver.receiveOrder();
    System.out.println(order + " : " + LocalDateTime.now());
    if (order != null) {
      model.addAttribute("order", order);
      return "receiveOrder";
    }
    return "noOrder";
  }


}
