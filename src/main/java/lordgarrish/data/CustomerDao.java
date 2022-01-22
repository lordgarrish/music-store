package lordgarrish.data;

import lordgarrish.business.*;

import java.sql.*;
import java.util.*;

public class CustomerDao implements AbstractDao<Customer, String> {
    private static AbstractDao<Customer, String> customerDao;

    private CustomerDao() {}

    public static synchronized AbstractDao<Customer, String> getInstance() {
        if(customerDao == null) {
            customerDao = new CustomerDao();
        }
        return customerDao;
    }

    @Override
    public boolean save(Customer customer) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try(Connection connection = pool.getConnection()) {
            this.addCustomer(customer, connection);
            this.addCreditCard(customer, connection);
            this.addOrder(customer, connection);
        }
        return true;
    }

    @Override
    public Customer update(Customer customer) throws SQLException {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public boolean delete(String id) throws SQLException {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Customer getById(String id) throws SQLException {
        throw new UnsupportedOperationException("Not implemented");
    }

    private void addCustomer(Customer customer, Connection connection) throws SQLException {
        Address userAddress = customer.getAddress();

        String query = "INSERT INTO customers (first_name, last_name, email, address, city, state, zip, country, phone_number) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try(PreparedStatement stat = connection.prepareStatement(query)) {
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
        }
    }

    private void addCreditCard(Customer customer, Connection connection) throws SQLException {
        String query = "INSERT INTO credit_cards (credit_card_number, credit_card_expiration_date, cvv, customer_id) " +
                "VALUES (?, ?, ?, (SELECT customer_id FROM customers WHERE email = ?))";

        try(PreparedStatement stat = connection.prepareStatement(query)) {
            CreditCard card = customer.getCreditCard();
            stat.setString(1, card.getCreditCardNumber());
            stat.setString(2, card.getCreditCardExpirationDate());
            stat.setString(3, card.getCvv());
            stat.setString(4, customer.getEmail());
            int r = stat.executeUpdate();
            System.out.println(r + " row(s) inserted in 'credit_cards' table");
        }
    }

    private void addOrder(Customer customer, Connection connection) throws SQLException {
        Order order = customer.getOrder();
        String ordersQuery = "INSERT INTO orders (order_id, customer_id) " +
                "VALUES (?, (SELECT customer_id FROM customers WHERE email = ?))";
        //Get query for 'orders_items' join table
        String ordersItemsQuery = this.createOrdersItemsQuery(order);

        //Insert order into 'orders' table and order's items in 'orders_items' table
        //(join table bc it's many-to-many relationship)
        int r1, r2;
        connection.setAutoCommit(false);
        try(PreparedStatement stat = connection.prepareStatement(ordersQuery)) {
            stat.setString(1, order.getOrderID());
            stat.setString(2, customer.getEmail());
            r1 = stat.executeUpdate();
        }
        try(PreparedStatement stat = connection.prepareStatement(ordersItemsQuery)) {
            r2 = stat.executeUpdate();
        }
        connection.commit();
        System.out.println(r1 + " row(s) inserted in 'orders' table");
        System.out.println(r2 + " row(s) inserted in 'orders_items' table");
    }

    //Constructs query for 'orders_items' join table
    private String createOrdersItemsQuery(Order order) {
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
