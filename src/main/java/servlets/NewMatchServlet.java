package servlets;

import dao.HibernatePlayerDao;
import dao.PlayerDao;
import dto.NewMatchRequestDto;
import dto.PlayerDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Match;
import models.Player;
import services.OngoingMatchesService;
import utils.MappingUtils;
import utils.ValidationUtils;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    private final PlayerDao playerDao = new HibernatePlayerDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("new-match.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstPlayerName = request.getParameter("first-player-name");
        String secondPlayerName = request.getParameter("second-player-name");

        NewMatchRequestDto newMatchRequestDto = new NewMatchRequestDto(firstPlayerName, secondPlayerName);

        ValidationUtils.validate(newMatchRequestDto);

        PlayerDto firstPlayerDto = new PlayerDto(firstPlayerName);
        PlayerDto secondPlayerDto = new PlayerDto(secondPlayerName);

        Player firstPlayer = playerDao.save(MappingUtils.convertToEntity(firstPlayerDto));
        Player secondPlayer = playerDao.save(MappingUtils.convertToEntity(secondPlayerDto));

        UUID uuid = UUID.randomUUID();

        Match match = new Match();
        match.setFirstPlayer(firstPlayer);
        match.setSecondPlayer(secondPlayer);

        OngoingMatchesService.addMatch(uuid, match);

        response.sendRedirect("match-score" + "?uuid=" + uuid);
    }
}
