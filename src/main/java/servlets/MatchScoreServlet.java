package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Match;
import models.Score;
import services.MatchScoreService;
import services.OngoingMatchesService;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {

    private final MatchScoreService matchScoreService = new MatchScoreService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UUID uuid = UUID.fromString(request.getParameter("uuid"));
        Match match = OngoingMatchesService.getMatch(uuid);

        request.setAttribute("uuid", uuid.toString());
        request.setAttribute("match", match);

        request.getRequestDispatcher("match-score.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UUID uuid = UUID.fromString(request.getParameter("uuid"));
        Match match = OngoingMatchesService.getMatch(uuid);

        request.setAttribute("uuid", uuid.toString());
        request.setAttribute("match", match);

        String pointWinnerId = request.getParameter("point-winner-id");
        Score score = match.getScore();

        if(Integer.parseInt(pointWinnerId) == match.getFirstPlayer().getId()) {
            score.setFirstPlayerPoints(score.getFirstPlayerPoints() + 1);
        } else {
            score.setSecondPlayerPoints(score.getSecondPlayerPoints() + 1);
        }

        request.getRequestDispatcher("match-score.jsp").forward(request, response);

    }
}
