package modelTests;

import avansivh11.dehartigesupermarkt.model.order.OrderLine;
import avansivh11.dehartigesupermarkt.model.product.Product;
import avansivh11.dehartigesupermarkt.model.shoppingcart.ShoppingCart;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import javax.validation.constraints.AssertTrue;

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

    @Test
    public void removeFromShoppingCartTest1() {
        //instantiate
        shoppingCart = new ShoppingCart();
        //testdata
        Product product1 = new Product("product1", "description1", null, 25, 10, 6);
        Product product2 = new Product("product2", "description2", null, 3.2, 20, 6);
        Product product3 = new Product("product3", "description3", null, 1.87, 10, 6);
        //add
        OrderLine orderLine1 = shoppingCart.add(product1, 5);
        OrderLine orderLine2 = shoppingCart.add(product2, 5);
        OrderLine orderLine3 = shoppingCart.add(product3, 5);
        //remove
        shoppingCart.remove(orderLine2, orderLine2.getAmount());
        shoppingCart.remove(orderLine3, 6);
        //do assertions
        assertTrue(shoppingCart.getOrderLines().size() == 1);
        Assertions.assertThat(shoppingCart.get(product2.getName())).isNull();
        Assertions.assertThat(shoppingCart.get(product3.getName())).isNull();
    }

    @Test
    public void removeFromShoppingCartTest2() {
        //instantiate
        shoppingCart = new ShoppingCart();
        //testdata
        Product product1 = new Product("product1", "description1", null, 25, 10, 6);
        Product product2 = new Product("product2", "description2", null, 3.2, 20, 6);
        Product product3 = new Product("product3", "description3", null, 1.87, 10, 6);
        //add
        OrderLine orderLine1 = shoppingCart.add(product1, 5);
        OrderLine orderLine2 = shoppingCart.add(product2, 5);
        OrderLine orderLine3 = shoppingCart.add(product3, 5);
        //remove
        shoppingCart.remove(orderLine1, 3);
        shoppingCart.remove(orderLine2, 1);
        shoppingCart.remove(orderLine3, 4);
        //result values stock
        int result1 = product1.getStock();
        int result2 = product2.getStock();
        int result3 = product3.getStock();
        //result values amount
        int result4 = shoppingCart.get(product1.getName()).getAmount();
        int result5 = shoppingCart.get(product2.getName()).getAmount();
        int result6 = shoppingCart.get(product3.getName()).getAmount();
        //assert the list
        assertTrue(shoppingCart.getOrderLines().size() == 3);
        //assert the stock of the products
        assertEquals(8, result1);
        assertEquals(16, result2);
        assertEquals(9, result3);
        //assert the amount of the orderlines
        assertEquals(2, result4);
        assertEquals(4, result5);
        assertEquals(1, result6);
    }
}
