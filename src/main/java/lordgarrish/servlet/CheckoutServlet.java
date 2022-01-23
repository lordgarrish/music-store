package lordgarrish.servlet;

import lordgarrish.business.*;
import lordgarrish.data.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    //This servlet does various actions like adding user's info and credit card to the database and creating
    //Order object and adding it to the database as well.

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext sc = getServletContext();

        String action = req.getParameter("action");
        if (action == null)
            action = "add";

        String url;
        if(action.equals("edit")) {
            url = "/checkout.jsp";
            sc.getRequestDispatcher(url).forward(req, resp);
        }
        else if(action.equals("add")) {
            url = createCustomer(req);
            sc.getRequestDispatcher(url).forward(req, resp);
        }
        else if(action.equals("confirm")) {
            url = "/payment.jsp";
            sc.getRequestDispatcher(url).forward(req, resp);
        }
        else if(action.equals("addCard")) {
            url = "/music_store_war_exploded/order";
            Customer customer = createOrder(req);
            saveCustomer(customer);

            resp.sendRedirect(url);
        }
    }

    private String createCustomer(HttpServletRequest req) {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String phoneNumber = req.getParameter("phoneNumber");
        String address = req.getParameter("address");
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        String zip = req.getParameter("zip");
        String country = req.getParameter("country");
        Address userAddress = new Address(address, city, state, zip, country);

        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        if(customer == null)
            customer = new Customer();

        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPhoneNumber(phoneNumber);
        customer.setAddress(userAddress);

        session.setAttribute("customer", customer);

        return "/confirm.jsp";
    }

    private Customer createOrder(HttpServletRequest req) {
        String creditCardNumber = req.getParameter("creditCardNumber");
        String creditCardExpirationDate = req.getParameter("creditCardExpirationDate");
        String cvv = req.getParameter("cvv");
        CreditCard card = new CreditCard(creditCardNumber, creditCardExpirationDate, cvv);

        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        if(customer == null) {
            System.err.println("Customer not found in current session");
            customer = new Customer();
        }

        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null) {
            System.err.println("Cart not found in current session");
            cart = new Cart();
        }

        //Makes unique order ID from customer's last name, current date and a random number from 0 to 99
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmm");
        String orderID = customer.getLastName() + "_" + now.format(formatter)+ "_" + (int) (Math.random() * 100);
        Order order = (Order) session.getAttribute("order");
        if(order == null || order.isEmptyOrder()) {
            order = new Order(orderID, cart);
        }
        session.setAttribute("order", order);

        customer.setCreditCard(card);
        customer.setOrder(order);

        return customer;
    }

    private void saveCustomer(Customer customer) {
        AbstractDao<Customer, String> customerDao = CustomerDao.getInstance();
        try {
            customerDao.save(customer);
        } catch (SQLException e) {
            System.err.println("Can't save customer in database");
            e.printStackTrace();
        }
    }
}
