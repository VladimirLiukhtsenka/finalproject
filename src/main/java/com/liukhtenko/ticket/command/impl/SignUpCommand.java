package com.liukhtenko.ticket.command.impl;

import com.liukhtenko.ticket.command.Command;
import com.liukhtenko.ticket.command.FormParameterName;
import com.liukhtenko.ticket.command.PageMessage;
import com.liukhtenko.ticket.command.PagePath;
import com.liukhtenko.ticket.dao.ColumnName;
import com.liukhtenko.ticket.entity.User;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.UserService;
import com.liukhtenko.ticket.validator.FormValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class SignUpCommand extends Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        if (!FormValidator.isPost(request)) {
            page = PagePath.PAGE_SIGN_UP;
            return page;
        } else {
            String phone = request.getParameter(ColumnName.PHONE);
            String name = request.getParameter(ColumnName.NAME);
            String surName = request.getParameter(ColumnName.SURNAME);
            String fatherName = request.getParameter(ColumnName.FATHER_NAME);
            String gender = request.getParameter(ColumnName.GENDER);
            String password = request.getParameter(ColumnName.PASSWORD);
            String mail = request.getParameter(ColumnName.MAIL);
            Map<String, String> inputMap = new HashMap<>();
            inputMap.put(ColumnName.PHONE, phone); // FIXME: 10.02.2020
            inputMap.put(ColumnName.NAME, name);
            inputMap.put(ColumnName.SURNAME, surName);
            inputMap.put(ColumnName.FATHER_NAME, surName);
            inputMap.put(ColumnName.GENDER, gender);
            inputMap.put(ColumnName.PASSWORD, password);
            inputMap.put(ColumnName.MAIL, mail);
            if (FormValidator.validateSigUpForm(inputMap, request)) {
                try {
                    UserService userService = new UserService();
                    User user = new User();
                    user.setId(FormParameterName.USER_ID);
                    user.setRoleID(FormParameterName.USER_ID);
                    user.setPhone(phone);
                    user.setName(name);
                    user.setSurName(surName);
                    user.setFatherName(fatherName);
                    user.setGender(Byte.parseByte(gender));
                    user.setPassword(password);
                    user.setMail(mail);
                    userService.createUser(user);  // FIXME: 04.02.2020
                    page = PagePath.PAGE_LOGIN;
                    return page;
                } catch (ServiceException e) {
                    request.setAttribute(PageMessage.MESSAGE_ERROR, e.toString()); // FIXME: 09.02.2020
                    page = PagePath.PAGE_SIGN_UP;
                    return page; // FIXME: 28.01.2020
                }
            } else {
                page = PagePath.PAGE_SIGN_UP;
                request.setAttribute(PageMessage.MESSAGE, "Try signUp again");
            }

        }
        return page;
    }

}
