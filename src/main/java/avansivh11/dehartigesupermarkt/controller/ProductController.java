package avansivh11.dehartigesupermarkt.controller;

import avansivh11.dehartigesupermarkt.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {

    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/")
    public String index(Model model) {

        model.addAttribute("title","Welkom bij de hartige supermarkt.");

        return "views/product/index";
    }
}
