package avansivh11.dehartigesupermarkt.model.shoppingcart;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Originator {
    private ShoppingCart shoppingCartState;

    public Memento save() {
       return new Memento(new ShoppingCart(shoppingCartState));
    }

    public void restore(Memento m) {
        shoppingCartState = m.getShoppingCartState();
    }
}
