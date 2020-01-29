package com.liukhtenko.ticket.command;

import com.liukhtenko.ticket.PagePath;
import com.liukhtenko.ticket.entity.User;
import com.liukhtenko.ticket.exception.DaoException;
import com.liukhtenko.ticket.exception.SiteException;
import com.liukhtenko.ticket.service.UserService;
import com.liukhtenko.ticket.validator.CustomPattern;
import com.liukhtenko.ticket.validator.FormValidator;

import javax.servlet.http.HttpServletRequest;

public class SignUpCommand extends Action {
    private static final String FORM_LABEL_PHONE = "phone";
    private static final String FORM_LABEL_NAME = "name";
    private static final String FORM_LABEL_SURNAME = "surname";
    private static final String FORM_LABEL_FATHER_NAME = "father name";
    private static final String FORM_LABEL_GENDER = "gender name";
    private static final String FORM_LABEL_PASSWORD = "password";
    private static final String FORM_LABEL_MAIL = "mail";

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
                user.setId(1);
                user.setRoleID(2); // FIXME: 27.01.2020
                String phone = request.getParameter(FORM_LABEL_PHONE);
                if(FormValidator.isValidString(phone,CustomPattern.PHONE)){user.setPhone(phone);}
                String name = request.getParameter(FORM_LABEL_NAME);
                if(FormValidator.isValidString(name,CustomPattern.LOGIN)){user.setName(name);}
                String surName = request.getParameter(FORM_LABEL_SURNAME);
                if(FormValidator.isValidString(surName,CustomPattern.LOGIN)){user.setSurName(surName);}
                String fatherName = request.getParameter(FORM_LABEL_FATHER_NAME);
                if(FormValidator.isValidString(fatherName,CustomPattern.LOGIN)){user.setFatherName(fatherName);}
                String gender = request.getParameter(FORM_LABEL_GENDER);
                if(FormValidator.isValidNumber(gender)){user.setGender(Byte.parseByte(gender));}
                String password = request.getParameter(FORM_LABEL_PASSWORD);
                if(FormValidator.isValidString(password,CustomPattern.PASSWORD)){user.setPassword(password);}
                String mail = request.getParameter(FORM_LABEL_MAIL);
                if(FormValidator.isValidString(mail,CustomPattern.EMAIL)){user.setMail(mail);}
                if (userService.createUser(user)) {
//                    String result = "Login";
                    page = PagePath.PAGE_LOGIN;
                    return page;
                } else {
                    return page;
                }
            } catch (SiteException | DaoException e) {
//                return new ErrorCommand().execute(request); // FIXME: 28.01.2020 и форму поправить
                return PagePath.PAGE_ERROR;
            }
        }
    }
}
