package avansivh11.dehartigesupermarkt.repository;

import avansivh11.dehartigesupermarkt.model.product.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
