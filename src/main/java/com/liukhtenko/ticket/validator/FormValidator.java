package com.liukhtenko.ticket.validator;

import com.liukhtenko.ticket.command.FormParameterName;
import com.liukhtenko.ticket.dao.ColumnName;
import com.liukhtenko.ticket.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * The class that validates forms.
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class FormValidator {
    /**
     * This method checks string data
     *
     * @param value   String to check
     * @param pattern template for checking
     * @return boolean as a result of the method
     */
    public static boolean isValidString(String value, String pattern) {
        return value != null && value.matches(pattern);
    }

    /**
     * This method checks number data
     *
     * @param value number to check
     * @return boolean as a result of the method
     * @throws ServiceException if happen NumberFormatException
     */
    public static boolean isValidNumber(String value) throws ServiceException {
        long number;
        try {
            number = Long.parseLong(value);
            if (number <= 0) {
                throw new ServiceException("Wrong data");
            }
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong data");
        }
        return true;
    }

    /**
     * This method checks number price
     *
     * @param value price to check
     * @return boolean as a result of the method
     * @throws ServiceException if happen NumberFormatException
     */
    public static boolean isValidPrice(String value) throws ServiceException {
        double price;
        try {
            price = Double.parseDouble(value);
            if (price <= 0) {
                throw new ServiceException("Wrong data");
            }
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong data");
        }
        return true;
    }

    /**
     * This method checks date
     *
     * @param value date to check
     * @return boolean as a result of the method
     * @throws ServiceException if happen ParseException
     */
    public static boolean isValidDate(String value) throws ServiceException {
        try {
            DateFormat dateFormat = new SimpleDateFormat(FormParameterName.DATE_FORMAT);
            dateFormat.parse(value);
        } catch (ParseException e) {
            throw new ServiceException("Wrong data");
        }
        return true;
    }

    /**
     * This method defines the request method
     *
     * @param req request from browser
     * @return boolean as a result of the method
     */
    public static boolean isPost(HttpServletRequest req) {
        return req.getMethod().toUpperCase().equals(FormParameterName.POST);
    }

    /**
     * This method checks sign up form
     *
     * @param map     map with user parameters entered
     * @param request request from browser
     * @return boolean as a result of the method
     */
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
