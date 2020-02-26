package com.liukhtenko.ticket.command;

import com.liukhtenko.ticket.entity.Event;
import com.liukhtenko.ticket.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
/**
 * The class for service commands.
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class CommandHelper {
    private static final String ATTRIBUTE_USER_KEY = "user";
    private static final double FLOATING_ONE_NUMBER = 1.0;
    private static final String POST_COMMAND_SIGN_UP = "SIGN_UP";
    private static final String POST_COMMAND_BUY_TICKET = "BUY_TICKET";
    private static final String POST_COMMAND_CREATE_EVENT = "CREATE_EVENT";
    private static final String POST_COMMAND_DELETE_EVENT = "DELETE_EVENT";
    private static final String POST_COMMAND_CREATE_TICKET = "CREATE_TICKET";
    private static final String POST_COMMAND_DELETE_TICKET = "DELETE_TICKET";
    private static final String GET_COMMAND_LOGIN = "do?command=Login";
    private static final String GET_COMMAND_PROFILE = "do?command=Profile";
    private static final String GET_COMMAND_EDIT_EVENT = "do?command=Edit_event";
    private static final String GET_COMMAND_EDIT_TICKET = "do?command=Edit_ticket";
    private static final String GET_COMMAND_ERROR = "do?command=Error";

    public static User findUserInSession(HttpServletRequest req) {
        User user = new User();
        if (req.getSession().getAttribute(ATTRIBUTE_USER_KEY) != null) {
            user = (User) req.getSession().getAttribute(ATTRIBUTE_USER_KEY);
        }
        return user;
    }

    public static void viewEvents(HttpServletRequest request, List<Event> listAllEvents) {
        HttpSession session = request.getSession();
        int page = FormParameterName.FIRST_PAGE;
        int recordsPerPage = FormParameterName.RECORDS_PER_PAGE;
        if (request.getParameter(FormParameterName.FORM_PARAM_PAGE) != null) {
            page = Integer.parseInt(request.getParameter(FormParameterName.FORM_PARAM_PAGE));
        }
        int start = (page - FormParameterName.FIRST_PAGE) * recordsPerPage;
        List<Event> eventsToPage = new ArrayList<>();
        for (int i = start; i < start + recordsPerPage && i < listAllEvents.size(); i++) {
            eventsToPage.add(listAllEvents.get(i));
        }
        int records = listAllEvents.size();
        int pages = (int) Math.ceil(records * FLOATING_ONE_NUMBER / recordsPerPage);
        session.setAttribute(FormParameterName.FORM_PARAM_EVENTS, eventsToPage);
        session.setAttribute(FormParameterName.FORM_PARAM_COUNT_PAGES, pages);
        session.setAttribute(FormParameterName.FORM_PARAM_CURRENT_PAGE, page);
    }

    public static String findRedirectCommand(String nameCommand) {
        String redirectCommand;
        switch (nameCommand) {
            case (POST_COMMAND_SIGN_UP):
                redirectCommand = GET_COMMAND_LOGIN;
                break;
            case (POST_COMMAND_BUY_TICKET):
                redirectCommand = GET_COMMAND_PROFILE;
                break;
            case (POST_COMMAND_CREATE_EVENT):
            case (POST_COMMAND_DELETE_EVENT):
                redirectCommand = GET_COMMAND_EDIT_EVENT;
                break;
            case (POST_COMMAND_CREATE_TICKET):
            case (POST_COMMAND_DELETE_TICKET):
                redirectCommand = GET_COMMAND_EDIT_TICKET;
                break;
            default:
                redirectCommand = GET_COMMAND_ERROR;
        }
        return redirectCommand;
    }
}
