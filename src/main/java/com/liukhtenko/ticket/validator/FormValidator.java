package com.liukhtenko.ticket.validator;

import com.liukhtenko.ticket.exception.ServiceException;


import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// FIXME: 24.01.2020 1.для всех типов проверки пофиксить 2.ServiceException?? 3.Можно в сервис валидацию
public class FormValidator {

    public static boolean isValidString(String value, String pattern) {
        return value != null && value.matches(pattern);
    }

    public static boolean isValidNumber(String value) throws ServiceException {
        try {
            Long.parseLong(value);
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong data");
        }
        return true;
    }
    public static boolean isValidDate(String value) throws ServiceException {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date moment = dateFormat.parse(value);
        } catch (ParseException e) {
            throw new ServiceException("Wrong data");
        }
        return true;
    }



    public static boolean isPost(HttpServletRequest req) {
        return req.getMethod().toUpperCase().equals("POST");
    }
}
