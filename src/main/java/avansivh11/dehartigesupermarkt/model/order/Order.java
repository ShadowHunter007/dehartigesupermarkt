package avansivh11.dehartigesupermarkt.model.order;

import avansivh11.dehartigesupermarkt.model.account.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name="Orders")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Order extends BaseOrder {
    private double totalPrice;
    @OneToOne
    private Customer customer;
    @OneToOne
    private OrderState currentState;
    private int weightClass;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLine> orderLines;

    public Order(Customer customer, ArrayList<OrderLine> orderLines) {
        this.customer = customer;
        this.orderLines = orderLines;
        if(orderLines != null && !orderLines.isEmpty()) {
            //calculate totalPrice
            totalPrice = calculateTotalPrice();
            //calculate weightclass
            weightClass = calculateWeightClass();
        }
        currentState = new OrderReceived(this);
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
