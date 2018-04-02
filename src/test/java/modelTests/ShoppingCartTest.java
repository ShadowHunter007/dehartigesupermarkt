package modelTests;

import avansivh11.dehartigesupermarkt.model.product.Product;
import avansivh11.dehartigesupermarkt.model.shoppingcart.ShoppingCart;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ShoppingCartTest {
    private ShoppingCart shoppingCart;


    @Test
    public void addToShoppingCartTest1() {
        //instantiate
        shoppingCart = new ShoppingCart();
        //testdata
        Product product1 = new Product("product1", "description1", null, 25, 10, 6);
        Product product2 = new Product("product2", "description2", null, 3.2, 20, 6);
        Product product3 = new Product("product3", "description3", null, 1.87, 10, 6);
        //add
        shoppingCart.add(product1, 5);
        shoppingCart.add(product2, 3);
        shoppingCart.add(product3,1);

        Product expected1 = product1;
        Product expected2 = product2;
        Product expected3 = product3;

        Product result1 = shoppingCart.get("product1").getProduct();
        Product result2 = shoppingCart.get("product2").getProduct();
        Product result3 = shoppingCart.get("product3").getProduct();

        //assert the orderline data
        assertTrue(shoppingCart.getOrderLines().size() == 3);
        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
        assertEquals(expected3, result3);
        //assert the product stock
        assertEquals(5, result1.getStock());
        assertEquals(17, result2.getStock());
        assertEquals(9, result3.getStock());

    }

    @Test
    public void addToShoppingCarTest2() {
        //instantiate
        shoppingCart = new ShoppingCart();
        //testdata
        Product product1 = new Product("product1", "description1", null, 25, 10, 6);
        Product product2 = new Product("product3", "description3", null, 1.87, 10, 6);
        //add
        shoppingCart.add(product1, 5);
        shoppingCart.add(product1, 3);
        shoppingCart.add(product2,1);

        int expected1 = 8;
        int expected2 = 1;

        int result1 = shoppingCart.get("product1").getAmount();
        int result2 = shoppingCart.get("product3").getAmount();

        int result3 = product1.getStock();
        int result4 = product2.getStock();

        //assert orderline data
        assertTrue(shoppingCart.getOrderLines().size() == 2);
        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
        //assert the product stock
        assertEquals(2, result3);
        assertEquals(9, result4);
    }

    //test with amount == 0
}
