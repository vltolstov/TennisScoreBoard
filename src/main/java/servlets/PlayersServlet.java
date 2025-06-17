package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.HibernatePlayerDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Player;

import java.io.IOException;
import java.util.List;

@WebServlet("/players")
public class PlayersServlet extends HttpServlet {

    private final HibernatePlayerDao hibernatePlayerDao = new HibernatePlayerDao();
    private final ObjectMapper mapper = new ObjectMapper();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Player> players = hibernatePlayerDao.findAll();

        response.getWriter().write(mapper.writeValueAsString(players));

    }

}
