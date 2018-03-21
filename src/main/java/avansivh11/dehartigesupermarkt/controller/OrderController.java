package avansivh11.dehartigesupermarkt.controller;

import avansivh11.dehartigesupermarkt.model.account.Customer;
import avansivh11.dehartigesupermarkt.model.order.*;
import avansivh11.dehartigesupermarkt.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
//@RequestMapping(value = "/orders")
public class OrderController {
    private final String ORDER_VIEW = "views/order/order_view.html";
    private final String ORDER_SUCCESS = "views/order/success.html";
    private final String ORDER_STATUS = "views/order/order_status_view.html";
    private final String ORDER_STATUS_OVERVIEW = "views/order/order_status_overview.html";

    @Autowired
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    //cannot be implemented yet
    /*@PostMapping("/orders")
    public ModelAndView createOrder(
            @RequestParam(value="shoppingcart", required=true) ShoppingCart shoppingCart
    ) {
        BaseOrder order = new Order(shoppingCart.getCustomer(), shoppingCart.getOrderLines());
        service.createOrder(order);
        return new ModelAndView(ORDER_VIEW, "order", order);
    }*/

    //after creating order (by clicking on the next/confirm button on shoppingcart view) this method is called with the newly created order
    /*@GetMapping("/view")
    public ModelAndView getOrderView(BaseOrder order) {
        this.currentOrder = order;
        return new ModelAndView(ORDER_VIEW, "order", order);
    }*/

    @PutMapping("/orders/{id}")
    public ModelAndView extraOptionsSubmit(
        @PathVariable("id") String id,
        @RequestParam(value="fastShipping", required=false) boolean fastShipping,
        @RequestParam(value="giftWrapped", required=false) boolean giftWrapped,
        @RequestParam(value="discount", required=false) boolean discount
    ) {
        //get the right order from the db
        BaseOrder order = service.getOrderById(Long.parseLong(id));
        //updates currentOrder automatically
        order = decorateOrder(fastShipping, giftWrapped, discount, order);
        //update in the database
        //gebeurt dit automatisch of moet ik hier een functie voor in de repo aanroepen??================================

        return new ModelAndView(ORDER_SUCCESS, "order", order);
    }

    @GetMapping("/orders/{id}/status")
    public ModelAndView showOrderStatus(@PathVariable("id") String id) {
        BaseOrder order = service.getOrderById(Long.parseLong(id));
        return new ModelAndView(ORDER_STATUS, "order", order);
    }

    @GetMapping("/orders/status")
    public ModelAndView showOrderStatusOverview() {
        ArrayList<BaseOrder> orders = service.getOrders();
        return new ModelAndView(ORDER_STATUS_OVERVIEW, "orders", orders);
    }

    private BaseOrder decorateOrder(boolean fastShipping, boolean giftWrapped, boolean discount, BaseOrder currentOrder) {
        if(currentOrder != null) {
            if (fastShipping) {
                FastShippingOrder fastShippingOrder = new FastShippingOrder(currentOrder);
                currentOrder = fastShippingOrder;
            }

            if (giftWrapped) {
                GiftWrappedOrder giftWrappedOrder = new GiftWrappedOrder(currentOrder);
                currentOrder = giftWrappedOrder;
            }

            if (discount) {
                DiscountOrder discountOrder = new DiscountOrder(currentOrder);
                currentOrder = discountOrder;
            }
        }
        return currentOrder;
    }
}
