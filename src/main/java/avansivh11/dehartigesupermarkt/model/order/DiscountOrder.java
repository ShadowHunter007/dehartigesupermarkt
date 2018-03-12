package avansivh11.dehartigesupermarkt.model.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class DiscountOrder extends DecoratedOrder {
    private double discountPercentage = 10;

    public DiscountOrder(String name, double price, BaseOrder order) {
        super(order);
    }

    @Override
    public double calculateTotalPrice() {
        double newPrice = 100 - ((discountPercentage * order.getTotalPrice())/100);
        return 0;
    }
}
