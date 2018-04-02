package avansivh11.dehartigesupermarkt.controller;

import avansivh11.dehartigesupermarkt.model.account.User;
import avansivh11.dehartigesupermarkt.model.shoppingcart.Caretaker;
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

@Controller
@RequestMapping(value = "/shoppingcart")
public class ShoppingCartController {
    private final String SHOPPINGCART_VIEW = "views/shoppingcart/shoppingcart_view.html";

    /*@Autowired
    private final ShoppingCartService service;

    public ShoppingCartController(ShoppingCartService service) {
        this.service = service;
        this.loggedInUser = SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping(value = "/")
    public ModelAndView showShoppingCart() {
        return new ModelAndView(SHOPPINGCART_VIEW, "shoppingcart", service.getShoppingCart());
    }

    //call when new product has been added
    @PostMapping(value = "/")
    public void addToShoppingCart(
            @PathVariable("shoppingCart") ShoppingCart shoppingCart
            ) {
        //loggedInUser.getDetails().get
        //update the product stock
        //update memento
        service.updateMemento(shoppingCart);
    }

    //only call when amount changed
    @PutMapping(value = "/")
    public ModelAndView updateShoppingCart(
            @PathVariable("shoppingCart") ShoppingCart shoppingCart
    ) {
        //update memento
        service.updateMemento(shoppingCart);
    }

    //call when entire prodcut was deleted
    @DeleteMapping(value = "/")
    public ModelAndView deleteFromShoppingCart(
            @PathVariable("shoppingCart") ShoppingCart shoppingCart
    ) {
        //update memento
        service.updateMemento(shoppingCart);
    }

    @PostMapping(value = "/undo")
    public ModelAndView undo(
            @PathVariable("index") String index
    ) {
        ShoppingCart newShoppingCart = service.changeMemento(Integer.parseInt(index));
        //also return the current index of the mementoList
    }

    @PostMapping(value = "/redo")
    public ModelAndView redo(
            @PathVariable("index") String index
    ) {
        ShoppingCart newShoppingCart = service.changeMemento(Integer.parseInt(index));
        //also return the current index of the mementolist
    }*/
}
