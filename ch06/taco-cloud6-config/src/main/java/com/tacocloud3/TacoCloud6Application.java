package com.tacocloud3;

import com.tacocloud3.model.Ingredient;
import com.tacocloud3.model.Role;
import com.tacocloud3.model.User;
import com.tacocloud3.repository.IngredientRepository;
import com.tacocloud3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.tacocloud3.model.Ingredient.*;

@SpringBootApplication
public class TacoCloud6Application {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloud6Application.class, args);
	}

	@Value("${greeting.welcome}")
	private String greeting;

	@Bean
	public ApplicationRunner dataUserLoader(UserRepository repo, PasswordEncoder encoder) {
		System.out.println(greeting);

		System.out.println("User repo will be filled");
		return args -> {
			repo.deleteAll(); // TODO: Quick hack to avoid tests from stepping on each other with constraint violations
			repo.save(new User(null, "test", encoder.encode("12345"), "ffasdfasf", "fksdf",
					"fksld", "fskldfj", "fsdf", "1231313", Role.ROLE_USER));

			repo.save(new User(null, "test2", encoder.encode("12345"), "ffasdfasf", "fksdf",
					"fksld", "fskldfj", "fsdf", "1231313", Role.ROLE_ADMIN));

		};
	}

	@Bean
	public ApplicationRunner dataLoader(IngredientRepository repo) {
		System.out.println("Ingredient repo will be filled");
		return args -> {
			repo.deleteAll(); // TODO: Quick hack to avoid tests from stepping on each other with constraint violations
			repo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
			repo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
			repo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
			repo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
			repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
			repo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
			repo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
			repo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
			repo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
			repo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
		};
	}



}
