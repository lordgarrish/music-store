package lordgarrish.servlet;

import lordgarrish.business.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Displays order info
        String url = "/order_info.jsp";
        ServletContext sc = getServletContext();
        HttpSession session = req.getSession();
        Order order = (Order) session.getAttribute("order");
        if(order == null) {
            order = new Order();
        }
        session.setAttribute("order", order);
        Cart cart = (Cart) session.getAttribute("cart");
        cart.removeAllItemsFromCart();
        session.setAttribute("cart", cart);
        sc.getRequestDispatcher(url).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
