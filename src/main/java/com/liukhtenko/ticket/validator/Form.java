package com.liukhtenko.ticket.validator;

import com.liukhtenko.ticket.exception.SiteException;

import javax.servlet.http.HttpServletRequest;

// FIXME: 24.01.2020 для всех типов проверки
public class Form {
    public static String getString(HttpServletRequest req, String name, String pattern) throws SiteException {
        String value = req.getParameter(name);
        if (value.matches(pattern)) {
            return value;
        }
        throw new SiteException("Incorrect value of " + name);
    }
}
