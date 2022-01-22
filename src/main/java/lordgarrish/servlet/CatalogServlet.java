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
import java.sql.SQLException;

@WebServlet("/catalog/*")
public class CatalogServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Displays product info on product page
        String requestURI = req.getRequestURI();
        String url = "/product_page.jsp";
        String productCode = requestURI.substring(requestURI.lastIndexOf('/') + 1);

        AbstractDao<MusicAlbum, String> albumDao = AlbumDao.getInstance();
        MusicAlbum album = null;
        try {
            album = albumDao.getById(productCode);
        } catch (SQLException e) {
            System.err.println("Can't get album " + productCode + " from DB");
            e.printStackTrace();
        }

        if(album == null) {
            System.out.println("Album " + productCode + " is null");
            url = "/404.jsp";
        }

        HttpSession session = req.getSession();
        session.setAttribute("album", album);
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }
}
