package lordgarrish.data;

import lordgarrish.business.*;

import java.util.*;
import java.sql.*;

//Data access class for adding customers, credit cards and orders to the database
public class CustomerDB {

    public static void addCustomer(Customer customer) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Address userAddress = customer.getAddress();

        String query = "INSERT INTO customers (first_name, last_name, email, address, city, state, zip, country, phone_number) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection connection = pool.getConnection()) {
            PreparedStatement stat = connection.prepareStatement(query);
            stat.setString(1, customer.getFirstName());
            stat.setString(2, customer.getLastName());
            stat.setString(3, customer.getEmail());
            stat.setString(4, userAddress.getAddress());
            stat.setString(5, userAddress.getCity());
            stat.setString(6, userAddress.getState());
            stat.setString(7, userAddress.getZip());
            stat.setString(8, userAddress.getCountry());
            stat.setString(9, customer.getPhoneNumber());
            int r = stat.executeUpdate();
            System.out.println(r + " row(s) inserted in 'customers' table");
        } catch (SQLException throwables) {
            System.err.println("Cant add user to DB");
            throwables.printStackTrace();
        }
    }

    public static void addCreditCard(Customer customer) {
        ConnectionPool pool = ConnectionPool.getInstance();

        String query = "INSERT INTO credit_cards (credit_card_number, credit_card_expiration_date, cvv, customer_id) " +
                "VALUES (?, ?, ?, (SELECT customer_id FROM customers WHERE email = ?))";

        try(Connection connection = pool.getConnection()) {
            PreparedStatement stat = connection.prepareStatement(query);
            CreditCard card = customer.getCreditCard();
            stat.setString(1, card.getCreditCardNumber());
            stat.setString(2, card.getCreditCardExpirationDate());
            stat.setString(3, card.getCvv());
            stat.setString(4, customer.getEmail());
            int r = stat.executeUpdate();
            System.out.println(r + " row(s) inserted in 'credit_cards' table");
        } catch (SQLException throwables) {
            System.err.println("Cant add credit card to DB");
            throwables.printStackTrace();
        }
    }

    public static void addOrder(Customer customer) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Order order = customer.getOrder();

        String ordersQuery = "INSERT INTO orders (order_id, customer_id) " +
                "VALUES (?, (SELECT customer_id FROM customers WHERE email = ?))";

        //Get query for 'orders_items' join table
        String ordersItemsQuery = createOrdersItemsQuery(order);

        //Insert order into 'orders' table and order's items in 'orders_items' table
        //(join table bc it's many-to-many relationship)
        try(Connection connection = pool.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement stat = connection.prepareStatement(ordersQuery);
            stat.setString(1, order.getOrderID());
            stat.setString(2, customer.getEmail());
            int r1 = stat.executeUpdate();
            stat = connection.prepareStatement(ordersItemsQuery);
            int r2 = stat.executeUpdate();
            connection.commit();
            System.out.println(r1 + " row(s) inserted in 'orders' table");
            System.out.println(r2 + " row(s) inserted in 'orders_items' table");
        } catch (SQLException throwables) {
            System.err.println("Cant add orders and albums id's to DB");
            throwables.printStackTrace();
        }
    }

    //Constructs query for 'orders_items' join table
    private static String createOrdersItemsQuery(Order order) {
        String head = "INSERT INTO orders_items (order_id, album_id, quantity) VALUES ";
        List<LineItem> items = order.getItems();
        String orderID = order.getOrderID();
        StringBuilder builder = new StringBuilder(head);
        for(LineItem item : items) {
            MusicAlbum album = item.getAlbum();
            String productCode = album.getCode();
            int quantity = item.getQuantity();
            String subquery = "('" + orderID + "', (SELECT album_id FROM albums WHERE code = '" + productCode + "'), "
                    + quantity + "),";
            builder.append(subquery);
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }
}
