package servlets;

import dao.HibernateMatchDao;
import dao.HibernatePlayerDao;
import dao.MatchDao;
import dao.PlayerDao;
import dto.PaginationResultDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Match;
import models.Player;
import services.MatchPaginationService;
import utils.ValidationUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {

    private static final int DEFAULT_PAGE_NUMBER = 1;
    private static final int DEFAULT_PAGE_SIZE = 10;
    private final MatchDao matchDao = new HibernateMatchDao();
    private final PlayerDao playerDao = new HibernatePlayerDao();
    private final MatchPaginationService matchPaginationService = new MatchPaginationService(matchDao);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = request.getParameter("page");
        int pageNumber;

        ValidationUtils.validate(page);

        if(page == null) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        } else {
            pageNumber = Integer.parseInt(page);
        }

        String playerName = request.getParameter("filter-by-player-name");

        PaginationResultDto<Match> pageResult;
        if(playerName == null) {
            pageResult = matchPaginationService.getMatchesPage(pageNumber, DEFAULT_PAGE_SIZE);
        } else {
            Optional<Player> optionalPlayer = playerDao.findByName(playerName);
                pageResult = optionalPlayer
                        .map(player -> matchPaginationService.getMatchesPage(pageNumber, DEFAULT_PAGE_SIZE, player))
                        .orElseGet(() -> new PaginationResultDto<>(Collections.emptyList(), pageNumber, 0));
        }

        request.setAttribute("matches", pageResult.getResults());
        request.setAttribute("currentPage", pageResult.getPage());
        request.setAttribute("totalPages", pageResult.getTotalPages());

        request.getRequestDispatcher("matches.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String playerName = request.getParameter("filter-by-player-name");

        response.sendRedirect("matches" + "?filter-by-player-name=" + playerName);
    }
}
