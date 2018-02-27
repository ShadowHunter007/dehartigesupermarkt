package avansivh11.dehartigesupermarkt.service;

import avansivh11.dehartigesupermarkt.model.product.Product;
import avansivh11.dehartigesupermarkt.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository repository) {
        this.productRepository = repository;
    }

    public ArrayList<Product> getProducts() {
        return (ArrayList<Product>) this.productRepository.findAll();
    }

    public Product createProduct(Product product) {
        return this.productRepository.save(product);
    }
}
