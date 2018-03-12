package avansivh11.dehartigesupermarkt.controller;

import avansivh11.dehartigesupermarkt.model.order.BaseOrder;
import avansivh11.dehartigesupermarkt.model.order.Order;
import avansivh11.dehartigesupermarkt.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/order")
public class OrderController {
    private final String ORDER_VIEW = "views/order/order_view";
    @Autowired
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    //after creating order (by clicking on the next/confirm button on shoppingcart view) this method is called with the newly created order
    @RequestMapping(value="/view", method = RequestMethod.GET)
    public ModelAndView getOrderView(BaseOrder order) {
        return new ModelAndView(ORDER_VIEW, "order", order);
    }
}
