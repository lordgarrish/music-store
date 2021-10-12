package lordgarrish.servlet;

import lordgarrish.business.*;
import lordgarrish.data.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@WebServlet("/catalog/*")
public class CatalogServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        PrintWriter out = resp.getWriter();
//        out.println("TEST CatalogServlet");

        String requestURI = req.getRequestURI();
        String url = "/product_page.jsp";
        String productCode = requestURI.substring(requestURI.lastIndexOf('/') + 1);
        //out.println(productCode);
        MusicAlbum album = AlbumDB.selectAlbum(productCode);

        if(album == null) {
            System.out.println("Album is null");
            url = "/404.jsp";
        }

        HttpSession session = req.getSession();
        session.setAttribute("album", album);
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }
}
