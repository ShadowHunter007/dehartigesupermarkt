package avansivh11.dehartigesupermarkt.model.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
public abstract class DecoratedOrder extends BaseOrder {

    @OneToOne
    protected BaseOrder order;

    public DecoratedOrder(BaseOrder order) {
        this.order = order;
        this.id = order.id;
        this.orderLines = order.orderLines;
        this.customer = order.customer;
        this.weightClass = order.weightClass;
        this.statusName = order.statusName;
        this.totalPrice = order.totalPrice;
        this.currentState = new OrderReceived(this);
    }
}