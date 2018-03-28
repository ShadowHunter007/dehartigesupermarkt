package serviceTests;

import avansivh11.dehartigesupermarkt.model.order.*;
import avansivh11.dehartigesupermarkt.repository.BaseOrderRepository;
import avansivh11.dehartigesupermarkt.service.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

public class orderServiceTest {
    @MockBean
    private BaseOrderRepository orderRepository;

    @Test
    public void fastShippingSortTest() {
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



        OrderService orderService = new OrderService(orderRepository);
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
}
