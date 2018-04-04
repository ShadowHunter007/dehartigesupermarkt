package avansivh11.dehartigesupermarkt.service;

import avansivh11.dehartigesupermarkt.model.order.*;
import avansivh11.dehartigesupermarkt.repository.BaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {
    private ArrayList<BaseOrder> sortedFastShippingList;
    private final BaseOrderRepository orderRepository;

    @Autowired
    public OrderService(BaseOrderRepository repo) {
        this.orderRepository = repo;
        //cache the orders from the database in the orderRepository that have a status of 'Received'
        sortedFastShippingList = (ArrayList)repo.getFastShippingOrders();
        //check if orders need to be set to ready to send
        if(sortedFastShippingList.size() >= 5) {
            setOrdersReadyToSend();
        }
    }

    //Warning -> test constructor
    public OrderService(BaseOrderRepository repo, ArrayList<BaseOrder> sortedFastShippingList) {
        this.orderRepository = repo;
        this.sortedFastShippingList = sortedFastShippingList;
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
        if(sortedFastShippingList.size() >= 5){
            setOrdersReadyToSend();
        }
        return order;
    }

    public void setOrdersReadyToSend() {
        assert sortedFastShippingList.size() >= 5;
        int count = 0;
        //sort the list on fastshipping order
        sortedFastShippingList = fastShippingSort(sortedFastShippingList);
        Iterator iter = sortedFastShippingList.iterator();
        //set the first 5 elements of the list with status 'received' to 'sent'
        while(iter.hasNext()) {
            BaseOrder order = (BaseOrder)iter.next();
            if(count < 5 && order.getCurrentState() instanceof OrderReceived) {
                //set the new state
                order.getCurrentState().goNext(order);
                //save to db
                saveOrder(order);
                //delete from the list with orders that are 'recevied'
                iter.remove();
                //another item has its status changed so increment count
                count++;
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
                return 1;
            } else {
                return 0;
            }
        };
        //use the merge sort from java to sort the arrayList
        Collections.sort(orders, orderInstanceComparator);
        //cache the sorted array to avoid unnecessary sorting actions in the future
        sortedFastShippingList = orders;

        return orders;
    }

    public ArrayList<OrderLine> convertOrderLines(HashMap<String, OrderLine> orderLines) {
        ArrayList<OrderLine> orderLineList = new ArrayList<>();
        for(String productName : orderLines.keySet()) {
            orderLineList.add(orderLines.get(productName));
        }
        return orderLineList;
    }
}
