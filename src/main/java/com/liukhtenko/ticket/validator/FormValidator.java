package com.liukhtenko.ticket.validator;

import com.liukhtenko.ticket.command.FormParameterName;
import com.liukhtenko.ticket.dao.ColumnName;
import com.liukhtenko.ticket.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

// FIXME: 24.01.2020 ServiceException?? 3.Можно в сервис валидацию
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
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// FIXME: 02.02.2020 in CONST
            Date moment = dateFormat.parse(value);
        } catch (ParseException e) {
            throw new ServiceException("Wrong data");
        }
        return true;
    }


    public static boolean isPost(HttpServletRequest req) {
        return req.getMethod().toUpperCase().equals("POST");
    }

    public static boolean validateSigUpForm(Map<String, String> map, HttpServletRequest request) {
        boolean flag = true;
        String phone = map.get(ColumnName.PHONE);
        if (FormValidator.isValidString(phone, FormRegexValidator.PHONE)) {
            request.setAttribute(ColumnName.PHONE, phone);
        } else {
            request.setAttribute(FormParameterName.FORM_PARAM_ERROR_PHONE, "wrong data!");
            flag = false;
        }
        String name = map.get(ColumnName.NAME);
        if (FormValidator.isValidString(name, FormRegexValidator.LOGIN)) {
            request.setAttribute(ColumnName.NAME, name);
        } else {
            request.setAttribute(FormParameterName.FORM_PARAM_ERROR_NAME, "wrong data!");
            flag = false;
        }
        String surName = map.get(ColumnName.SURNAME);
        if (FormValidator.isValidString(surName, FormRegexValidator.LOGIN)) {
            request.setAttribute(ColumnName.SURNAME, surName);
        } else {
            request.setAttribute(FormParameterName.FORM_PARAM_ERROR_SURNAME, "wrong data!");
            flag = false;
        }
        String fatherName = map.get(ColumnName.FATHER_NAME);
        if (FormValidator.isValidString(fatherName, FormRegexValidator.LOGIN)) {
            request.setAttribute(ColumnName.FATHER_NAME, fatherName);
        } else {
            request.setAttribute(FormParameterName.FORM_PARAM_ERROR_FATHER_NAME, "wrong data!");
            flag = false;
        }
        String gender = map.get(ColumnName.GENDER);
        request.setAttribute(ColumnName.GENDER, gender);
        String password = map.get(ColumnName.PASSWORD);
        if (FormValidator.isValidString(password, FormRegexValidator.PASSWORD)) {
            request.setAttribute(ColumnName.PASSWORD, password);
        } else {
            request.setAttribute(FormParameterName.FORM_PARAM_ERROR_PASSWORD, "wrong data!");
            flag = false;
        }
        String mail = map.get(ColumnName.MAIL);
        if (FormValidator.isValidString(mail, FormRegexValidator.EMAIL)) {
            request.setAttribute(ColumnName.MAIL, mail);
        } else {
            request.setAttribute(FormParameterName.FORM_PARAM_ERROR_MAIL, "wrong data!");
            flag = false;
        }
        return flag;
    }
}
