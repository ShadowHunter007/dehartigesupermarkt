package avansivh11.dehartigesupermarkt.model.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Name is required.")
    private String name;

    @NotEmpty(message = "Description is required.")
    private String description;

    @NotEmpty(message = "Image is required")
    private String imagePath;

    @NotNull(message = "Price is required.")
    @Min(0)
    private double price;

    @NotNull(message = "Voorraad is required")
    @Min(0)
    private int stock;

    @NotNull(message = "Vat percentage is required.")
    @Min(0)
    private int vatPercentage;

    public Product(String name, String description, String imagePath, double price, int stock, int vatPercentage) {
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
        this.price = price;
        this.stock = stock;
        this.vatPercentage = vatPercentage;
    }

    public double getTotalPriceExVat() {
        double divisor = (Double.parseDouble(String.valueOf(vatPercentage)) / 100) + 1;
        double priceExVat = price / divisor;
        return priceExVat;
    }
}
