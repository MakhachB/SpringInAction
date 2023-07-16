package tacos.restclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import tacos.restclient.model.TacoOrder;

import java.util.List;
import java.util.Optional;

@SpringBootConfiguration
@ComponentScan
@Slf4j
public class RestExamples {

    public static void main(String[] args) {
        SpringApplication.run(RestExamples.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CommandLineRunner fetchIngredients(TacoCloudClient tacoCloudClient) {
        return args -> {
            log.info("----------------------- GET -------------------------");
            log.info("GETTING ORDER BY ID");
            log.info("Ingredient:  " + tacoCloudClient.getOrderById(1L));
            log.info("GETTING ALL INGREDIENTS");
            List<TacoOrder> tacoOrders = tacoCloudClient.getRecentOrders();
            log.info("All tacoOrders:");
            for (TacoOrder tacoOrder : tacoOrders) {
                log.info("   - " + tacoOrder);
            }
        };
    }

    @Bean
    public CommandLineRunner putAnIngredient(TacoCloudClient tacoCloudClient) {
        return args -> {
            log.info("----------------------- PUT -------------------------");
            Optional<TacoOrder> before = tacoCloudClient.getOrderById(1L);
            log.info("BEFORE:  " + before);
            before.get().setDeliveryCity("Kizlyar");
            tacoCloudClient.updateOrder(before.get());
            Optional<TacoOrder> after = tacoCloudClient.getOrderById(1L);
            log.info("AFTER:  " + after.get());
        };
    }

//    @Bean
//    public CommandLineRunner addAnIngredient(TacoCloudClient tacoCloudClient) {
//        return args -> {
//            log.info("----------------------- POST -------------------------");
//            Ingredient chix = new Ingredient("CHIX", "Shredded Chicken", Ingredient.Type.PROTEIN);
//            Ingredient chixAfter = tacoCloudClient.createIngredient(chix);
//            log.info("AFTER=1:  " + chixAfter);
//        };
//    }


    @Bean
    public CommandLineRunner deleteAnIngredient(TacoCloudClient tacoCloudClient) {
        return args -> {
            log.info("----------------------- DELETE -------------------------");
            Optional<TacoOrder> before = tacoCloudClient.getOrderById(1L);
            log.info("BEFORE:  " + before);
            tacoCloudClient.deleteTacoOrder(before.get());
            Optional<TacoOrder> after = tacoCloudClient.getOrderById(1L);
            log.info("AFTER:  is present? - " + after.isPresent());
        };
    }
}
