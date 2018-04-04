package avansivh11.dehartigesupermarkt.model.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderReceived extends OrderState {

    public OrderReceived(BaseOrder context) {
        super(context);
        this.setStatusName("Received");
        context.setStatusName(this.statusName);
    }

    @Override
    public void goNext(BaseOrder order) {
        context.setCurrentState(new OrderReadyToSend(order));
    }
}
