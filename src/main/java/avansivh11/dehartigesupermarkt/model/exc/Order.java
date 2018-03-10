package avansivh11.dehartigesupermarkt.model;

import avansivh11.dehartigesupermarkt.service.State;

import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
public class Order extends BaseOrder{
    private ArrayList<OrderLine> orderLines;
    private Customer customer;
    private double totalPrice;
    private State state;

    public Order() {}
    public Order(ArrayList<OrderLine> orderLines, Customer customer, double totalPrice, State state) {
        super();
        this.orderLines = orderLines;
        this.customer = customer;
        this.totalPrice = totalPrice;
    }

    public ArrayList<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(ArrayList<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
