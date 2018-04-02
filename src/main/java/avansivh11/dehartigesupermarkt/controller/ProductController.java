package avansivh11.dehartigesupermarkt.controller;

import avansivh11.dehartigesupermarkt.Security.AuthenticationFacade;
import avansivh11.dehartigesupermarkt.Security.CurrentUser;
import avansivh11.dehartigesupermarkt.model.account.User;
import avansivh11.dehartigesupermarkt.model.pdf.BasePdf;
import avansivh11.dehartigesupermarkt.model.pdf.DeliveryNotePdf;
import avansivh11.dehartigesupermarkt.model.pdf.InvoicePdf;
import avansivh11.dehartigesupermarkt.model.pdf.OrderHistoryPdf;
import avansivh11.dehartigesupermarkt.model.product.Product;
import avansivh11.dehartigesupermarkt.service.LoggingService;
import avansivh11.dehartigesupermarkt.service.logging.AbstractLogger;
import avansivh11.dehartigesupermarkt.service.ProductService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

@Controller
public class ProductController {

    private final ProductService productService;
    private final LoggingService loggingService;
    private final CurrentUser currentUser;

    @Autowired
    public ProductController(ProductService productService, LoggingService loggingService, CurrentUser currentUser) {
        this.productService = productService;
        this.loggingService = loggingService;
        this.currentUser = currentUser;
    }

    @GetMapping("/")
    public String index(Model model) throws DocumentException, ParserConfigurationException, IOException {

        model.addAttribute("title","Welkom bij de hartige supermarkt.");

        /* ************* PDF *******************
        //BasePdf basePdf = new InvoicePdf();
        //basePdf.buildPDF();
        //BasePdf basePdf2 = new OrderHistoryPdf();
        //basePdf2.buildPDF();
        //BasePdf basePdf3 = new DeliveryNotePdf();
        //basePdf3.buildPDF();
        /* ************ PDF - END******************** */

        /* ************* Logging ******************* */
        AbstractLogger logger = AbstractLogger.getChainOfLoggers(loggingService);
        logger.logMessage(AbstractLogger.WEBSITE, "A visitor is on the homepage.");
        /* ************ Logging - END******************** */

        ArrayList<Product> products = productService.getProducts();
        model.addAttribute("products", products);
        return "views/product/index";
    }

    @GetMapping("/products/{id}")
    public String productSpecification(@PathVariable("id") long id, Model model){
        //this is how to get the current user
        User user = currentUser.getCurrentUser();
        Product product = productService.getProduct(id);
        model.addAttribute("product", product);

        return "views/product/productSpecification";
    }

    @PostMapping("/shoppingcart")
    public void shoppingCart(@RequestParam Long id, @RequestParam int quantity){
        //product id
        System.out.println(id);
        //order quantity
        System.out.println(quantity);
    }

}
