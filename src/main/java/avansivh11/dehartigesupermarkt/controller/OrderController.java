package avansivh11.dehartigesupermarkt.controller;

import avansivh11.dehartigesupermarkt.Security.CurrentUser;
import avansivh11.dehartigesupermarkt.model.account.User;
import avansivh11.dehartigesupermarkt.model.order.*;
import avansivh11.dehartigesupermarkt.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class OrderController {
    private final String ORDER_VIEW = "views/order/order_view";
    private final String ORDER_SUCCESS = "views/order/order_success_view";
    private final String ORDER_STATUS = "views/order/order_statu_view";
    private final String ORDER_STATUS_OVERVIEW = "views/order/order_status_overview";
    private final String LOGIN_VIEW = "views/login/login";
    private final CurrentUser currentUser;

    @Autowired
    private final OrderService service;

    public OrderController(OrderService service, CurrentUser currentUser) {
        this.service = service;
        this.currentUser = currentUser;
    }

    @PostMapping("/orders")
    public ModelAndView createOrder() {
        //check if the user is logged in
        User customer = checkUserLogin();
        if(customer == null) {
            return new ModelAndView(LOGIN_VIEW);
        } else {
            ArrayList<OrderLine> orderLines = service.convertOrderLines(ShoppingCartConstants);
            BaseOrder order = new Order(customer, orderLines);
            service.saveOrder(order);

            return new ModelAndView(ORDER_VIEW, "order", order);
        }
    }

    @PostMapping("/orders/extra/{id}")
    public ModelAndView extraOptionsSubmit(
        @PathVariable("id") String id,
        @RequestParam(value="fastShipping", required=false) boolean fastShipping,
        @RequestParam(value="giftWrapped", required=false) boolean giftWrapped,
        @RequestParam(value="discount", required=false) boolean discount
    ) {
        //check if the user is logged in
        User customer = checkUserLogin();
        if(customer == null) {
            return new ModelAndView(LOGIN_VIEW);
        } else {
            //get the right order from the db
            BaseOrder order = service.getOrderById(Long.parseLong(id));
            //updates currentOrder automatically
            order = decorateOrder(fastShipping, giftWrapped, discount, order);
            //update in the database
            service.saveOrder(order);

            return new ModelAndView(ORDER_SUCCESS, "order", order);
        }
    }

    @GetMapping("/orders/status/{id}")
    public ModelAndView showOrderStatus(@PathVariable("id") String id) {
        BaseOrder order = service.getOrderById(Long.parseLong(id));
        return new ModelAndView(ORDER_STATUS, "order", order);
    }

    @GetMapping("/orders/status")
    public ModelAndView showOrderStatusOverview() {
        //check if the user is logged in
        User customer = checkUserLogin();
        if(customer == null) {
            return new ModelAndView(LOGIN_VIEW);
        } else {
            ArrayList<BaseOrder> orders = service.getOrders();
            return new ModelAndView(ORDER_STATUS_OVERVIEW, "orders", orders);
        }

    }

    private BaseOrder decorateOrder(boolean fastShipping, boolean giftWrapped, boolean discount, BaseOrder currentOrder) {
        if(currentOrder != null) {
            //the order of the wrappings is important
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

    private User checkUserLogin() {
        User customer = currentUser.getCurrentUser();
        return customer;
    }
}
