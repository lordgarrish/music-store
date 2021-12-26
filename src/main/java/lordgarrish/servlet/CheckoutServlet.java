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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    //This servlet does various actions like adding user's info and credit card to the database and also creates
    //Order object and adds to the database as well. Sorry for spaghetti code, I'm looking forward to doing it more
    //concise

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
            url = createOrder(req);
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

        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        if(customer == null)
            customer = new Customer();

        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPhoneNumber(phoneNumber);
        customer.setAddress(address);
        customer.setCity(city);
        customer.setState(state);
        customer.setZip(zip);
        customer.setCountry(country);

        session.setAttribute("customer", customer);

        return "/confirm.jsp";
    }

    private String createOrder(HttpServletRequest req) {
        String creditCardNumber = req.getParameter("creditCardNumber");
        String creditCardExpirationDate = req.getParameter("creditCardExpirationDate");
        String cvv = req.getParameter("cvv");

        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        if(customer == null) {
            System.err.println("Customer not found in 'createOrder' method");
            return "/error_page.jsp";
        }
        CreditCard card = new CreditCard();
        card.setCreditCardNumber(creditCardNumber);
        card.setCreditCardExpirationDate(creditCardExpirationDate);
        card.setCvv(cvv);

        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null) {
            System.err.println("Cart not found in 'createOrder' method");
            return "/error_page.jsp";
        }
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmm");
        //Makes order id from customer's last name, current date and a random number from 0 to 99
        String orderID = customer.getLastName() + "_" + now.format(formatter)+ "_" + (int) (Math.random() * 100);
        Order order = (Order) session.getAttribute("order");
        if(order == null || order.isEmptyOrder()) {
            order = new Order(orderID, cart);
        }
        session.setAttribute("order", order);

        customer.setCreditCard(card);
        customer.setOrder(order);

        CustomerDB.addCustomer(customer);
        CustomerDB.addCreditCard(customer);
        CustomerDB.addOrder(customer);

        return "/music_store_war_exploded/order";
    }
}
