package serviceTests;

import avansivh11.dehartigesupermarkt.model.order.*;
import avansivh11.dehartigesupermarkt.repository.BaseOrderRepository;
import avansivh11.dehartigesupermarkt.service.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
    @MockBean
    private BaseOrderRepository orderRepository;

    private ArrayList<BaseOrder> orders = new ArrayList<>();

    public OrderServiceTest() {
        //testdata
        ArrayList<BaseOrder> orders = new ArrayList<>();
        orders.add(new Order());
        orders.add(new GiftWrappedOrder());
        orders.add(new Order());
        orders.add(new Order());
        orders.add(new GiftWrappedOrder());
        orders.add(new FastShippingOrder());
        orders.add(new Order());
        orders.add(new DiscountOrder());
        orders.add(new FastShippingOrder());
        orders.add(new Order());

        //change the states of the orders in the list
        orders.get(0).setCurrentState(new OrderSent(orders.get(0)));
        orders.get(1).setCurrentState(new OrderReceived(orders.get(1)));
        orders.get(2).setCurrentState(new OrderReceived(orders.get(2)));
        orders.get(3).setCurrentState(new OrderReadyToSend(orders.get(3)));
        orders.get(4).setCurrentState(new OrderReadyToSend(orders.get(4)));
        orders.get(5).setCurrentState(new OrderReceived(orders.get(5)));
        orders.get(6).setCurrentState(new OrderReceived(orders.get(6)));
        orders.get(7).setCurrentState(new OrderDelivered(orders.get(7)));
        orders.get(8).setCurrentState(new OrderReceived(orders.get(8)));
        orders.get(9).setCurrentState(new OrderReceived(orders.get(9)));

        this.orders = orders;
    }

    @Test
    public void fastShippingSortTest() {
        OrderService orderService = new OrderService(orderRepository, orders);
        ArrayList<BaseOrder> sortedList = orderService.fastShippingSort(orders);

        //is instanceof FastShippingOrder
        Assertions.assertThat(sortedList.get(0)).isInstanceOfAny(FastShippingOrder.class);
        Assertions.assertThat(sortedList.get(1)).isInstanceOfAny(FastShippingOrder.class);

        //is NOT instanceof FastShippingOrder
        Assertions.assertThat(sortedList.get(2)).isNotInstanceOfAny(FastShippingOrder.class);
        Assertions.assertThat(sortedList.get(3)).isNotInstanceOfAny(FastShippingOrder.class);
        Assertions.assertThat(sortedList.get(4)).isNotInstanceOfAny(FastShippingOrder.class);
        Assertions.assertThat(sortedList.get(5)).isNotInstanceOfAny(FastShippingOrder.class);
        Assertions.assertThat(sortedList.get(6)).isNotInstanceOfAny(FastShippingOrder.class);
        Assertions.assertThat(sortedList.get(7)).isNotInstanceOfAny(FastShippingOrder.class);
        Assertions.assertThat(sortedList.get(8)).isNotInstanceOfAny(FastShippingOrder.class);
        Assertions.assertThat(sortedList.get(9)).isNotInstanceOfAny(FastShippingOrder.class);
    }

    @Test
    public void setOrdersReadyToSendTest1() {
        OrderService orderService = new OrderService(orderRepository, orders);
        OrderService spy = Mockito.spy(orderService);
        Mockito.doReturn(null).when(spy).saveOrder(any());
        //execute testing operation
        spy.setOrdersReadyToSend();

        //assert the list
        assertTrue(orders.size() == 5);
    }

    @Test(expected = java.lang.AssertionError.class)
    public void setOrdersReadyToSendTest2() {
        //remove 7 items from the original list
        Iterator iter = orders.iterator();
        int count = 0;
        while(iter.hasNext() && count < 7) {
            iter.next();
            iter.remove();
            count++;
        }
        OrderService orderService = new OrderService(orderRepository, orders);
        OrderService spy = Mockito.spy(orderService);
        Mockito.doReturn(null).when(spy).saveOrder(any());
        //execute testing operation
        spy.setOrdersReadyToSend();
    }
}
