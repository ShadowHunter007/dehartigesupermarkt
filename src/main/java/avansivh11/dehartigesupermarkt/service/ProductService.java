package avansivh11.dehartigesupermarkt.service;

import avansivh11.dehartigesupermarkt.model.product.Product;
import avansivh11.dehartigesupermarkt.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.productRepository = repository;
    }

    public ArrayList<Product> getProducts() {
        return (ArrayList<Product>) this.productRepository.findAll();
    }

    public Product getProduct(long id){
        return this.productRepository.findOne(id);
    }

    public Product createProduct(Product product) {
        return this.productRepository.save(product);
    }
}
