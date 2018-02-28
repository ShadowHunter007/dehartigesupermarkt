package avansivh11.dehartigesupermarkt.model;

public class FastShippingOption extends DecorateOrder {
    private double fastShippingPrice;

    public FastShippingOption(Order order) {
        super(order);
        fastShippingPrice = 15.00;
    }

    @Override
    public void decorate() {
        order.setTotalPrice(order.getTotalPrice() + fastShippingPrice);
    }
}
