package com.liukhtenko.ticket.filter;

        import com.liukhtenko.ticket.command.FormParameterName;
        import com.liukhtenko.ticket.command.PagePath;

        import javax.servlet.*;
        import javax.servlet.annotation.WebFilter;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import javax.servlet.http.HttpSession;
        import java.io.IOException;


@WebFilter(urlPatterns = {"/pages/*"})
public class FilterPageRedirectSecurity implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

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
