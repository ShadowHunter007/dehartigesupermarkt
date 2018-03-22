package avansivh11.dehartigesupermarkt.Security;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class LoginCounterFilter implements Filter {
    private static Set<String> users = new HashSet<>();


    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession(false);
        Optional.ofNullable(session.getAttribute("username"))
                .map(Object::toString)
                .ifPresent(users::add);
        request.setAttribute("counter", users.size());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
