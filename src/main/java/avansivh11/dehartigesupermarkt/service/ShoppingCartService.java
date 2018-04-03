package avansivh11.dehartigesupermarkt.service;

import avansivh11.dehartigesupermarkt.model.order.OrderLine;
import avansivh11.dehartigesupermarkt.model.product.Product;
import avansivh11.dehartigesupermarkt.model.shoppingcart.Caretaker;
import avansivh11.dehartigesupermarkt.model.shoppingcart.Memento;
import avansivh11.dehartigesupermarkt.model.shoppingcart.Originator;
import avansivh11.dehartigesupermarkt.model.shoppingcart.ShoppingCart;
import avansivh11.dehartigesupermarkt.repository.ProductRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
@Setter
@ToString
public class ShoppingCartService {
    private final ProductRepository productRepository;
    private final ShoppingCart shoppingCart;
    private final Caretaker caretaker;
    private final Originator originator;

    @Autowired
    public ShoppingCartService(ProductRepository productRepository) {
        //instantiate new final shoppingcart
        ShoppingCart shoppingCart = new ShoppingCart();
        this.productRepository = productRepository;
        this.shoppingCart = shoppingCart;
        this.caretaker = new Caretaker();
        this.originator = new Originator();
    }

    public void updateMemento(ShoppingCart shoppingCart) {
        originator.setShoppingCartState(shoppingCart);
        caretaker.addMementoAtTail(originator.save());
    }

    public ShoppingCart updateAmounts(ShoppingCart shoppingCart, OrderLine orderLine, int newAmount) {
        int oldAmount = orderLine.getAmount();
        if(oldAmount != newAmount) {
            shoppingCart.changeAmount(orderLine, newAmount-oldAmount);
        }
        return shoppingCart;
    }

    //synchronizes the first shopping cart with the second and returns it
    public ShoppingCart syncShoppingCarts(ShoppingCart returnShoppingCart, ShoppingCart syncShoppingCart) {
        OrderLine returnOrderLine = null;
        OrderLine syncOrderLine = null;
        //loop and comparewith returnShoppingcart, delete any orderlines that should not be there
        for(String name : returnShoppingCart.getOrderLines().keySet()) {
            if(syncShoppingCart.get(name) == null) {
                returnOrderLine = returnShoppingCart.get(name);
                try {
                    returnShoppingCart.remove(returnOrderLine, returnOrderLine.getAmount());
                } catch(Exception ex) {
                    ex.getLocalizedMessage();
                    System.out.println("Item " + name + " could not be deleted from the current shopping cart");
                    continue;
                }
            } else {
                returnOrderLine = returnShoppingCart.get(name);
                syncOrderLine = syncShoppingCart.get(name);
                //sync amounts, product stock and totalprice
                if (returnOrderLine.getAmount() != syncOrderLine.getAmount()) {
                    updateAmounts(returnShoppingCart, returnOrderLine, syncOrderLine.getAmount());
                }
            }
        }
        //now loop again but compare with syncShoppingcart, add any missing orderlines
        for (String name : syncShoppingCart.getOrderLines().keySet()) {
            if (returnShoppingCart.get(name) != null) {
                returnOrderLine = returnShoppingCart.get(name);
                syncOrderLine = syncShoppingCart.get(name);
                //sync amounts, product stock and totalprice
                if (returnOrderLine.getAmount() != syncOrderLine.getAmount()) {
                    updateAmounts(returnShoppingCart, returnOrderLine, syncOrderLine.getAmount());
                }
            } else {
                //add missing product
                try {
                    Product productToAdd = syncShoppingCart.get(name).getProduct();
                    int amountToAdd = syncShoppingCart.get(name).getAmount();
                    returnShoppingCart.add(productToAdd, amountToAdd);
                } catch (Exception ex) {
                    ex.getLocalizedMessage();
                    System.out.println("Item " + name + " in shopping cart could not be restored");
                    continue;
                }
            }
        }
        assert returnShoppingCart.getOrderLines().size() == syncShoppingCart.getOrderLines().size();
        return returnShoppingCart;
    }

    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> products = (ArrayList<Product>)productRepository.findAll();
        return products;
    }
}
