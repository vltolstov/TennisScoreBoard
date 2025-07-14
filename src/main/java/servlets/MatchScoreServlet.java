package servlets;

import dao.HibernateMatchDao;
import dao.MatchDao;
import dto.MatchRequestDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Match;
import models.Score;
import services.MatchScoreService;
import services.MatchService;
import services.OngoingMatchesService;

import java.io.IOException;
import java.util.UUID;

import static utils.MappingUtils.convertToDto;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {

    private final MatchScoreService matchScoreService = new MatchScoreService();
    private final MatchService matchService = new MatchService();
    private final MatchDao matchDao = new HibernateMatchDao();

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

        int pointWinnerId = Integer.parseInt(request.getParameter("point-winner-id"));
        Score score = match.getScore();
        matchScoreService.scoreCalculate(score, match.getFirstPlayer().getId(), match.getSecondPlayer().getId(), pointWinnerId);

        if(matchService.checkGameOver(match)){

            OngoingMatchesService.removeMatch(uuid);
            matchService.setWinner(score, match);
            matchDao.save(match);

            MatchRequestDto matchRequestDto = convertToDto(match);
            request.setAttribute("firstPlayerName", matchRequestDto.getFirstPlayerName());
            request.setAttribute("secondPlayerName", matchRequestDto.getSecondPlayerName());
            request.setAttribute("score", matchRequestDto.getScore());
            request.setAttribute("winnerName", matchRequestDto.getWinnerName());
            request.getRequestDispatcher("winner.jsp").forward(request, response);
        }

        request.getRequestDispatcher("match-score.jsp").forward(request, response);
    }
}
