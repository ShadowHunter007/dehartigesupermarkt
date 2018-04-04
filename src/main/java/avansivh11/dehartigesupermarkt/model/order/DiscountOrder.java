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

    public DiscountOrder(BaseOrder order) {
        super(order);
        setTotalPrice(calculateTotalPrice());
    }

    @Override
    public double calculateTotalPrice() {
        double initialPrice = order.getTotalPrice();
        double newPrice = initialPrice - ((initialPrice/100)*discountPercentage);
        return newPrice;
    }
}
