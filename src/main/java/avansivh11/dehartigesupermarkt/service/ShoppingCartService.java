package avansivh11.dehartigesupermarkt.service;

import avansivh11.dehartigesupermarkt.model.shoppingcart.Caretaker;
import avansivh11.dehartigesupermarkt.model.shoppingcart.Memento;
import avansivh11.dehartigesupermarkt.model.shoppingcart.Originator;
import avansivh11.dehartigesupermarkt.model.shoppingcart.ShoppingCart;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
@ToString
public class ShoppingCartService {
    private final ShoppingCart shoppingCart;
    private final Caretaker caretaker;
    private final Originator originator;

    public ShoppingCartService() {
        //instantiate new final shoppingcart
        ShoppingCart shoppingCart = new ShoppingCart();
        this.shoppingCart = shoppingCart;
        this.caretaker = new Caretaker();
        this.originator = new Originator();
    }


    public void updateMemento(ShoppingCart shoppingCart) {
        originator.setShoppingCartState(shoppingCart);
        caretaker.addMementoAtTail(originator.save());
    }
}
