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
public class PremiumOrder extends DecoratedOrder {
    @NotEmpty(message = "Name should not be empty.")
    private String name;

    @NotNull(message = "Price is required.")
    @Min(0)
    private double price = 0;

    public PremiumOrder(String name, double price, BaseOrder order) {
        super(order);
        this.name = name;
        this.price = price;
    }
}
