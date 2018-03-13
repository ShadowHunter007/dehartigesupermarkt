package avansivh11.dehartigesupermarkt.controller;

import avansivh11.dehartigesupermarkt.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/shoppingcart")
public class ShoppingCartController {

	@Autowired
	private final ShoppingCartService shoppingCartService;

	public ShoppingCartController(ShoppingCartService shoppingCartService) {
		this.shoppingCartService = shoppingCartService;
	}

	@RequestMapping("/")
	public String index(Model model) {
		return "views/shoppingcart/index";
	}
}
