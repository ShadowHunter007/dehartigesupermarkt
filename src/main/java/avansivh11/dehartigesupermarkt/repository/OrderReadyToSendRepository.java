package avansivh11.dehartigesupermarkt.repository;

import avansivh11.dehartigesupermarkt.model.order.OrderReadyToSend;
import org.springframework.data.repository.CrudRepository;

public interface OrderReadyToSendRepository  extends CrudRepository<OrderReadyToSend, Long> {
}
