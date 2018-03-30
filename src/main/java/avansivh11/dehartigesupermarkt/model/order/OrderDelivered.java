package avansivh11.dehartigesupermarkt.model.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderDelivered extends OrderState {

    public OrderDelivered(BaseOrder context) {
        super(context);
    }

    @Override
    public void goNext(BaseOrder order) {

    }
}
