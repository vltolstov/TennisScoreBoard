package servlets;

import dao.HibernateMatchDao;
import dao.MatchDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Match;
import utils.ValidationUtils;

import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {

    private static final int DEFAULT_PAGE_NUMBER = 1;
    private final MatchDao matchDao = new HibernateMatchDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = request.getParameter("page");
        String filterByPlayerName = request.getParameter("filter_by_player_name");
        int pageNumber;
        List<Match> matches;

        ValidationUtils.validate(page);
        if(page == null) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        } else {
            pageNumber = Integer.parseInt(page);
        }

        // + валидация имени игрока
        if(filterByPlayerName == null) {
            matches = matchDao.findAll();
        }

        matches = matchDao.findAll();
        //matches = matchDao.findByPlayer();

        request.setAttribute("matches", matches);

        request.getRequestDispatcher("matches.jsp").forward(request, response);
    }
}
