package com.tacocloud3.repository;

import com.tacocloud3.model.Ingredient;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcIngredientRepository implements IngredientRepository {
    
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Iterable<Ingredient> findAll() {
        return jdbcTemplate.query(
                "select id, name, type from INGREDIENT",
                new BeanPropertyRowMapper<>(Ingredient.class)
        );
    }

    @Override
    public Optional<Ingredient> findById(String id) {
        List<Ingredient> result = jdbcTemplate.query(
                "select id, name, type from INGREDIENT where id=?",
                new BeanPropertyRowMapper<>(Ingredient.class),
                id
        );
        return result.size() == 0 ?
                Optional.empty() :
                Optional.of(result.get(0));
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbcTemplate.update(
                "insert into INGREDIENT (id, name, type) values (?, ?, ?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString());
        return ingredient;
    }


//    private Ingredient mapRowToIngredient(ResultSet row, int rowNum) throws SQLException {
//        return new Ingredient(
//                row.getString("id"),
//                row.getString("name"),
//                Ingredient.Type.valueOf(row.getString("type"))
//        );
//    }
}
