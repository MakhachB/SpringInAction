package com.cassandra.messaging;


import com.cassandra.model.TacoOrder;

public interface OrderMessagingService {
    void sendOrder(TacoOrder tacoOrder);
}
