package avansivh11.dehartigesupermarkt.model.shoppingcart;

import avansivh11.dehartigesupermarkt.model.order.OrderLine;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ShoppingCart {
    private ArrayList<OrderLine> orderLines;
}

