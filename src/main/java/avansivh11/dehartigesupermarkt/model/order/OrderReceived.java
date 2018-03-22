package avansivh11.dehartigesupermarkt.model.order;

public class OrderReceived extends OrderState {

    public OrderReceived(BaseOrder context) {
        super(context);
    }

    @Override
    public void goNext(BaseOrder order) {
        context.setCurrentState(new OrderReadyToSend(order));
    }
}
