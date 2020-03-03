
package com.liukhtenko.ticket.filter;

import com.liukhtenko.ticket.command.FormParameterName;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The class that checks and sets the required locale
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
@WebFilter(urlPatterns = {"/*"})
public class FilterLocale implements Filter {
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
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain
            filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession();
        if (session.getAttribute(FormParameterName.FORM_PARAM_LOCALE) == null) {
            session.setAttribute(FormParameterName.FORM_PARAM_LOCALE, FormParameterName.LOCALE_RU);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void destroy() {

    }
}
