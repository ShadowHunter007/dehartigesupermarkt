package avansivh11.dehartigesupermarkt.service;

import avansivh11.dehartigesupermarkt.model.order.BaseOrder;
import avansivh11.dehartigesupermarkt.repository.BaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderService {
    @Autowired
    private final BaseOrderRepository orderRepository;

    public OrderService(BaseOrderRepository repo) {
        this.orderRepository = repo;
    }

    public ArrayList<BaseOrder> getOrders() {
        return (ArrayList<BaseOrder>) this.orderRepository.findAll();
    }

    public BaseOrder getOrderById(Long id) { return this.orderRepository.findOne(id);}
}
