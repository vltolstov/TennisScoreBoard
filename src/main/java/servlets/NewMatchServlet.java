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
import models.Player;
import utils.MappingUtils;
import utils.ValidationUtils;

import java.io.IOException;
import java.util.List;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    private final PlayerDao playerDao = new HibernatePlayerDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("new-match.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String playerOneName = request.getParameter("player-one-name");
        String playerTwoName = request.getParameter("player-two-name");

        NewMatchRequestDto newMatchRequestDto = new NewMatchRequestDto(playerOneName, playerTwoName);

        ValidationUtils.validate(newMatchRequestDto);

        PlayerDto playerOneDto = new PlayerDto(playerOneName);
        PlayerDto playerTwoDto = new PlayerDto(playerTwoName);

        Player playerOne = playerDao.save(MappingUtils.convertToEntity(playerOneDto));
        Player playerTwo = playerDao.save(MappingUtils.convertToEntity(playerTwoDto));



//        List<Player> playerFromDb = playerDao.findAll();
//        System.out.println(playerFromDb);
//        создаем матч

        response.sendRedirect("match-score.jsp");
    }
}
