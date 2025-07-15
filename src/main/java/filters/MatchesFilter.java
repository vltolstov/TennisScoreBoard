package filters;

import exceptions.DatabaseOperationException;
import exceptions.PageNumberException;
import exceptions.PaginationException;
import exceptions.PlayerNameException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/matches")
public class MatchesFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            super.doFilter(request, response, chain);
        }
        catch (PaginationException | PageNumberException e){
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        }
    }

}
