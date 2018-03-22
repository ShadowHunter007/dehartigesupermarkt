package avansivh11.dehartigesupermarkt.model.order;

public class OrderSent extends OrderState {

    public OrderSent(BaseOrder context) {
        super(context);
    }

    @Override
    public void goNext(BaseOrder order) {
        context.setCurrentState(new OrderDelivered(order));
    }
}
