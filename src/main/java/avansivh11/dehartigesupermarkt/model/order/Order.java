package avansivh11.dehartigesupermarkt.model.order;

import avansivh11.dehartigesupermarkt.model.account.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name="Orders")
@Entity
@Getter
@NoArgsConstructor
@ToString
public class Order extends BaseOrder {
    private double totalPrice;
    @OneToOne
    private Customer customer;
    @OneToOne
    private State  state;
    private int weightClass;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLine> orderLines;

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
            totalPrice = totalPrice + orderLine.getTotalPrice();
        }
        return totalPrice;
    }

    private double getExVatTotalPrice() {
        double totalPriceExVat = 0;
        for(OrderLine orderLine : orderLines) {
            totalPriceExVat = totalPriceExVat + orderLine.getTotalPriceExVat();
        }
        return totalPriceExVat;
    }
}
