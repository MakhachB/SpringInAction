package com.messaging.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class Ingredient {

//    private static final long serialVersionID = 1L;

    @Id
    private Long id;

    private @NonNull String slug;

    private @NonNull String name;

    private @NonNull Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
