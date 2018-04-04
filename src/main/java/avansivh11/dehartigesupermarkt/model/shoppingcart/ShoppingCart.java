package avansivh11.dehartigesupermarkt.model.shoppingcart;

import avansivh11.dehartigesupermarkt.model.account.User;
import avansivh11.dehartigesupermarkt.model.order.Order;
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

    //copy constructor
    public ShoppingCart(ShoppingCart shoppingCart) {
        this.orderLines = new HashMap<>();
        for(String name : shoppingCart.getOrderLines().keySet()) {
            this.orderLines.put(name, new OrderLine(shoppingCart.getOrderLines().get(name)));
        }
    }

    //returns the newly created orderline, or the updated orderline
    public OrderLine add(Product product, int amount) {
        if(product == null) { throw new java.lang.IllegalArgumentException(); }
        assert amount > 0;
        //check if the current product is already in an orderline (check by name)
        if(orderLines.get(product.getName()) == null) {
            //invert amount - product stock needs to go down
            int amountChange = amount*-1;
            updateProductStock(product, amountChange);

            //construct the orderline
            OrderLine orderLine = new OrderLine(product, amount);
            orderLines.put(product.getName(), orderLine);
            return orderLine;
        } else {
            OrderLine targetOrderLine = orderLines.get(product.getName());
            changeAmount(targetOrderLine, amount);
            return targetOrderLine;
        }
    }

    public OrderLine get(String key) {
        return orderLines.get(key);
    }

    public void remove(OrderLine orderLine, int amount) {
        if(orderLine == null) { throw new java.lang.IllegalArgumentException(); }
        assert amount > 0;
        if(orderLine.getAmount() - amount <= 0) {
            //remove the entire orderline from the map
            orderLines.remove(orderLine.getProduct().getName());
        } else {
            amount = amount*-1;
            assert amount < 0;
            changeAmount(orderLine, amount);
        }
    }

    public void changeAmount(OrderLine orderLine, int change) {
        //if the change is positive it affects the stock negatively - so invert
        int invertChange = change * -1;
        updateProductStock(orderLine.getProduct(), invertChange);
        //now update the amount of the orderline
        orderLine.setAmount(orderLine.getAmount() + change);
        orderLine.setTotalPrice(orderLine.getAmount()*orderLine.getProduct().getPrice());
    }

    private void updateProductStock(Product product, int change) {
        product.setStock(product.getStock() + change);
        assert product.getStock() >= 0;
    }
}


