package lordgarrish.business;

import java.io.Serializable;

//Java Bean class representing customer's order
public class Order implements Serializable {

    private String orderID;
    private Cart cart; //Every order has its own cart of items

    public Order() {}

    public Order(String orderID, Cart cart) {
        this.orderID = orderID;
        this.cart = cart;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public boolean isEmptyOrder() {
        return cart == null || cart.isEmptyCart();
    }
}
