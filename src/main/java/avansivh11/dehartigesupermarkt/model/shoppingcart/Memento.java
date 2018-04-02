package avansivh11.dehartigesupermarkt.model.shoppingcart;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Memento {
    private ShoppingCart shoppingCartState;

    public Memento(ShoppingCart shoppingCartState) {
        this.shoppingCartState = shoppingCartState;
    }


}
