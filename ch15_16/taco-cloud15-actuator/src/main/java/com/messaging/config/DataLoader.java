package com.messaging.config;

import com.messaging.model.Ingredient;
import com.messaging.model.Taco;
import com.messaging.repository.IngredientRepository;
import com.messaging.repository.TacoRepository;
import com.messaging.repository.UserRepository;
import com.messaging.security.Role;
import com.messaging.security.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

import static com.messaging.model.Ingredient.Type;

//@Profile("!prod")
@Configuration
@Slf4j
public class DataLoader {

    @Value("${greeting.welcome}")
    private String greeting;

    @Bean
    public CommandLineRunner RepoDataLoader(
            IngredientRepository ingRepo,
            UserRepository userRepo,
            PasswordEncoder encoder,
            TacoRepository tacoRepo) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                System.out.println(greeting);

                ingRepo.deleteAll().subscribe();
                userRepo.deleteAll().subscribe();
                tacoRepo.deleteAll().subscribe();

                Ingredient flourTortilla = saveAnIngredient("FLTO", "Flour Tortilla", Type.WRAP);
                Ingredient cornTortilla = saveAnIngredient("COTO", "Corn Tortilla", Type.WRAP);
                Ingredient groundBeef = saveAnIngredient("GRBF", "Ground Beef", Type.PROTEIN);
                Ingredient carnitas = saveAnIngredient("CARN", "Carnitas", Type.PROTEIN);
                Ingredient tomatoes = saveAnIngredient("TMTO", "Diced Tomatoes", Type.VEGGIES);
                Ingredient lettuce = saveAnIngredient("LETC", "Lettuce", Type.VEGGIES);
                Ingredient cheddar = saveAnIngredient("CHED", "Cheddar", Type.CHEESE);
                Ingredient jack = saveAnIngredient("JACK", "Monterrey Jack", Type.CHEESE);
                Ingredient salsa = saveAnIngredient("SLSA", "Salsa", Type.SAUCE);
                Ingredient sourCream = saveAnIngredient("SRCR", "Sour Cream", Type.SAUCE);

                userRepo.save(new User(null, "habuma", encoder.encode("password"),
                        "Craig Walls", "123 North Street", "Cross Roads", "TX",
                        "76227", "123-123-1234", "mail1", Role.ROLE_ADMIN)).subscribe();
                userRepo.save(new User(null, "test", encoder.encode("12345"), "ffasdfasf", "fksdf",
                        "fksld", "fskldfj", "fsdf", "1231313", "mail3", Role.ROLE_USER)).subscribe();
                userRepo.save(new User(null, "test2", encoder.encode("12345"), "ffasdfasf", "fksdf",
                        "fksld", "fskldfj", "fsdf", "1231313", "mail3", Role.ROLE_ADMIN)).subscribe();

                Taco taco1 = new Taco();
                taco1.setId("TACO1");
                taco1.setName("Carnivore");
                taco1.setIngredients(Arrays.asList(flourTortilla, groundBeef, carnitas, sourCream, salsa, cheddar));
                tacoRepo.save(taco1).subscribe();

                Taco taco2 = new Taco();
                taco2.setId("TACO2");
                taco2.setName("Bovine Bounty");
                taco2.setIngredients(Arrays.asList(cornTortilla, groundBeef, cheddar, jack, sourCream));
                tacoRepo.save(taco2).subscribe();

                Taco taco3 = new Taco();
                taco3.setId("TACO3");
                taco3.setName("Veg-Out");
                taco3.setIngredients(Arrays.asList(flourTortilla, cornTortilla, tomatoes, lettuce, salsa));
                tacoRepo.save(taco3).subscribe();
                log.info("Repositories was filled");
            }

            private Ingredient saveAnIngredient(String id, String name, Type type) {
                Ingredient ingredient = new Ingredient(id, name, type);
                ingRepo.save(ingredient).subscribe();
                return ingredient;
            }
        };
    }

//    @Bean
//    @Profile("!prod")
//    public ApplicationRunner userLoader(UserRepository repo, PasswordEncoder encoder) {
//        System.out.println(greeting);
//        log.info("User repo will be filled");
//        return args -> {
//            repo.deleteAll(); // TODO: Quick hack to avoid tests from stepping on each other with constraint violations
//            repo.save(new User(null, "test", encoder.encode("12345"), "ffasdfasf", "fksdf",
//                    "fksld", "fskldfj", "fsdf", "1231313", Role.ROLE_USER));
//            repo.save(new User(null, "test2", encoder.encode("12345"), "ffasdfasf", "fksdf",
//                    "fksld", "fskldfj", "fsdf", "1231313", Role.ROLE_ADMIN));
//        };
//    }
//
//    @Bean
//    public ApplicationRunner ingredientLoader(IngredientRepository repo) {
//        log.info("Ingredient repo will be filled");
//        return args -> {
//            repo.deleteAll();
//            repo.save(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
//            repo.save(new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP));
//            repo.save(new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
//            repo.save(new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN));
//            repo.save(new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES));
//            repo.save(new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES));
//            repo.save(new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));
//            repo.save(new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE));
//            repo.save(new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE));
//            repo.save(new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE));
//        };
//    }
//
}
