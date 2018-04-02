package avansivh11.dehartigesupermarkt.model.shoppingcart;

import avansivh11.dehartigesupermarkt.model.account.User;
import avansivh11.dehartigesupermarkt.model.order.OrderLine;
import avansivh11.dehartigesupermarkt.model.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ShoppingCart {
    private HashMap<String, OrderLine> orderLines = new HashMap<>();
    private User customer;

    public void add(Product product, int amount) {
        if(product == null) { throw new java.lang.IllegalArgumentException(); }
        assert amount > 0;
        //check if the current product is already in an orderline (check by name)
        if(orderLines.get(product.getName()) == null) {
            OrderLine orderLine = new OrderLine(product, amount);
            orderLines.put(product.getName(), orderLine);
            //invert amount - product stock needs to go down
            amount = amount*-1;
            updateProductStock(product, amount);
        } else {
            OrderLine targetOrderLine = orderLines.get(product.getName());
            changeAmount(targetOrderLine, amount);
        }
    }

    public OrderLine get(String key) {
        return orderLines.get(key);
    }

    public void remove(Product product, int amount) {
        if(product == null) { throw new java.lang.IllegalArgumentException(); }
        assert amount > 0;
        //convert the amount to a negative
        OrderLine orderLine = orderLines.get(product.getName());
        if(orderLine.getAmount() - amount <= 0) {
            //remove the entire orderline from the map
            orderLines.remove(product.getName());
        } else {
            amount = amount*-1;
            changeAmount(orderLine, amount);
        }
    }

    private void changeAmount(OrderLine orderLine, int change) {
        orderLine.setAmount(orderLine.getAmount() + change);
        //if the change is positive it affects the stock negatively - so invert
        change = change*-1;
        updateProductStock(orderLine.getProduct(), change);
    }

    private void updateProductStock(Product product, int change) {
        product.setStock(product.getStock() + change);
    }
}


