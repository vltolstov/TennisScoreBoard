package servlets;

import dto.NewMatchRequestDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.ValidationUtils;

import java.io.IOException;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

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

        //пишем базу
        //создаем матч

        response.sendRedirect("match-score.jsp");
    }
}
