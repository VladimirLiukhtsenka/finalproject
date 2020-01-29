package com.liukhtenko.ticket.command;

import com.liukhtenko.ticket.PagePath;
import com.liukhtenko.ticket.entity.User;
import com.liukhtenko.ticket.exception.DaoException;
import com.liukhtenko.ticket.exception.SiteException;
import com.liukhtenko.ticket.service.UserService;
import com.liukhtenko.ticket.validator.CustomPattern;
import com.liukhtenko.ticket.validator.FormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand extends Action {
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        if (!FormValidator.isPost(request)) {
//            return null;
            page = PagePath.PAGE_LOGIN;
            return page;
        } else {
            try {
                String mail = FormValidator.getString(request,"textinputMail",CustomPattern.EMAIL);
                String password = FormValidator.getString(request,"passwordinput",CustomPattern.PASSWORD);
                UserService userService = new UserService();
                User user = userService.findUserByMailAndPassword(mail, password);
                if(user.getMail() !=null){
                    HttpSession session =request.getSession();
                    session.setAttribute("user",user.getName());
//                    String result = "Profile";
                    page = PagePath.PAGE_PROFILE;
                    return page;
//                    return new ProfileCommand().execute(request);
                }else {
                    request.setAttribute(Message.MESSAGE," Wrong data");
                    return page;
                }
            } catch (SiteException | DaoException e) {
                e.printStackTrace(); // FIXME: 27.01.2020
                request.setAttribute(Message.MESSAGE_ERROR,e.toString()); // FIXME: 27.01.2020
                page = null;
            }
        }
        return page;
    }
}