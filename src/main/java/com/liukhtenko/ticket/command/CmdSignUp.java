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
            user.setId(1);
            user.setRoleID(2);
            user.setPhone(Form.getString(request, "phone", CustomPattern.Phone));
            user.setName(Form.getString(request, "name", CustomPattern.Login));
            user.setSurName(Form.getString(request, "surname", CustomPattern.Login));
            user.setFatherName(Form.getString(request, "father name", CustomPattern.Login));
            user.setGender(Form.getByte(request, "selectbasicGender"));
            user.setPassword(Form.getString(request, "passwordinput", CustomPattern.Password));
            user.setMail(Form.getString(request, "textinputMail", CustomPattern.Email));
          if(userService.createUser(user)){
              return Actions.LOGIN.command;
          }
            else {return null;}
        } catch (SiteException |DaoException e ) {
            return Actions.ERROR.command;
        }
    }
}