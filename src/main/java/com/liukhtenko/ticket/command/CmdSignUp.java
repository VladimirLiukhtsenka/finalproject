package com.liukhtenko.ticket.command;

import com.liukhtenko.ticket.entity.User;
import com.liukhtenko.ticket.exception.DaoException;
import com.liukhtenko.ticket.exception.SiteException;
import com.liukhtenko.ticket.service.UserService;
import com.liukhtenko.ticket.validator.CustomPattern;
import com.liukhtenko.ticket.validator.FormValidator;

import javax.servlet.http.HttpServletRequest;

public class CmdSignUp extends Action {
    @Override
    public String execute(HttpServletRequest request) {
        if (!FormValidator.isPost(request)) {
            return null;
        } else {
            UserService userService = new UserService();
            User user = new User();
            try {
                user.setId(1);
                user.setRoleID(2); // FIXME: 27.01.2020
                String phone = request.getParameter("phone");
                if(FormValidator.isValidString(phone,CustomPattern.PHONE)){user.setPhone(phone);}
               // user.setPhone(FormValidator.getString(request, "phone", CustomPattern.PHONE)); // FIXME: 28.01.2020 in CONST названия проправить
                String name = request.getParameter("name");
                if(FormValidator.isValidString(name,CustomPattern.LOGIN)){user.setName(name);}
//                user.setName(FormValidator.getString(request, "name", CustomPattern.LOGIN));
                String surName = request.getParameter("surname");
                if(FormValidator.isValidString(surName,CustomPattern.LOGIN)){user.setSurName(surName);}
//                user.setSurName(FormValidator.getString(request, "surname", CustomPattern.LOGIN));
                String fatherName = request.getParameter("father name");
                if(FormValidator.isValidString(fatherName,CustomPattern.LOGIN)){user.setFatherName(fatherName);}
//                user.setFatherName(FormValidator.getString(request, "father name", CustomPattern.LOGIN));
                String gender = request.getParameter("selectbasicGender");
                if(FormValidator.isValidNumber(gender)){user.setGender(Byte.parseByte(gender));} // FIXME: 28.01.2020
//                user.setGender(FormValidator.getByte(request, "FormValidator"));
                String password = request.getParameter("passwordinput");
                if(FormValidator.isValidString(password,CustomPattern.PASSWORD)){user.setPassword(password);}
//                user.setPassword(FormValidator.getString(request, "passwordinput", CustomPattern.PASSWORD));
                String mail = request.getParameter("textinputMail");
                if(FormValidator.isValidString(mail,CustomPattern.EMAIL)){user.setMail(mail);}
//                user.setMail(FormValidator.getString(request, "textinputMail", CustomPattern.EMAIL));
                if (userService.createUser(user)) {
                    String result = "Login";
                    return result;
//                    return new CmdLogin().execute(request);
                } else {
                    return null;
                }
            } catch (SiteException | DaoException e) {
                return new CmdError().execute(request); // FIXME: 28.01.2020 и форму поправить
            }
        }
    }
}
