package com.liukhtenko.ticket.command.usercommand;

import com.liukhtenko.ticket.command.Command;
import com.liukhtenko.ticket.command.FormParameterName;
import com.liukhtenko.ticket.command.PagePath;
import com.liukhtenko.ticket.dao.ColumnName;
import com.liukhtenko.ticket.entity.User;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.UserService;
import com.liukhtenko.ticket.validator.EncryptionPassword;
import com.liukhtenko.ticket.validator.FormValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * The class allows the guest to sign up.
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class SignUpCommand implements Command {
    private static Logger logger = LogManager.getLogger();
    /**
     *
     * @param request from browser
     * @return String page
     *
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        request.setAttribute(FormParameterName.TYPE_METHOD, FormParameterName.GET);
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
            inputMap.put(ColumnName.PHONE, phone);
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
                    password = EncryptionPassword.encrypt(password);
                    user.setPassword(password);
                    user.setMail(mail);
                    userService.createUser(user);
                    page = PagePath.PAGE_LOGIN;
                    request.setAttribute(FormParameterName.TYPE_METHOD, FormParameterName.POST);
                } catch (ServiceException e) {
                    request.setAttribute(FormParameterName.FORM_PARAM_MESSAGE_ERROR, "Impossible to signUp.");
                    logger.log(Level.WARN, "Error in SignUpCommand", e);
                    page = PagePath.PAGE_SIGN_UP;
                    return page;
                }
            } else {
                page = PagePath.PAGE_SIGN_UP;
            }
        }
        return page;
    }

}
