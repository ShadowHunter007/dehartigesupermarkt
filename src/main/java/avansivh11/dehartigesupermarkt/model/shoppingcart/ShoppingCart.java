package avansivh11.dehartigesupermarkt.model.shoppingcart;

import avansivh11.dehartigesupermarkt.model.account.Customer;
import avansivh11.dehartigesupermarkt.model.order.BaseOrder;
import avansivh11.dehartigesupermarkt.model.order.OrderLine;
import avansivh11.dehartigesupermarkt.model.order.OrderState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name="Orders")
@Entity
@Getter
@NoArgsConstructor
@ToString
public class ShoppingCart extends BaseOrder {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    protected List<OrderLine> orderLines = new ArrayList<>();

    private Customer customer;

    public ShoppingCart(Customer customer) {
        this.customer = customer;
    }

    public void add(OrderLine line) {
        orderLines.add(line);
    }

    @Override
    public double price() {
        int price = 0;
        for(OrderLine line : orderLines) {
            price += line.getPrice();
        }
        return price;
    }
}
