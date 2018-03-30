package avansivh11.dehartigesupermarkt.Configuration;

import avansivh11.dehartigesupermarkt.model.product.Product;
import avansivh11.dehartigesupermarkt.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class ApplicationConfig {

    private ProductService productService;

    @Autowired
    public ApplicationConfig(ProductService productService){
        this.productService = productService;
    }

    @PostConstruct
    public void createProducts(){
        Product prod1 = new Product("Tijgerbrood", "Dit heerlijke brood is een voor jou vers gebakken volkoren brood met een heerlijk tijgerkorstje.", "/images/Tijgerbrood.JPG", 1.89, 10);
        Product prod2 = new Product("Extra jam aardbeien", "Deze jam zit boordevol aardbeien en is lekker fris van smaak.", "/images/AardbeiJam.JPG", 0.69, 10);
        Product prod3 = new Product("Vlokken", "Verrassende krullige vlokken voor op de boterham.", "/images/Vlokken.jpg", 1.65, 10);
        Product prod4 = new Product("Chocoladehagel puur", "Heerlijke pure chocoladehagelslag om de boterham lekker mee te versieren.", "/images/PureHagelslag.JPG", 1.99, 10);
        Product prod5 = new Product("Hazelnootpasta", "Duo Penotti, dubbel lekker in een potti, is de originele tweekleuren hazelnootpasta.", "/images/HazelnootPasta.JPG", 1.99, 10);
        Product prod6 = new Product("Pindakaas", "Calvé Pindakaas is de enige échte pindakaas. En dat proef je meteen!", "/images/Pindakaas.JPG", 2.29, 10);
        Product prod7 = new Product("Chips naturel", "De enige echte Lay's chips, knapperig gebakken, met een beetje zout voor de smaak.", "/images/NaturelChips.JPG", 1.29, 10);
        Product prod8 = new Product("Borrelnootjes", "Pinda's in een krokant jasje met ui- en knoflooksmaak.", "/images/Borrelnootjes.jpg", 1.79, 10);
        Product prod9 = new Product("Coca-Cola regular", "Coca-Cola is de meest favoriete frisdrank van de wereld. De 1,5L PET is de ideale fles om uit te schenken tijdens de gezellige momenten aan tafel.", "/images/Cola.jpg", 2.08, 10);

        productService.createProduct(prod1);
        productService.createProduct(prod2);
        productService.createProduct(prod3);
        productService.createProduct(prod4);
        productService.createProduct(prod5);
        productService.createProduct(prod6);
        productService.createProduct(prod7);
        productService.createProduct(prod8);
        productService.createProduct(prod9);
    }
}
