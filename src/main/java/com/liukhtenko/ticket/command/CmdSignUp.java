package com.liukhtenko.ticket.command;

import com.liukhtenko.ticket.entity.User;
import com.liukhtenko.ticket.exception.DaoException;
import com.liukhtenko.ticket.exception.SiteException;
import com.liukhtenko.ticket.service.UserService;
import com.liukhtenko.ticket.validator.CustomPattern;
import com.liukhtenko.ticket.validator.Form;

import javax.servlet.http.HttpServletRequest;

public class CmdSignUp extends Action {
    @Override
    public Action execute(HttpServletRequest request) {
        UserService userService = new UserService();
        User user = new User();
        try {
            user.setName(Form.getString(request,"name", CustomPattern.Login));
        } catch (SiteException e) {
            return Actions.ERROR.command;
        }
        try {
            userService.createUser(user);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return null;
    }
}
