package avansivh11.dehartigesupermarkt.service;

import avansivh11.dehartigesupermarkt.model.order.*;
import avansivh11.dehartigesupermarkt.repository.BaseOrderRepository;
import com.sun.org.apache.bcel.internal.generic.FASTORE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

@Service
public class OrderService {
    private ArrayList<BaseOrder> sortedFastShippingList;

    @Autowired
    private final BaseOrderRepository orderRepository;

    //make a workaround for testing
    public OrderService(BaseOrderRepository repo) {
        this.orderRepository = repo;
        //cache the orders from the database in the orderRepository that have a status of 'Received'
        sortedFastShippingList = (ArrayList)repo.getFastShippingOrders();
        //check if orders need to be set to ready to send
        if(sortedFastShippingList.size() >= 5) {
            setOrdersReadyToSend();
        }
    }

    public ArrayList<BaseOrder> getOrders() {
        return (ArrayList<BaseOrder>) this.orderRepository.findAll();
    }

    public BaseOrder getOrderById(Long id) {
        return this.orderRepository.findOne(id);
    }

    public BaseOrder saveOrder(BaseOrder order) {
        this.orderRepository.save(order);
        //cache immediatly to avoid extra database accesses if needed
        if(order.getCurrentState() instanceof OrderReceived) {
            sortedFastShippingList.add(order);
        }

        //check if there are now enough entries to send the next badge of orders

        return order;
    }

    public int checkOrderReceivedAmount() {
        //check if there are engough orders on status received to see if a new badge of orders can be sent
        int count = 0;
        for(BaseOrder currentOrder : sortedFastShippingList) {
            if(currentOrder.getCurrentState() instanceof OrderReceived) {
                count++;
            }
        }
        return count;
    }

    public void setOrdersReadyToSend() {
        assert sortedFastShippingList.size() >= 5;
        //sort the list on fastshipping order
        sortedFastShippingList = fastShippingSort(sortedFastShippingList);
        //set the first 5 elements of the list with status 'received' to 'sent'
        for(int i = 0;  i < sortedFastShippingList.size(); i++) {
            if(sortedFastShippingList.get(i).getCurrentState() instanceof OrderReceived) {
                BaseOrder order = sortedFastShippingList.get(i);
                //set the new state
                order.getCurrentState().goNext(order);
                //save to db
                saveOrder(order);
                //delete from the list with orders that are 'recevied'
                remove(order);
            }
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

    //removes the specified entry from the sortedFastShippingList
    private void remove(BaseOrder order) {
        sortedFastShippingList.remove(order);
    }
}
