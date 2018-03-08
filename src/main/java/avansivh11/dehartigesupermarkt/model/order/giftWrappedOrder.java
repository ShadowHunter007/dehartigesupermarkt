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
public class giftWrappedOrder extends DecoratedOrder {

    public giftWrappedOrder(BaseOrder order) {
        super(order);
    }

    @Override
    public double calculateTotalPrice() {
        return 0;
    }
}
