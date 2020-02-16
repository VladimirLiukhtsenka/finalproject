package com.liukhtenko.ticket.command;

import com.liukhtenko.ticket.entity.Event;
import com.liukhtenko.ticket.entity.User;

import javax.servlet.http.HttpServletRequest;
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
    public static void ViewEvents(HttpServletRequest request, List <Event> listAllEvents){
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
        request.setAttribute(FormParameterName.FORM_PARAM_EVENTS, eventsToPage);
        request.setAttribute(FormParameterName.FORM_PARAM_COUNT_PAGES, noOfPages);
        request.setAttribute(FormParameterName.FORM_PARAM_CURRENT_PAGE, page);
    }
}
