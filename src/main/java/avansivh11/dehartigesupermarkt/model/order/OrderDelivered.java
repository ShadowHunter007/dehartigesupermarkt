package avansivh11.dehartigesupermarkt.model.order;

public class OrderDelivered extends OrderState {

    public OrderDelivered(BaseOrder context) {
        super(context);
    }

    @Override
    public void goNext(BaseOrder order) {

    }
}
