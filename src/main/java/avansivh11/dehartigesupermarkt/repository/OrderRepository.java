package avansivh11.dehartigesupermarkt.repository;

import avansivh11.dehartigesupermarkt.model.order.BaseOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<BaseOrder, Long> {

}
