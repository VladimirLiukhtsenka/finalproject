package com.liukhtenko.ticket.filter;

import com.liukhtenko.ticket.command.*;
import com.liukhtenko.ticket.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@WebFilter(urlPatterns = {"/*"}, servletNames = {"FrontController"})
public class FilterRole implements Filter {
    static Logger logger = LogManager.getLogger();
    private final static Set<CommandFactory.Command> ALLOW_GUEST = new HashSet<>();
    private final static Set<CommandFactory.Command> ALLOW_USER = new HashSet<>();
    private final static Set<CommandFactory.Command> ALLOW_ADMIN = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ALLOW_GUEST.add(CommandFactory.Command.ERROR);
        ALLOW_GUEST.add(CommandFactory.Command.SIGN_UP);
        ALLOW_GUEST.add(CommandFactory.Command.HOME);
        ALLOW_GUEST.add(CommandFactory.Command.LOGIN);
        ALLOW_GUEST.add(CommandFactory.Command.VIEW_CONCERTS_EVENT);
        ALLOW_GUEST.add(CommandFactory.Command.VIEW_FESTIVALS_EVENT);
        ALLOW_GUEST.add(CommandFactory.Command.VIEW_FOR_CHILDREN_EVENT);
        ALLOW_GUEST.add(CommandFactory.Command.VIEW_FOR_MOVIE_EVENT);
        ALLOW_GUEST.add(CommandFactory.Command.VIEW_SPORT_EVENT);
        ALLOW_GUEST.add(CommandFactory.Command.VIEW_THEATER_EVENT);
        ALLOW_GUEST.add(CommandFactory.Command.VIEW_TICKET_OFFICE);

        ALLOW_USER.addAll(ALLOW_GUEST);
        ALLOW_USER.remove(CommandFactory.Command.SIGN_UP);
        ALLOW_USER.remove(CommandFactory.Command.LOGIN);
        ALLOW_USER.add(CommandFactory.Command.VIEW_TICKET);
        ALLOW_USER.add(CommandFactory.Command.BUY_TICKET);
        ALLOW_USER.add(CommandFactory.Command.PROFILE);

        ALLOW_ADMIN.addAll(ALLOW_USER);
        ALLOW_ADMIN.remove(CommandFactory.Command.BUY_TICKET);
        ALLOW_ADMIN.add(CommandFactory.Command.ADMIN_PROFILE);
        ALLOW_ADMIN.add(CommandFactory.Command.CREATE_EVENT);
        ALLOW_ADMIN.add(CommandFactory.Command.CREATE_TICKET);
        ALLOW_ADMIN.add(CommandFactory.Command.DELETE_EVENT);
        ALLOW_ADMIN.add(CommandFactory.Command.DELETE_TICKET);
        ALLOW_ADMIN.add(CommandFactory.Command.EDIT_EVENT);
        ALLOW_ADMIN.add(CommandFactory.Command.EDIT_TICKET);

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        String s = req.getParameter("command");
        if (s != null) {
            String nameCommand = s.toUpperCase();
            CommandFactory.Command command = CommandFactory.Command.valueOf(nameCommand);
            User user = CommandHelper.findUserInSession(req);
            long roleId = user.getRoleID();
            switch ((int) roleId) {
                case (FormParameterName.GUEST_ID):
                    if (ALLOW_GUEST.contains(command)) {
                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        logger.log(Level.INFO, "Command is not available for GUEST");
                        resp.sendRedirect(req.getContextPath() + PagePath.PAGE_LOGIN);
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
