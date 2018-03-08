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
    private double lowWeightPrice = 12.50;
    private double midWeightPrice = 22.50;
    private double highWeightPrice = 50.00;

    public FastShippingOrder(BaseOrder order) {
        super(order);
        calculateTotalPrice();
    }

    @Override
    public double calculateTotalPrice() {
        int orderWeightClass = order.getWeightClass();
        double initialPrice = order.getTotalPrice();
        double newPrice;

        if(orderWeightClass == 1) {
            newPrice = initialPrice + lowWeightPrice;
            this.setTotalPrice(newPrice);
            return newPrice;
        } else if(orderWeightClass == 2) {
            newPrice = initialPrice + midWeightPrice;
            this.setTotalPrice(newPrice);
            return newPrice;
        } else if(orderWeightClass == 3) {
            newPrice = initialPrice + highWeightPrice;
            this.setTotalPrice(newPrice);
            return newPrice;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
    /*
    idea for handling this order option:
    when a set of oders is set from state "received" to state "readyToSend". Orders from this type are always put at the front of the queue.
    This is well combined with the subject "algoritms and datastructures" (in the sense of a sorting algoritm)
     */
