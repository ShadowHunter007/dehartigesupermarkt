package avansivh11.dehartigesupermarkt.repository;

import avansivh11.dehartigesupermarkt.model.order.BaseOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface BaseOrderRepository  extends CrudRepository<BaseOrder, Long> {
    @Query(value = "SELECT * FROM base_order, order_state WHERE base_order.current_state_id = order_state.id AND order_state.dtype = 'received'", nativeQuery = true)
    List<BaseOrder> getFastShippingOrders();
}
