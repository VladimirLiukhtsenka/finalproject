package com.liukhtenko.ticket.command.impl;

import com.liukhtenko.ticket.command.Command;
import com.liukhtenko.ticket.command.FormParameterName;
import com.liukhtenko.ticket.command.PageMessage;
import com.liukhtenko.ticket.command.PagePath;
import com.liukhtenko.ticket.dao.ColumnName;
import com.liukhtenko.ticket.entity.User;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.UserService;
import com.liukhtenko.ticket.validator.FormRegexValidator;
import com.liukhtenko.ticket.validator.FormValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SignUpCommand extends Command {
    static Logger logger = LogManager.getLogger();

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
                user.setId(FormParameterName.USER_ID);
                user.setRoleID(FormParameterName.USER_ID);
                String phone = request.getParameter(ColumnName.PHONE);
                if (FormValidator.isValidString(phone, FormRegexValidator.PHONE)) { // FIXME: 02.02.2020 upgrade validate
                    user.setPhone(phone);
                }
                String name = request.getParameter(ColumnName.NAME);
                if (FormValidator.isValidString(name, FormRegexValidator.LOGIN)) {
                    user.setName(name);
                }
                String surName = request.getParameter(ColumnName.SURNAME);
                if (FormValidator.isValidString(surName, FormRegexValidator.LOGIN)) {
                    user.setSurName(surName);
                }
                String fatherName = request.getParameter(ColumnName.FATHER_NAME);
                if (FormValidator.isValidString(fatherName, FormRegexValidator.LOGIN)) {
                    user.setFatherName(fatherName);
                }
                String gender = request.getParameter(ColumnName.GENDER);
                if (FormValidator.isValidNumber(gender)) {
                    user.setGender(Byte.parseByte(gender));
                }
                String password = request.getParameter(ColumnName.PASSWORD);
                if (FormValidator.isValidString(password, FormRegexValidator.PASSWORD)) {
                    user.setPassword(password);
                }
                String mail = request.getParameter(ColumnName.MAIL);
                if (FormValidator.isValidString(mail, FormRegexValidator.EMAIL)) {
                    user.setMail(mail);
                }
                if (userService.createUser(user)) { // FIXME: 04.02.2020 
                    page = PagePath.PAGE_LOGIN;
                    return page;
                }
            } catch (ServiceException e) {
                HttpSession session = request.getSession();
                session.setAttribute(PageMessage.MESSAGE_ERROR, e.toString()); // FIXME: 09.02.2020
                page = PagePath.PAGE_SIGN_UP;
                return page; // FIXME: 28.01.2020
            }
        }
        return page;
    }
}
