package com.liukhtenko.ticket.filter;

import com.liukhtenko.ticket.command.CommandHelper;
import com.liukhtenko.ticket.command.CommandType;
import com.liukhtenko.ticket.command.FormParameterName;
import com.liukhtenko.ticket.command.PagePath;
import com.liukhtenko.ticket.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * The class that defines possible commands depending on the role
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
@WebFilter(urlPatterns = {"/*"}, servletNames = {"FrontController"})
public class FilterRole implements Filter {
    private static Logger logger = LogManager.getLogger();
    private final static Set<CommandType> ALLOW_GUEST = new HashSet<>();
    private final static Set<CommandType> ALLOW_USER = new HashSet<>();
    private final static Set<CommandType> ALLOW_ADMIN = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) {
        ALLOW_GUEST.add(CommandType.ERROR);
        ALLOW_GUEST.add(CommandType.SIGN_UP);
        ALLOW_GUEST.add(CommandType.HOME);
        ALLOW_GUEST.add(CommandType.LOGIN);
        ALLOW_GUEST.add(CommandType.CHANGE_LOCALE);
        ALLOW_GUEST.add(CommandType.VIEW_CONCERTS_EVENT);
        ALLOW_GUEST.add(CommandType.VIEW_FESTIVALS_EVENT);
        ALLOW_GUEST.add(CommandType.VIEW_FOR_CHILDREN_EVENT);
        ALLOW_GUEST.add(CommandType.VIEW_FOR_MOVIE_EVENT);
        ALLOW_GUEST.add(CommandType.VIEW_SPORT_EVENT);
        ALLOW_GUEST.add(CommandType.VIEW_THEATER_EVENT);
        ALLOW_GUEST.add(CommandType.VIEW_TICKET_OFFICE);

        ALLOW_USER.addAll(ALLOW_GUEST);
        ALLOW_USER.remove(CommandType.SIGN_UP);
        ALLOW_USER.remove(CommandType.LOGIN);
        ALLOW_USER.add(CommandType.VIEW_TICKET);
        ALLOW_USER.add(CommandType.BUY_TICKET);
        ALLOW_USER.add(CommandType.PROFILE);
        ALLOW_USER.add(CommandType.UPDATE_USER);
        ALLOW_USER.add(CommandType.UPDATE_PHOTO);

        ALLOW_ADMIN.addAll(ALLOW_USER);
        ALLOW_ADMIN.remove(CommandType.BUY_TICKET);
        ALLOW_ADMIN.add(CommandType.ADMIN_PROFILE);
        ALLOW_ADMIN.add(CommandType.CREATE_EVENT);
        ALLOW_ADMIN.add(CommandType.CREATE_TICKET);
        ALLOW_ADMIN.add(CommandType.DELETE_EVENT);
        ALLOW_ADMIN.add(CommandType.DELETE_TICKET);
        ALLOW_ADMIN.add(CommandType.EDIT_EVENT);
        ALLOW_ADMIN.add(CommandType.EDIT_TICKET);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String reqParameter = req.getParameter(FormParameterName.FORM_PARAM_COMMAND);
        if (reqParameter != null) {
            String nameCommand = reqParameter.toUpperCase();
            CommandType command = CommandType.valueOf(nameCommand);
            User user = CommandHelper.findUserInSession(req);
            long roleId = user.getRoleID();
            switch ((int) roleId) {
                case (FormParameterName.GUEST_ID):
                    if (ALLOW_GUEST.contains(command)) {
                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        logger.log(Level.INFO, "Command is not available for GUEST");
                        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(PagePath.PAGE_LOGIN);
                        dispatcher.forward(req, resp);
                    }
                    break;
                case (FormParameterName.USER_ID):
                    if (ALLOW_USER.contains(command)) {
                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        logger.log(Level.INFO, "Command is not available for USER");
                        resp.sendRedirect(req.getContextPath() + PagePath.PAGE_INDEX);
                    }
                    break;
                case (FormParameterName.ADMIN_ID):
                    if (ALLOW_ADMIN.contains(command)) {
                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        logger.log(Level.INFO, "Command is not available for ADMIN");
                        resp.sendRedirect(req.getContextPath() + PagePath.PAGE_INDEX);
                    }
                    break;
                default:
                    resp.sendRedirect(req.getContextPath() + PagePath.PAGE_INDEX);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
