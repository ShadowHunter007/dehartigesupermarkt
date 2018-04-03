package avansivh11.dehartigesupermarkt.model.order;

import avansivh11.dehartigesupermarkt.model.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

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

    public OrderLine(Product product, int amount) {
        this.product = product;
        this.amount = amount;
        this.totalPrice = product.getPrice() * amount;
    }
    //copy constructor
    public OrderLine(OrderLine orderLine) {
        this.product = orderLine.getProduct();
        this.amount = orderLine.getAmount();
        this.totalPrice = product.getPrice() * amount;
    }

    public double getTotalPriceExVat() {
        BigDecimal totalPrice = new BigDecimal(product.getTotalPriceExVat() * amount);
        //correctly round the double to two decimals
        totalPrice = totalPrice.setScale(2, RoundingMode.HALF_UP);
        return totalPrice.doubleValue();
    }
}
