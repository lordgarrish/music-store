package lordgarrish.business;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.*;

//Java Bean class representing customer's order
public class Order implements Serializable {

    private String orderID;
    private final List<LineItem> items;

    public Order() { items = new ArrayList<>(); }

    public Order(String orderID, Cart cart) {
        this.orderID = orderID;
        items = cart.getItems();
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public List<LineItem> getItems() {
        return new ArrayList<>(items);
    }

    public boolean isEmptyOrder() {
        return items.isEmpty();
    }

    public String getTotalPrice() {
        double totalPrice = 0.0;
        for(LineItem item : items) {
            totalPrice += item.getTotal();
        }
        DecimalFormat decFormat = new DecimalFormat();
        decFormat.setMaximumFractionDigits(2);
        return decFormat.format(totalPrice);
    }
}
