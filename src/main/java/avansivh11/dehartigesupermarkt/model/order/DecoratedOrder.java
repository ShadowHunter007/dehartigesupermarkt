package avansivh11.dehartigesupermarkt.model.order;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter
@NoArgsConstructor
public abstract class DecoratedOrder extends BaseOrder {

    @OneToOne
    protected BaseOrder order;

    public DecoratedOrder(BaseOrder order) {
        this.order = order;
    }
}