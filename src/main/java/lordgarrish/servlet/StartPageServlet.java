package lordgarrish.servlet;

import lordgarrish.business.*;
import lordgarrish.data.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/catalog", "/news"})
public class StartPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String url;

        if(requestURI.endsWith("catalog")) {
            url = showCatalog(req, resp);
        }
        else if(requestURI.endsWith("news")){
            url = "/404.jsp"; //News page in development
        } else {
            url = "/404.jsp";
        }

        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }

    private String showCatalog(HttpServletRequest req, HttpServletResponse resp) {
        //Shows store catalog on '/catalog' page
        String url = "/catalog.jsp";

        //Get albums from DB
        List<MusicAlbum> albumsList = AlbumDB.selectAlbums();
        HttpSession session = req.getSession();
        session.setAttribute("albums", albumsList);

        return url;
    }
}
