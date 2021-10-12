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

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        PrintWriter out = resp.getWriter();
//        out.println("CartServlet TEST");
        String url = "/cart.jsp";
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        session.setAttribute("cart", cart);
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext sc = getServletContext();

        String action = request.getParameter("action");
        if (action == null) action = "cart";

        String url = "/catalog.jsp";
        if (action.equals("shop")) url = "/catalog.jsp";
        else if (action.equals("cart")) {
            String productCode = request.getParameter("productCode");
            String quantityString = request.getParameter("quantity");

            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null)
                cart = new Cart();

            int quantity;
            try {
                quantity = Integer.parseInt(quantityString);
                if (quantity < 0) quantity = 1;
            } catch (NumberFormatException nfe) {
                quantity = 1;
            }

            MusicAlbum album = AlbumDB.selectAlbum(productCode);

            LineItem lineItem = new LineItem();
            lineItem.setAlbum(album);
            lineItem.setQuantity(quantity);
            if (quantity > 0) {
                cart.addItem(lineItem);
            } else if (quantity == 0) {
                cart.removeItem(lineItem);
            }

            session.setAttribute("cart", cart);
            url = "/cart.jsp";
        }

        else if(action.equals("checkout")) {
            url = "/checkout.jsp";
        }

        sc.getRequestDispatcher(url).forward(request, response);
    }
}
