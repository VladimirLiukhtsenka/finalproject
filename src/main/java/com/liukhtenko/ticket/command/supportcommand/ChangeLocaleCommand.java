package com.liukhtenko.ticket.command.supportcommand;

import com.liukhtenko.ticket.command.Command;
import com.liukhtenko.ticket.command.FormParameterName;
import com.liukhtenko.ticket.command.PagePath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The class that changes the locale .
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class ChangeLocaleCommand implements Command {
    private String locale = FormParameterName.LOCALE_EN;

    /**
     * @param request from browser
     * @return String page
     */
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(FormParameterName.TYPE_METHOD, FormParameterName.GET);
        if (request.getParameter(FormParameterName.LANGUAGE_EN) != null) {
            locale = FormParameterName.LOCALE_EN;
        }
        if (request.getParameter(FormParameterName.LANGUAGE_RU) != null) {
            locale = FormParameterName.LOCALE_RU;
        }
        HttpSession session = request.getSession();
        session.setAttribute(FormParameterName.FORM_PARAM_LOCALE, locale);
        return PagePath.PAGE_INDEX;
    }
}
