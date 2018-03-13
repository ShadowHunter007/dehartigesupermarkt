package avansivh11.dehartigesupermarkt.service;

import avansivh11.dehartigesupermarkt.model.account.Customer;
import avansivh11.dehartigesupermarkt.model.product.Product;
import avansivh11.dehartigesupermarkt.model.shoppingcart.ShoppingCart;
import avansivh11.dehartigesupermarkt.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ShoppingCartService {

    @Autowired
    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartService(ShoppingCartRepository repository) {
        this.shoppingCartRepository = repository;
    }

//    public ShoppingCart getCart(Customer customer) {
//        return this.shoppingCartRepository.findOne();
//    }
}
