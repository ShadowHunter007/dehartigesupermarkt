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
public class FastShippingOrder extends DecoratedOrder {
    private double lowWeightPrice = 5.00;
    private double midWeightPrice = 8.00;
    private double highWeightPrice = 12.50;

    public FastShippingOrder(BaseOrder order) {
        super(order);
        double newPrice = calculateTotalPrice();
        setTotalPrice(newPrice);
    }

    @Override
    public double calculateTotalPrice() {
        int orderWeightClass = order.getWeightClass();
        double initialPrice = order.getTotalPrice();
        double newPrice;

        if(orderWeightClass == 1) {
            newPrice = initialPrice + lowWeightPrice;
            return newPrice;
        } else if(orderWeightClass == 2) {
            newPrice = initialPrice + midWeightPrice;
            return newPrice;
        } else if(orderWeightClass == 3) {
            newPrice = initialPrice + highWeightPrice;
            return newPrice;
        } else {
            throw new IllegalArgumentException();
        }
    }
}

