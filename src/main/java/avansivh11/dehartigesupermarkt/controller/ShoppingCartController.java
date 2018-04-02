package avansivh11.dehartigesupermarkt.controller;

import avansivh11.dehartigesupermarkt.Security.CurrentUser;
import avansivh11.dehartigesupermarkt.model.account.User;
import avansivh11.dehartigesupermarkt.model.order.OrderLine;
import avansivh11.dehartigesupermarkt.model.product.Product;
import avansivh11.dehartigesupermarkt.model.shoppingcart.Caretaker;
import avansivh11.dehartigesupermarkt.model.shoppingcart.Memento;
import avansivh11.dehartigesupermarkt.model.shoppingcart.Originator;
import avansivh11.dehartigesupermarkt.model.shoppingcart.ShoppingCart;
import avansivh11.dehartigesupermarkt.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.text.ParseException;

@Controller
@RequestMapping(value = "/shoppingcart")
public class ShoppingCartController {
    private final String SHOPPINGCART_VIEW = "views/shoppingcart/shoppingcart_view.html";
    private final String HOME_VIEW = "views/product/index.html";
    private final String LOGIN_VIEW = "views/login/login.html";
    private final CurrentUser currentUser;

    @Autowired
    private final ShoppingCartService service;

    public ShoppingCartController(ShoppingCartService service, CurrentUser currentUser) {
        this.service = service;
        this.currentUser = currentUser;
    }

    @GetMapping(value = "/")
    public ModelAndView showShoppingCart() {
        //check if the user is logged in
        User customer = checkUserLogin();
        if(customer == null) {
            return new ModelAndView(LOGIN_VIEW);
        } else {
            return new ModelAndView(SHOPPINGCART_VIEW, "shoppingcart", service.getShoppingCart());
        }
    }

    //call when new product has been added
    @PostMapping(value = "/")
    public ModelAndView addToShoppingCart(
            @RequestParam(value="amount", required=true) String stringAmount,
            @ModelAttribute("product") Product product
            ) {
        //check if the user is logged in
        User customer = checkUserLogin();
        if(customer == null) {
            return new ModelAndView(LOGIN_VIEW);
        } else {
            try {
                handleSave(product, stringAmount, customer);
                return new ModelAndView(HOME_VIEW, "message", "Het product is toegevoegd aan uw winkelwagen: " + product.getName());
            } catch (Exception ex) {
                ex.getLocalizedMessage();
                return new ModelAndView(HOME_VIEW, "message", "Er is iets misgegaan met het toevoegen van het product, probeer het nog een keer of log opnieuw in: " + product.getName());
            }
        }
    }

    //only call when amount changed
    @PutMapping(value = "/")
    public ModelAndView updateShoppingCart(
            @RequestParam(value="amount", required=false) String stringAmount,
            @ModelAttribute("product") Product product
    ) {
        User customer = checkUserLogin();
        if(customer == null) {
            return new ModelAndView(LOGIN_VIEW);
        } else {
            try {
                handleSave(product, stringAmount, customer);
                return new ModelAndView(HOME_VIEW, "message", "Het aantal van: " + product.getName() + " is aangepast met " + stringAmount);
            } catch (Exception ex) {
                ex.getLocalizedMessage();
                return new ModelAndView(HOME_VIEW, "message", "Er is iets misgegaan met het veranderen van het aantal van het product, probeer het nog een keer of log opnieuw in: " + product.getName());
            }
        }
    }

    //call when entire prodcut was deleted
    @DeleteMapping(value = "/")
    public ModelAndView deleteFromShoppingCart(
            @ModelAttribute("product") Product product
    ) {
        User customer = checkUserLogin();
        if(customer == null) {
            return new ModelAndView(LOGIN_VIEW);
        } else {
            ShoppingCart shoppingCart = customer.getShoppingCart();
            OrderLine orderLine = shoppingCart.get(product.getName());
            shoppingCart.remove(product, orderLine.getAmount());
            //update memento
            service.updateMemento(shoppingCart);

            return new ModelAndView(SHOPPINGCART_VIEW, "shoppingcart", shoppingCart);
        }
    }

    @PostMapping(value = "/undo")
    public ModelAndView undo() {
        //check if the user is logged in
        User customer = checkUserLogin();
        if(customer == null) {
            return new ModelAndView(LOGIN_VIEW, "shoppingcart", customer.getShoppingCart());
        } else {
            //get previous element from mementolist (last item is current)
            Caretaker careTaker = service.getCaretaker();
            int index = careTaker.getMementos().size()-2;
            Memento memento = careTaker.getMemento(index);

            //delete last item mementolist
            index++;
            careTaker.removeMemento(index);
            return new ModelAndView(SHOPPINGCART_VIEW, "shoppingcart", memento.getShoppingCartState());
        }
    }

    private User checkUserLogin() {
        User customer = currentUser.getCurrentUser();
        return customer;
    }

    private void handleSave(Product product, String stringAmount, User customer) {
        int amount = (Integer.parseInt(stringAmount));
        //update shoppingcart
        ShoppingCart shoppingCart = customer.getShoppingCart();
        shoppingCart.add(product, amount);
        //update memento
        service.updateMemento(shoppingCart);
    }
}
