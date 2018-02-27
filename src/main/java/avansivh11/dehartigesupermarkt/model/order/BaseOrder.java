package avansivh11.dehartigesupermarkt.model.order;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public abstract class BaseOrder {

	@Id
	@GeneratedValue
	private Long id;

	public abstract int price();
}
