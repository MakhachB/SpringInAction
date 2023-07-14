package com.tacocloud3.config;

import com.tacocloud3.model.Ingredient;
import com.tacocloud3.model.Role;
import com.tacocloud3.model.User;
import com.tacocloud3.repository.IngredientRepository;
import com.tacocloud3.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Profile("!prod")
@Configuration
@Slf4j
public class DataLoader {

    @Value("${greeting.welcome}")
    private String greeting;

    @Bean
    @Profile("!prod")
    public ApplicationRunner userLoader(UserRepository repo, PasswordEncoder encoder) {
        System.out.println(greeting);
        log.info("User repo will be filled");
        return args -> {
            repo.deleteAll(); // TODO: Quick hack to avoid tests from stepping on each other with constraint violations
            repo.save(new User(null, "test", encoder.encode("12345"), "ffasdfasf", "fksdf",
                    "fksld", "fskldfj", "fsdf", "1231313", Role.ROLE_USER));
            repo.save(new User(null, "test2", encoder.encode("12345"), "ffasdfasf", "fksdf",
                    "fksld", "fskldfj", "fsdf", "1231313", Role.ROLE_ADMIN));
        };
    }

    @Bean
    public ApplicationRunner ingredientLoader(IngredientRepository repo) {
        log.info("Ingredient repo will be filled");
        return args -> {
            repo.deleteAll();
            repo.save(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
            repo.save(new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP));
            repo.save(new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
            repo.save(new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN));
            repo.save(new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES));
            repo.save(new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES));
            repo.save(new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));
            repo.save(new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE));
            repo.save(new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE));
            repo.save(new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE));
        };
    }



}
