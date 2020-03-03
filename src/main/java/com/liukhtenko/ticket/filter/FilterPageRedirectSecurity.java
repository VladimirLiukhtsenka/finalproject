package com.liukhtenko.ticket.filter;

        import com.liukhtenko.ticket.command.FormParameterName;
        import com.liukhtenko.ticket.command.PagePath;

        import javax.servlet.*;
        import javax.servlet.annotation.WebFilter;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import javax.servlet.http.HttpSession;
        import java.io.IOException;

/**
 * The class that prevents direct browser access to pages.
 * But it allows access from a servlet.
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
@WebFilter(urlPatterns = {"/pages/*"})
public class FilterPageRedirectSecurity implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }
    /**
     * This method is called every time a filter is accessed
     *
     * @param servletRequest  current request
     * @param servletResponse current response
     * @param filterChain     list of all filters
     * @throws IOException      if happen IOException
     * @throws ServletException if happen ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession();
        if (session.getAttribute(FormParameterName.REDIRECT_SECURE) == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + PagePath.PAGE_INDEX);
        } else {
            session.setAttribute(FormParameterName.REDIRECT_SECURE, null);
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
