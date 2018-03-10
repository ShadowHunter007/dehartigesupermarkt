package avansivh11.dehartigesupermarkt.controller;

import avansivh11.dehartigesupermarkt.model.pdf.BasePdf;
import avansivh11.dehartigesupermarkt.model.pdf.InvoicePdf;
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

    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/")
    public String index(Model model) throws DocumentException, ParserConfigurationException, IOException {

        model.addAttribute("title","Welkom bij de hartige supermarkt.");

        /* ************* PDF ******************* */
        //BasePdf basePdf = new InvoicePdf();
        //basePdf.buildPDF();
         /* ************ PDF - END******************** */

        return "views/product/index";
    }
}
