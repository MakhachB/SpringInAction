package com.tacocloud3.repository;

import com.tacocloud3.model.TacoOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, String> {
    List<TacoOrder> findByDeliveryZip(String deliveryZip);

    List<TacoOrder> readByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDt, Date endDt);

    List<TacoOrder> findByDeliveryCityOrderByDeliveryCity(String deliveryCity);

//    @Query("from TacoOrder o where o.deliveryCity='Seattle'")
//    List<TacoOrder> readOrdersDeliveredInSeattle();
}
