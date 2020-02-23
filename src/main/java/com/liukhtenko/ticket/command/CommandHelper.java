package com.liukhtenko.ticket.command;

import com.liukhtenko.ticket.entity.Event;
import com.liukhtenko.ticket.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class CommandHelper {
    private static final String ATTRIBUTE_USER_KEY = "user";

    public static User findUserInSession(HttpServletRequest req) {
        User user = new User();
        if (req.getSession().getAttribute(ATTRIBUTE_USER_KEY) != null) {
            user = (User) req.getSession().getAttribute(ATTRIBUTE_USER_KEY);
        }
        return user;
    }
    public static void viewEvents(HttpServletRequest request, List <Event> listAllEvents){
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
        int noOfRecords = listAllEvents.size();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        session.setAttribute(FormParameterName.FORM_PARAM_EVENTS, eventsToPage);
        session.setAttribute(FormParameterName.FORM_PARAM_COUNT_PAGES, noOfPages);
        session.setAttribute(FormParameterName.FORM_PARAM_CURRENT_PAGE, page);
    }

    public static String findRedirectCommand (String nameCommand){
        String redirectCommand;
        switch (nameCommand){
            case ("SIGN_UP"):
                redirectCommand = "do?command=Login";
            break;
            case ("BUY_TICKET"):
                redirectCommand = "do?command=Profile";
                break;
            case ("CREATE_EVENT"):
            case ("DELETE_EVENT"):
                redirectCommand = "do?command=Edit_event";
                break;
            case ("CREATE_TICKET"):
            case ("DELETE_TICKET"):
                redirectCommand = "do?command=Edit_ticket";
                break;
            default:
                redirectCommand = "do?command=Edit_event";
        }
        return redirectCommand;
    }
}
