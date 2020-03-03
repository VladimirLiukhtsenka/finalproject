package com.liukhtenko.ticket.command.supportcommand;

import com.liukhtenko.ticket.command.Command;
import com.liukhtenko.ticket.command.FormParameterName;
import com.liukhtenko.ticket.command.PagePath;

import javax.servlet.http.HttpServletRequest;

/**
 * The class that displays the index page.
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class HomeCommand implements Command {
    /**
     *
     * @param request from browser
     * @return String page
     *
     */
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(FormParameterName.TYPE_METHOD, FormParameterName.GET);
        return PagePath.PAGE_INDEX;
    }
}
