package filters;

import exceptions.DatabaseOperationException;
import exceptions.PlayerNameException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/new-match")
public class NewMatchFormFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            super.doFilter(request, response, chain);
        }
        catch (PlayerNameException e){
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("new-match.jsp").forward(request, response);
        }
        catch (DatabaseOperationException e){
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("new-match.jsp").forward(request, response);
        }
    }
}
