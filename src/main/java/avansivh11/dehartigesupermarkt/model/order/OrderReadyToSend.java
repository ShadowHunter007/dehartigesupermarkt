package avansivh11.dehartigesupermarkt.model.order;

public class OrderReadyToSend extends OrderState {

    public OrderReadyToSend(BaseOrder context) {
        super(context);
    }

    @Override
    public void goNext(BaseOrder order) {
        context.setCurrentState(new OrderSent(order));
    }
}
