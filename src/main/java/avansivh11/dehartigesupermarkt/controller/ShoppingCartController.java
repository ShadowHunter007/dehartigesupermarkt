package avansivh11.dehartigesupermarkt.controller;

import avansivh11.dehartigesupermarkt.Security.CurrentUser;
import avansivh11.dehartigesupermarkt.model.account.User;
import avansivh11.dehartigesupermarkt.model.order.OrderLine;
import avansivh11.dehartigesupermarkt.model.product.Product;
import avansivh11.dehartigesupermarkt.model.shoppingcart.Caretaker;
import avansivh11.dehartigesupermarkt.model.shoppingcart.Memento;
import avansivh11.dehartigesupermarkt.model.shoppingcart.ShoppingCart;
import avansivh11.dehartigesupermarkt.model.shoppingcart.ShoppingCartConstants;
import avansivh11.dehartigesupermarkt.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class ShoppingCartController {
    private final String SHOPPINGCART_VIEW = "views/shoppingcart/shoppingcart_view";
    private final String HOME_VIEW = "views/product/index";
    private final String LOGIN_VIEW = "views/login/login";
    private final CurrentUser currentUser;
    private ShoppingCart shoppingCart;

    @Autowired
    private final ShoppingCartService service;

    public ShoppingCartController(ShoppingCartService service, CurrentUser currentUser) {
        this.service = service;
        this.currentUser = currentUser;
        shoppingCart = new ShoppingCart();
    }

    @GetMapping(value = "/shoppingcart")
    public ModelAndView showShoppingCart() {
        //check if the user is logged in
        User customer = checkUserLogin();
        if(customer == null) {
            return new ModelAndView(LOGIN_VIEW);
        } else {
            ShoppingCartConstants.SHOPPINGCART = shoppingCart;
            return new ModelAndView(SHOPPINGCART_VIEW, "shoppingcart", shoppingCart);
        }
    }

    //call when new product has been added
    @PostMapping(value = "/shoppingcart")
    public ModelAndView addToShoppingCart(
            @ModelAttribute("product") Product product,
            @RequestParam(value="quantity", required=true) String stringAmount
            ) {
        //check if the user is logged in
        User customer = checkUserLogin();
        if(customer == null) {
            return new ModelAndView(LOGIN_VIEW);
        } else {
            ArrayList<Product> allProducts;
            try {
                handleSave(product, stringAmount, customer);
                 allProducts = service.getAllProducts();
                return new ModelAndView(HOME_VIEW, "products", allProducts);
            } catch (Exception ex) {
                ex.getLocalizedMessage();
                allProducts = service.getAllProducts();
                return new ModelAndView(HOME_VIEW, "products", allProducts);
            }
        }
    }

    //only call when amount changed
    @PostMapping(value = "/shoppingcart/update")
    public ModelAndView updateShoppingCart(
            @RequestParam(value="quantity", required = true) String stringAmount,
            @RequestParam(value="productName", required = true) String productName
    ) {
        User customer = checkUserLogin();
        if(customer == null) {
            return new ModelAndView(LOGIN_VIEW);
        } else {
            try {
                this.shoppingCart = service.updateAmounts(shoppingCart, shoppingCart.get(productName), Integer.parseInt(stringAmount));
                //update memento
                service.updateMemento(shoppingCart);
                return new ModelAndView(SHOPPINGCART_VIEW, "shoppingcart", shoppingCart);
            } catch (Exception ex) {
                ex.getLocalizedMessage();
                ArrayList<Product> allProducts = service.getAllProducts();
                return new ModelAndView(HOME_VIEW, "products", allProducts);
            }
        }
    }

    //call when entire product was deleted
    @PostMapping(value = "/shoppingcart/delete")
    public ModelAndView deleteFromShoppingCart(
            @RequestParam(value="productName") String productName
    ) {
        User customer = checkUserLogin();
        if(customer == null) {
            return new ModelAndView(LOGIN_VIEW);
        } else {
            OrderLine orderLine = shoppingCart.get(productName);
            shoppingCart.remove(orderLine, orderLine.getAmount());
            service.saveToDb(orderLine.getProduct());
            //update memento
            service.updateMemento(shoppingCart);

            return new ModelAndView(SHOPPINGCART_VIEW, "shoppingcart", shoppingCart);
        }
    }

    @GetMapping(value = "/shoppingcart/undo")
    public ModelAndView undo() {
        //check if the user is logged in
        User customer = checkUserLogin();
        if(customer == null) {
            return new ModelAndView(LOGIN_VIEW);
        } else {
            //get previous element from mementolist (last item is current)
            Caretaker careTaker = service.getCaretaker();
            Memento memento;
            if(careTaker.getMementos().size() > 1 ) {
                int index = careTaker.getMementos().size() - 2;
                memento = careTaker.getMemento(index);
                //delete last item mementolist
                careTaker.removeMemento(careTaker.getMementos().size()-1);
            } else {
                memento = careTaker.getMemento(0);
            }
            //sync cached shopping cart with memento
            ShoppingCart sync = memento.getShoppingCartState();
            shoppingCart = service.syncShoppingCarts(shoppingCart, sync);

            return new ModelAndView(SHOPPINGCART_VIEW, "shoppingcart", shoppingCart);
        }
    }

    private User checkUserLogin() {
        User customer = currentUser.getCurrentUser();
        return customer;
    }

    private void handleSave(Product product, String stringAmount, User customer) {
        int amount = (Integer.parseInt(stringAmount));
        //update shoppingcart
        shoppingCart.add(product, amount);
        //update memento
        service.updateMemento(shoppingCart);
        //update db
        service.saveToDb(product);
    }
}
