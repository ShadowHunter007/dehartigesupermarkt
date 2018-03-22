package avansivh11.dehartigesupermarkt.Security;

import javax.servlet.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

public class FilterChainImplementation implements FilterChain {
    private Iterator<Filter> filters;

    public FilterChainImplementation(Filter... filters) {
        this.filters = Arrays.asList(filters).iterator();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        if (filters.hasNext()) {
            Filter filter = filters.next();
            filter.doFilter(request, response, this);
        }
    }
}