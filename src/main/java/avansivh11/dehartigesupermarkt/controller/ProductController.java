package avansivh11.dehartigesupermarkt.controller;

import avansivh11.dehartigesupermarkt.service.LoggingService;
import avansivh11.dehartigesupermarkt.service.logging.AbstractLogger;
import avansivh11.dehartigesupermarkt.service.ProductService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Controller
public class ProductController {

    private final ProductService productService;
    private final LoggingService loggingService;

    @Autowired
    public ProductController(ProductService productService, LoggingService loggingService) {
        this.productService = productService;
        this.loggingService = loggingService;
    }

    @RequestMapping("/")
    public String index(Model model) throws DocumentException, ParserConfigurationException, IOException {

        model.addAttribute("title","Welkom bij de hartige supermarkt.");

        /* ************* PDF ******************* */
        //BasePdf basePdf = new InvoicePdf();
        //basePdf.buildPDF();
         /* ************ PDF - END******************** */

        /* ************* Logging ******************* */
        AbstractLogger logger = AbstractLogger.getChainOfLoggers(loggingService);
        logger.logMessage(AbstractLogger.WEBSITE, "A visitor is on the homepage.");
        /* ************ Logging - END******************** */

        return "views/product/index";
    }
}
