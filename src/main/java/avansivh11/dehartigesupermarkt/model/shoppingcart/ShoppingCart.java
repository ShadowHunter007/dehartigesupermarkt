package avansivh11.dehartigesupermarkt.model.order;

import avansivh11.dehartigesupermarkt.model.account.Customer;
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
public class Order extends BaseOrder {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    protected List<OrderLine> orderLines = new ArrayList<>();

    private Customer customer;

    private OrderState state;

    public Order(Customer customer, OrderState state) {
        this.customer = customer;
        this.state = state;
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
