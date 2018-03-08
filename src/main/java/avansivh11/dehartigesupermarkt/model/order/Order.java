package avansivh11.dehartigesupermarkt.model.order;

import avansivh11.dehartigesupermarkt.model.account.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;

@Table(name="Orders")
@Entity
@Getter
@NoArgsConstructor
@ToString
public class Order extends BaseOrder {
    private double totalPrice;
    private Customer customer;
    private State  state;
    private int weightClass;
    private ArrayList<OrderLine> orderLines;

    public Order(Customer customer, int weightClass, ArrayList<OrderLine> orderLines) {
        this.customer = customer;
        this.weightClass = weightClass;
        this.orderLines = orderLines;
        //calculate totalPrice
        totalPrice = calculateTotalPrice();
        //perhaps set this after made persistent
        state = new ReceivedState();
    }

    @Override
    public double calculateTotalPrice() {
        double totalPrice = 0;
        for(OrderLine orderLine : orderLines) {
            totalPrice = totalPrice + orderLine.getPrice();
        }
        return totalPrice;
    }

    private double getExVatTotalPrice() {
        double totalPriceExVat = 0;
        for(OrderLine orderLine : orderLines) {
            totalPriceExVat = totalPriceExVat + orderLine.getPriceExVat();
        }
        return totalPriceExVat;
    }
}
