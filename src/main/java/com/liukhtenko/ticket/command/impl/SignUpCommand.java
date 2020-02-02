package com.liukhtenko.ticket.command.impl;

import com.liukhtenko.ticket.command.Command;
import com.liukhtenko.ticket.command.PagePath;
import com.liukhtenko.ticket.entity.User;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.UserService;
import com.liukhtenko.ticket.validator.FormRegexValidator;
import com.liukhtenko.ticket.validator.FormValidator;

import javax.servlet.http.HttpServletRequest;

public class SignUpCommand extends Command {
    private static final String FORM_PARAM_PHONE = "phone";
    private static final String FORM_PARAM_NAME = "name";
    private static final String FORM_PARAM_SURNAME = "surname";
    private static final String FORM_PARAM_FATHER_NAME = "father name";
    private static final String FORM_PARAM_GENDER = "gender name";
    private static final String FORM_PARAM_PASSWORD = "password";
    private static final String FORM_PARAM_MAIL = "mail";
    private static final int ID = 1;
    private static final int ROLE_ID = 2;

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        if (!FormValidator.isPost(request)) {
            page = PagePath.PAGE_SIGN_UP;
            return page;
        } else {
            UserService userService = new UserService();
            User user = new User();
            try {
                user.setId(ID);
                user.setRoleID(ROLE_ID);
                String phone = request.getParameter(FORM_PARAM_PHONE);
                if (FormValidator.isValidString(phone, FormRegexValidator.PHONE)) { // FIXME: 02.02.2020 upgrade validate
                    user.setPhone(phone);
                }
                String name = request.getParameter(FORM_PARAM_NAME);
                if (FormValidator.isValidString(name, FormRegexValidator.LOGIN)) {
                    user.setName(name);
                }
                String surName = request.getParameter(FORM_PARAM_SURNAME);
                if (FormValidator.isValidString(surName, FormRegexValidator.LOGIN)) {
                    user.setSurName(surName);
                }
                String fatherName = request.getParameter(FORM_PARAM_FATHER_NAME);
                if (FormValidator.isValidString(fatherName, FormRegexValidator.LOGIN)) {
                    user.setFatherName(fatherName);
                }
                String gender = request.getParameter(FORM_PARAM_GENDER);
                if (FormValidator.isValidNumber(gender)) {
                    user.setGender(Byte.parseByte(gender));
                }
                String password = request.getParameter(FORM_PARAM_PASSWORD);
                if (FormValidator.isValidString(password, FormRegexValidator.PASSWORD)) {
                    user.setPassword(password);
                }
                String mail = request.getParameter(FORM_PARAM_MAIL);
                if (FormValidator.isValidString(mail, FormRegexValidator.EMAIL)) {
                    user.setMail(mail);
                }
                if (userService.createUser(user)) {
                    page = PagePath.PAGE_LOGIN;
                    return page;
                } else {
                    return page;
                }
            } catch (ServiceException e) {
                 return PagePath.PAGE_ERROR; // FIXME: 28.01.2020 и форму поправить
            }
        }
    }
}
