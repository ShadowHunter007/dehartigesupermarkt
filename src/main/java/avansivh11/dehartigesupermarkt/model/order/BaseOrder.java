package avansivh11.dehartigesupermarkt.model.order;

import avansivh11.dehartigesupermarkt.model.account.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@NoArgsConstructor
public abstract class BaseOrder {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull(message = "Vul een geldige waarde in voor de totaalprijs" )
	private double totalPrice;
	@NotNull(message = "Vul een geldige waarde in voor de klant")
	private Customer customer;
	@NotNull(message="Vul een geldige toestand in voor deze bestelling")
	private State state;
	@NotNull(message="Vul een geldige gewichtsklasse in voor deze bestelling")
	@Min(1)
	@Max(3)
	private int weightClass;
	@OneToMany(cascade = CascadeType.ALL)
	@NotNull(message = "Vul een geldige bestelregel in")
	private ArrayList<OrderLine> orderLines;


	public abstract double calculateTotalPrice();
}
