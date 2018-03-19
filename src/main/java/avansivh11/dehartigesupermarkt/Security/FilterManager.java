package avansivh11.dehartigesupermarkt.Security;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FilterManager {
    public static void process(HttpServletRequest request,
                               HttpServletResponse response, FrontCommand frontCommand) throws IOException, ServletException {
        FilterChain filterChain = new FilterChainImplementation(
                new AuthenticationFilter(), new LoginCounterFilter());
        filterChain.doFilter(request, response);
    }
}
