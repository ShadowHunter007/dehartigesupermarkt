package avansivh11.dehartigesupermarkt.model.order;

import avansivh11.dehartigesupermarkt.model.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name="Orders")
@Entity
@Getter
@NoArgsConstructor
public class Order extends BaseOrder {

    private static final Logger logger = LoggerFactory.getLogger(Order.class);

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    protected List<Product> orderItems = new ArrayList<>();

    public void add(Product p) {
        orderItems.add(p);
    }

    @Override
    public int price() {
        int price = 0;
        for(Product item : orderItems) {
            price += item.getPrice();
        }
        return price;
    }

    @Override
    public String toString() {
        String s = "";
        for (Product item : orderItems) {
            s += "product: " + item.getName() + "; ";
        }
        return s;
    }
}
