package tacos.kitchen.messaging;

import tacos.kitchen.model.TacoOrder;

public interface OrderReceiver {

  TacoOrder receiveOrder();

}