package lordgarrish.business;

import java.io.Serializable;
import java.util.*;

//Java Bean class representing customer's order
public class Order implements Serializable {

    private String orderID;
    private final List<LineItem> items;

    public Order() { items = new ArrayList<>(); }

    public Order(String orderID, Cart cart) {
        this.orderID = orderID;
        items = new ArrayList<>(cart.getItems());
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public List<LineItem> getItems() {
        return items;
    }

    public boolean isEmptyOrder() {
        return items.isEmpty();
    }
}
