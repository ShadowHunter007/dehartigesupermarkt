package avansivh11.dehartigesupermarkt.model;

public abstract class DecorateOrder extends BaseOrder {
    protected Order order;

    public DecorateOrder(Order order) {
        this.order = order;
    }

    public abstract void decorate();
}
