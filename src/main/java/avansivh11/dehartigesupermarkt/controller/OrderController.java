package avansivh11.dehartigesupermarkt.controller;

import avansivh11.dehartigesupermarkt.model.order.*;
import avansivh11.dehartigesupermarkt.repository.DiscountOrderRepository;
import avansivh11.dehartigesupermarkt.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/order")
public class OrderController {
    private final String ORDER_VIEW = "views/order/order_view";
    private BaseOrder currentOrder;

    @Autowired
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    //after creating order (by clicking on the next/confirm button on shoppingcart view) this method is called with the newly created order
    @RequestMapping(value="/view", method = RequestMethod.GET)
    public ModelAndView getOrderView(BaseOrder order) {
        this.currentOrder = order;
        return new ModelAndView(ORDER_VIEW, "order", order);
    }

    @PostMapping("/options")
    public ModelAndView extraOptionsSubmit(
        @RequestParam(value="fastShipping", required=false) boolean fastShipping,
        @RequestParam(value="giftWrapped", required=false) boolean giftWrapped,
        @RequestParam(value="discount", required=false) boolean discount
    ) {
        //updates currentOrder automatically
        decorateOrder(fastShipping, giftWrapped, discount);

        return new ModelAndView(ORDER_VIEW, "order", currentOrder);
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
