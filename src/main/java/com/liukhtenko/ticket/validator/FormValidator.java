package com.liukhtenko.ticket.validator;

import com.liukhtenko.ticket.exception.SiteException;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// FIXME: 24.01.2020 для всех типов проверки
public class FormValidator {
    public static String getString(HttpServletRequest req, String name) throws SiteException {
        return getString(req, name, ".*");
    }

    public static boolean isValidString(String value, String pattern) throws SiteException {
        if (value != null && value.matches(pattern)) {
            return true;
        } else {
            throw new SiteException("Wrong data");
        }
    }

    public static boolean isValidNumber(String value) throws SiteException {
        try {
            Long.parseLong(value);
        } catch (NumberFormatException e) {
            throw new SiteException("Wrong data");
        }
        return true;
    }
    public static boolean isValidDate(String value) throws SiteException {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date moment = dateFormat.parse(value);
        } catch (ParseException e) {
            throw new SiteException("Wrong data");
        }
        return true;
    }
    public static String getString(HttpServletRequest req, String name, String pattern) throws SiteException {
        String value = req.getParameter(name);
        if (value != null && value.matches(pattern)) {
            return value;
        }
        throw new SiteException("Incorrect value of " + name);
    }

    public static byte getByte(HttpServletRequest req, String name) throws SiteException {
        try {
            return Byte.parseByte(req.getParameter(name));
        } catch (Exception e) {
            throw new SiteException("Incorrect value of " + name, e);
        }
    }


    public static long getLong(HttpServletRequest req, String name) throws SiteException {
        try {
            return Long.parseLong(req.getParameter(name));
        } catch (Exception e) {
            throw new SiteException("Incorrect value of " + name, e);
        }
    }

    public static boolean isPost(HttpServletRequest req) {
        return req.getMethod().toUpperCase().equals("POST");
    }
}
