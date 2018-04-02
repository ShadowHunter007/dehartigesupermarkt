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
public class OrderReadyToSend extends OrderState {

    public OrderReadyToSend(BaseOrder context) {
        super(context);
        this.setStatusName("Ready to send");
    }

    @Override
    public void goNext(BaseOrder order) {
        context.setCurrentState(new OrderSent(order));
    }
}
