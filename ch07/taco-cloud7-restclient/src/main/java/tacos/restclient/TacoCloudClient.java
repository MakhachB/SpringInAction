package tacos.restclient;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tacos.restclient.model.TacoOrder;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TacoCloudClient {

    public static final String RESOURCE_URI = "http://localhost:8080/api/orders";

    private final RestTemplate rest;


    public Optional<TacoOrder> getOrderById(Long id) {
        return Optional.ofNullable(rest.getForObject(RESOURCE_URI + "/{id}", TacoOrder.class, id));
    }

    public List<TacoOrder> getRecentOrders() {
        return rest.exchange(RESOURCE_URI + "?recent",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<TacoOrder>>() {
                }).getBody();
    }

    public void deleteTacoOrder(TacoOrder order) {
        rest.delete(RESOURCE_URI + "/{id}", order.getId());
    }

    public void updateOrder(TacoOrder order) {
        rest.put(RESOURCE_URI + "/{id}", order, order.getId());
    }
}
