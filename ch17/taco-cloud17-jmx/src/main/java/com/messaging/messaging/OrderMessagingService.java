package com.messaging.messaging;


import com.messaging.model.TacoOrder;

public interface OrderMessagingService {
    void sendOrder(TacoOrder tacoOrder);
}
