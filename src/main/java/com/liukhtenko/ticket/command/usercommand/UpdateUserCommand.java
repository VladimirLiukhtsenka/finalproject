package com.liukhtenko.ticket.command.usercommand;

import com.liukhtenko.ticket.command.*;
import com.liukhtenko.ticket.dao.ColumnName;
import com.liukhtenko.ticket.entity.User;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.TicketService;
import com.liukhtenko.ticket.service.impl.UserService;
import com.liukhtenko.ticket.validator.FormRegexValidator;
import com.liukhtenko.ticket.validator.FormValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UpdateUserCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        User user = CommandHelper.findUserInSession(request);
        // HttpSession session = request.getSession();
        request.setAttribute(FormParameterName.TYPE_METHOD,FormParameterName.GET);
        String page;
        if (!FormValidator.isPost(request)) {
            page = PagePath.PAGE_UPDATE_USER;
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
                page=PagePath.PAGE_PROFILE;
            } catch (ServiceException e) {
                logger.log(Level.INFO, "Incorrect data" + e.toString());
                request.setAttribute(PageMessage.MESSAGE_ERROR, "Incorrect data");
                page = PagePath.PAGE_UPDATE_USER;
            }
        }
        return page;
    }
}
