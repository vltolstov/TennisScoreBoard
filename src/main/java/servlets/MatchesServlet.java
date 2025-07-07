package servlets;

import dao.HibernateMatchDao;
import dao.HibernatePlayerDao;
import dao.MatchDao;
import dao.PlayerDao;
import exceptions.PlayerNameException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Match;
import models.Player;
import utils.ValidationUtils;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {

    private static final int DEFAULT_PAGE_NUMBER = 1;
    private final MatchDao matchDao = new HibernateMatchDao();
    private final PlayerDao playerDao = new HibernatePlayerDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Match> matches;

        String page = request.getParameter("page");
        int pageNumber;

        ValidationUtils.validate(page);

        if(page == null) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        } else {
            pageNumber = Integer.parseInt(page);
        }

        String playerName = request.getParameter("filter_by_player_name");
        if(playerName == null) {
            matches = matchDao.findAll();
        } else {
            Optional<Player> optionalPlayer = Optional.empty();
            optionalPlayer = playerDao.findByName(playerName);
            Player player = optionalPlayer.orElseThrow(() -> new PlayerNameException("Player not found"));
            matches = matchDao.findByPlayer(player);
        }

        request.setAttribute("matches", matches);
        request.getRequestDispatcher("matches.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String playerName = request.getParameter("filter_by_player_name");

        response.sendRedirect("matches" + "?filter_by_player_name=" + playerName);
    }
}
