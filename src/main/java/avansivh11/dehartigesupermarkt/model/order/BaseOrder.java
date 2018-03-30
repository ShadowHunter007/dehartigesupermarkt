package avansivh11.dehartigesupermarkt.model.order;

import avansivh11.dehartigesupermarkt.model.account.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public abstract class BaseOrder {

	@Id
	@GeneratedValue
	protected Long id;

	@NotNull(message = "Vul een geldige waarde in voor de totaalprijs" )
	protected double totalPrice;
	@NotNull(message = "Vul een geldige waarde in voor de klant")
	@OneToOne
	protected User customer;
	@NotNull(message="Vul een geldige toestand in voor deze bestelling")
	@OneToOne
	protected OrderState currentState;
	@NotNull(message="Vul een geldige gewichtsklasse in voor deze bestelling")
	@Min(1)
	@Max(3)
	protected int weightClass;
	@OneToMany(cascade = CascadeType.ALL)
	@NotNull(message = "Vul een geldige bestelregel in")
	protected List<OrderLine> orderLines;

	public abstract double calculateTotalPrice();
}
