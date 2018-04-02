package avansivh11.dehartigesupermarkt.controller;

import avansivh11.dehartigesupermarkt.model.account.User;
import avansivh11.dehartigesupermarkt.model.order.*;
import avansivh11.dehartigesupermarkt.model.shoppingcart.ShoppingCart;
import avansivh11.dehartigesupermarkt.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "/orders")
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

    @PostMapping("/")
    public ModelAndView createOrder(
            @RequestParam(value="shoppingcart") ShoppingCart shoppingCart
    ) {
        ArrayList<OrderLine> orderLines = service.convertOrderLines(shoppingCart.getOrderLines());
        BaseOrder order = new Order(shoppingCart.getCustomer(), orderLines);
        service.saveOrder(order);

        return new ModelAndView(ORDER_VIEW, "order", order);
    }

    @PutMapping("/{id}")
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
        service.saveOrder(order);

        return new ModelAndView(ORDER_SUCCESS, "order", order);
    }

    @GetMapping("/{id}/status")
    public ModelAndView showOrderStatus(@PathVariable("id") String id) {
        BaseOrder order = service.getOrderById(Long.parseLong(id));
        return new ModelAndView(ORDER_STATUS, "order", order);
    }

    @GetMapping("/status")
    public ModelAndView showOrderStatusOverview() {
        ArrayList<BaseOrder> orders = service.getOrders();
        return new ModelAndView(ORDER_STATUS_OVERVIEW, "orders", orders);
    }

    private BaseOrder decorateOrder(boolean fastShipping, boolean giftWrapped, boolean discount, BaseOrder currentOrder) {
        if(currentOrder != null) {
            if (discount) {
                DiscountOrder discountOrder = new DiscountOrder(currentOrder);
                currentOrder = discountOrder;
            }

            if (giftWrapped) {
                GiftWrappedOrder giftWrappedOrder = new GiftWrappedOrder(currentOrder);
                currentOrder = giftWrappedOrder;
            }

            if (fastShipping) {
                FastShippingOrder fastShippingOrder = new FastShippingOrder(currentOrder);
                currentOrder = fastShippingOrder;
            }
        }
        return currentOrder;
    }
}
