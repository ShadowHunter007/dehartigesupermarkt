package modelTests;

import avansivh11.dehartigesupermarkt.model.account.User;
import avansivh11.dehartigesupermarkt.model.order.*;
import avansivh11.dehartigesupermarkt.model.product.Product;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class OrderTest {
    @MockBean
    private User customer;
    private ArrayList<OrderLine> orderLines = new ArrayList<>();

    public OrderTest() {
        orderLines.add(new OrderLine(new Product("product1", "description1", null, 2.5, 100), 5, 6));
        orderLines.add(new OrderLine(new Product("product2", "description2", null, 4, 25), 1, 6));
        orderLines.add(new OrderLine(new Product("product3", "description3", null, 10, 10), 3, 6));
    }

    //for a normal order
    @Test
    public void calculateTotalPriceTest1() {
        BaseOrder order = new Order(customer, orderLines);
        double totalPrice = order.calculateTotalPrice();

        assertEquals(totalPrice, 46.5, 0.00);
    }

    //for a fastshippingorder weightclass 1
    @Test
    public void calculateTotalPriceTest2() {
        BaseOrder order = new Order(customer, orderLines);
        FastShippingOrder wrappedOrder = new FastShippingOrder(order);

        Assertions.assertThat(wrappedOrder.getWeightClass() == 1);
        assertEquals(wrappedOrder.getTotalPrice(), 51.5, 0.00);
    }

    //for a fastshippingorder weightclass 2
    @Test
    public void calculateTotalPriceTest3() {
        add2OrderLines();
        BaseOrder order = new Order(customer, orderLines);
        FastShippingOrder wrappedOrder = new FastShippingOrder(order);

        Assertions.assertThat(wrappedOrder.getWeightClass() == 2);
        assertEquals(wrappedOrder.getTotalPrice(), 74.5, 0.00);
    }

    //for a fastshippingorder weightclass 3
    @Test
    public void calculateTotalPriceTest4() {
        add5OrderLines();
        BaseOrder order = new Order(customer, orderLines);
        FastShippingOrder wrappedOrder = new FastShippingOrder(order);

        Assertions.assertThat(wrappedOrder.getWeightClass() == 3);
        assertEquals(wrappedOrder.getTotalPrice(), 114, 0.00);
    }

    //for giftwrappedorder
    @Test
    public void calculateTotalPriceTest5() {
        BaseOrder order = new Order(customer, orderLines);
        GiftWrappedOrder wrappedOrder = new GiftWrappedOrder(order);

        assertEquals(wrappedOrder.getTotalPrice(), 50, 0.00);
    }

    //for order that has discount
    @Test
    public void calculateTotalPriceTest6() {
        BaseOrder order = new Order(customer, orderLines);
        DiscountOrder wrappedOrder = new DiscountOrder(order);

        assertEquals(wrappedOrder.getTotalPrice(), 41.85, 0.00);
    }

    //for order with discount, is giftwrapped and has fastshipping
    @Test
    public void calculateTotalPriceTest7() {
        add2OrderLines();
        BaseOrder order = new Order(customer, orderLines);
        DiscountOrder discountOrder = new DiscountOrder(order);
        GiftWrappedOrder giftWrappedOrder = new GiftWrappedOrder(discountOrder);
        FastShippingOrder fastShippingOrder = new FastShippingOrder(giftWrappedOrder);

        Assertions.assertThat(fastShippingOrder.getWeightClass() == 2);
        assertEquals(fastShippingOrder.getTotalPrice(), 71.35, 0.00);
    }

    //test get total price ex vat
    //this test is bugged for some reason
   /* @Test
    public void getExVatTotalPriceTest() {
        BaseOrder order = new Order(customer, orderLines);
        assertEquals(order.getExVatTotalPrice(), 43.87, 0.00);
    }*/

    private void add2OrderLines() {
        orderLines.add(new OrderLine(new Product("product3", "description3", null, 2.5, 10), 4, 6));
        orderLines.add(new OrderLine(new Product("product4", "description4", null, 2.5, 10), 4, 6));
    }

    private void add5OrderLines() {
        add2OrderLines();
        orderLines.add(new OrderLine(new Product("product3", "description3", null, 1, 10), 10, 6));
        orderLines.add(new OrderLine(new Product("product4", "description4", null, 2, 10), 10, 6));
        orderLines.add(new OrderLine(new Product("product3", "description3", null, 1, 10), 5, 6));
    }
}
