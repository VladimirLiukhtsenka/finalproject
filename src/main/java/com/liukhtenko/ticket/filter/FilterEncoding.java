package com.liukhtenko.ticket.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * The class that checks and sets the required encoding
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
@WebFilter(urlPatterns = {"/*"}, initParams =
        {@WebInitParam(name = "encoding", value = "UTF-8", description = "encoding param")})
public class FilterEncoding implements Filter {
    private String code;

    @Override
    public void init(FilterConfig filterConfig) {
        code = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String currentEncoding = servletRequest.getCharacterEncoding();
        if (code != null && !code.equalsIgnoreCase(currentEncoding)) {
            servletRequest.setCharacterEncoding(code);
        }
        currentEncoding = servletResponse.getCharacterEncoding();
        if (code != null && !code.equalsIgnoreCase(currentEncoding)) {
            servletResponse.setCharacterEncoding(code);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        code = null;
    }
}
