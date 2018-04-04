package avansivh11.dehartigesupermarkt.model.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class GiftWrappedOrder extends DecoratedOrder {
    private double giftWrapPrice = 3.50;

    public GiftWrappedOrder(BaseOrder order) {
        super(order);
        double newPrice = calculateTotalPrice();
        setTotalPrice(newPrice);
    }

    @Override
    public double calculateTotalPrice() {
        double newPrice = order.getTotalPrice() + giftWrapPrice;
        return newPrice;
    }
}
