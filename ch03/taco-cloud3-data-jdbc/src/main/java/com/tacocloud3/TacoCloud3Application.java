package com.tacocloud3;

import com.tacocloud3.model.Ingredient;
import com.tacocloud3.repository.IngredientRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

import static com.tacocloud3.model.Ingredient.*;

@SpringBootApplication
public class TacoCloud3Application {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloud3Application.class, args);
	}

	@Bean
	public ApplicationRunner dataLoader(IngredientRepository repo) {
		System.out.println("Ingredient repo will be filled");
		return args -> {
			args.getNonOptionArgs().forEach(System.out::println);
			System.out.println("---------------");
			Arrays.stream(args.getSourceArgs()).forEach(System.out::println);
			System.out.println("---------------");
			args.getOptionNames().forEach(System.out::println);
			System.out.println("---------------");
			System.out.println(args.getOptionValues("version"));
			repo.deleteAll(); // TODO: Quick hack to avoid tests from stepping on each other with constraint violations
			repo.save(new Ingredient("БУЛК", "Булка", Type.WRAP));
			repo.save(new Ingredient("ЛАВШ", "Лаваш", Type.WRAP));
			repo.save(new Ingredient("ТАНД", "Тандыр", Type.WRAP));
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
