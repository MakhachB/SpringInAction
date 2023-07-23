package tacos.email;

import lombok.Data;
import tacos.email.model.Taco;

import java.util.ArrayList;
import java.util.List;

@Data
public class EmailOrder {

    private final String email;
    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco) {
        tacos.add(taco);
    }
}
