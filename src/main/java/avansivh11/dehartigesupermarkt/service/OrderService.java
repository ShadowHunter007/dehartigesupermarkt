package avansivh11.dehartigesupermarkt.service;

import avansivh11.dehartigesupermarkt.model.order.*;
import avansivh11.dehartigesupermarkt.repository.BaseOrderRepository;
import com.sun.org.apache.bcel.internal.generic.FASTORE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {
    private ArrayList<BaseOrder> sortedFastShippingList = new ArrayList<>();

    @Autowired
    private final BaseOrderRepository orderRepository;

    public OrderService(BaseOrderRepository repo) {
        this.orderRepository = repo;
    }

    public ArrayList<BaseOrder> getOrders() {
        return (ArrayList<BaseOrder>) this.orderRepository.findAll();
    }

    public BaseOrder getOrderById(Long id) {
        return this.orderRepository.findOne(id);
    }

    public BaseOrder saveOrder(BaseOrder order) {
        this.orderRepository.save(order);
        //update the cached sortedFastShippingList
        fastShippingSort(getOrders());
        return order;
    }

    public void setOrdersReadyToSend() {
        assert sortedFastShippingList.size() >= 5;
        assert sortedFastShippingList.get(4) instanceof FastShippingOrder;

        for(int i =0; i < 5; i++) {
            sortedFastShippingList.get(i).getCurrentState().goNext(sortedFastShippingList.get(i));
        }
    }

    public ArrayList<BaseOrder> fastShippingSort(ArrayList<BaseOrder> orders) {
        //construct a comparator based on BaseOrder
        //check the instance per order and return the right value for input for the merge sort
        Comparator<BaseOrder> orderInstanceComparator = (o1, o2) -> {
            if(o1 instanceof FastShippingOrder && !(o2 instanceof FastShippingOrder)) {
                return -1;
            } else if(!(o1 instanceof FastShippingOrder) && o2 instanceof FastShippingOrder) {
                return -1;
            } else if(!(o1 instanceof FastShippingOrder) && !(o2 instanceof FastShippingOrder)) {
                return 1;
            } else
            return 0;
        };
        //use the merge sort from java to sort the arrayList
        Collections.sort(orders, orderInstanceComparator);
        //cache the sorted array to avoid unnecessary sorting actions in the future
        sortedFastShippingList = orders;

        return orders;
    }
}
