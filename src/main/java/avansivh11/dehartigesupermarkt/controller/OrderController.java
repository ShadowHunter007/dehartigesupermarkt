package avansivh11.dehartigesupermarkt.controller;

import avansivh11.dehartigesupermarkt.model.order.*;
import avansivh11.dehartigesupermarkt.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "/order")
public class OrderController {
    private final String ORDER_VIEW = "views/order/order_view.html";
    private final String ORDER_SUCCESS = "views/order/success.html";
    private final String ORDER_STATUS = "views/order/order_status_view.html";
    private final String ORDER_STATUS_OVERVIEW = "views/order/order_status_overview.html";
    private BaseOrder currentOrder;

    @Autowired
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    //after creating order (by clicking on the next/confirm button on shoppingcart view) this method is called with the newly created order
    @GetMapping("/view")
    public ModelAndView getOrderView(BaseOrder order) {
        this.currentOrder = order;
        return new ModelAndView(ORDER_VIEW, "order", order);
    }

    @PostMapping("/confirm")
    public ModelAndView extraOptionsSubmit(
        @RequestParam(value="fastShipping", required=false) boolean fastShipping,
        @RequestParam(value="giftWrapped", required=false) boolean giftWrapped,
        @RequestParam(value="discount", required=false) boolean discount
    ) {
        //updates currentOrder automatically
        decorateOrder(fastShipping, giftWrapped, discount);

        return new ModelAndView(ORDER_SUCCESS, "order", currentOrder);
    }

    @GetMapping("/status/{id}")
    public ModelAndView showOrderStatus(@PathVariable("id") String id) {
        Long requestOrderId = Long.parseLong(id);
        service.getOrderById(requestOrderId);
        return new ModelAndView(ORDER_STATUS, "order", currentOrder);
    }

    @GetMapping("/status_overview")
    public ModelAndView showOrderStatusOverview() {
        ArrayList<BaseOrder> orders = service.getOrders();
        return new ModelAndView(ORDER_STATUS_OVERVIEW, "orders", orders);
    }

    private void decorateOrder(boolean fastShipping, boolean giftWrapped, boolean discount) {
        if(currentOrder != null) {
            if (fastShipping) {
                FastShippingOrder fastShippingOrder = new FastShippingOrder(currentOrder);
                this.currentOrder = fastShippingOrder;
            }

            if (giftWrapped) {
                GiftWrappedOrder giftWrappedOrder = new GiftWrappedOrder(currentOrder);
                this.currentOrder = giftWrappedOrder;
            }

            if (discount) {
                DiscountOrder discountOrder = new DiscountOrder(currentOrder);
                this.currentOrder = discountOrder;
            }
        }
    }
}
