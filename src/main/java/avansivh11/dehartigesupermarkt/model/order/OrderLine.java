package avansivh11.dehartigesupermarkt.model.order;

import avansivh11.dehartigesupermarkt.model.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
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

    @NotNull(message = "Er moet een product ingevuld zijn")
    @OneToOne(cascade = CascadeType.ALL)
    private Product product;

    //Included vat
    @NotNull(message = "Er moet een prijs ingevuld zijn")
    @Min(0)
    private double totalPrice;

    @NotNull(message = "Er moet een hoeveelheid ingevuld zijn")
    private int amount;

    @NotNull(message = "Vul een btw percentage in")
    private int vatPercentage;

    public OrderLine(Product product, int amount, int vatPercentage) {
        this.product = product;
        this.amount = amount;
        this.vatPercentage = vatPercentage;
        this.totalPrice = product.getPrice() * amount;
    }

    public double getTotalPriceExVat() {
        double divisor = vatPercentage / 100 + 1;
        double priceExVat = totalPrice / divisor;
        return priceExVat;
    }
}
