package avansivh11.dehartigesupermarkt.model.order;

import avansivh11.dehartigesupermarkt.model.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderLine {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "Product is required.")
    private Product product;

    @NotNull(message = "Price is required.")
    @Min(0)
    private double price;

    public OrderLine(Product product, double price) {
        this.product = product;
        this.price = price;
    }
}
