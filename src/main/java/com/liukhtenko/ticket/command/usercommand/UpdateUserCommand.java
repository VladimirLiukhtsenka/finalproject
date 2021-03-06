package com.liukhtenko.ticket.command.usercommand;

import com.liukhtenko.ticket.command.Command;
import com.liukhtenko.ticket.command.CommandHelper;
import com.liukhtenko.ticket.command.FormParameterName;
import com.liukhtenko.ticket.command.PagePath;
import com.liukhtenko.ticket.dao.ColumnName;
import com.liukhtenko.ticket.entity.User;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.UserService;
import com.liukhtenko.ticket.validator.FormRegexValidator;
import com.liukhtenko.ticket.validator.FormValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The class allows the user to update their personal data.
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class UpdateUserCommand implements Command {
    private static Logger logger = LogManager.getLogger();
    /**
     *
     * @param request from browser
     * @return String page
     *
     */
    @Override
    public String execute(HttpServletRequest request) {
        User user = CommandHelper.findUserInSession(request);
        request.setAttribute(FormParameterName.TYPE_METHOD, FormParameterName.GET);
        String page =  PagePath.PAGE_UPDATE_USER;
        if (!FormValidator.isPost(request)) {
            return page;
        } else {
            try {
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
                String mail = request.getParameter(ColumnName.MAIL);
                if (FormValidator.isValidString(mail, FormRegexValidator.EMAIL)) {
                    user.setMail(mail);
                }
                String phone = request.getParameter(ColumnName.PHONE);
                if (FormValidator.isValidString(phone, FormRegexValidator.PHONE)) {
                    user.setPhone(phone);
                }
                UserService userService = new UserService();
                userService.updateUser(user);
            } catch (ServiceException e) {
                logger.log(Level.INFO, "Incorrect data" + e.toString());
                request.setAttribute(FormParameterName.FORM_PARAM_MESSAGE_ERROR, "Incorrect data");
            }
        }
        return page;
    }
}
