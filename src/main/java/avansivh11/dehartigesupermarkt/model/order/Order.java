package avansivh11.dehartigesupermarkt.model.order;

import avansivh11.dehartigesupermarkt.model.account.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Order extends BaseOrder {
    public Order(User customer, List<OrderLine> orderLines) {
        currentState = new OrderReceived(this);
        this.customer = customer;
        this.orderLines = orderLines;
        if(orderLines != null && !orderLines.isEmpty()) {
            //calculate totalPrice
            totalPrice = calculateTotalPrice();
            //calculate weightclass
            weightClass = calculateWeightClass();
        }
    }

    @Override
    public double calculateTotalPrice() {
        double totalPrice = 0;
        for(OrderLine orderLine : orderLines) {
            totalPrice = totalPrice + orderLine.getTotalPrice();
        }
        return totalPrice;
    }

    public double getExVatTotalPrice() {
        double totalPriceExVat = 0;
        for(OrderLine orderLine : orderLines) {
            totalPriceExVat = totalPriceExVat + orderLine.getTotalPriceExVat();
        }
        return totalPriceExVat;
    }

    private int calculateWeightClass() {
        int orderLineCount = orderLines.size();
        int weightClass = 0;
        if(orderLineCount > 0 && orderLineCount < 4) {
            return weightClass = 1;
        } else if(orderLineCount >= 4 && orderLineCount < 7) {
            return weightClass = 2;
        } else if(orderLineCount <= 0) {
            throw new java.util.EmptyStackException();
        } else {
            return weightClass = 3;
        }
    }
}
