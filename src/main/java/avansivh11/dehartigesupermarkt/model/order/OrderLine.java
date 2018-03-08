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

    //Included vat
    @NotNull(message = "Price is required.")
    @Min(0)
    private double price;

    @NotNull(message = "Vul een btw percentage in")
    private int vatPercentage;

    public OrderLine(Product product, double price, int vatPercentage) {
        this.product = product;
        this.price = price;
        this.vatPercentage = vatPercentage;
    }

    public double getPriceExVat() {
        return price*100/(100+vatPercentage);
    }
}
