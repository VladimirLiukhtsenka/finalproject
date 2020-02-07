package com.liukhtenko.ticket.command;

import com.liukhtenko.ticket.entity.User;

import javax.servlet.http.HttpServletRequest;

public class CommandHelper {
    private static final String ATTRIBUTE_USER_KEY = "user";

    public static User findUserInSession(HttpServletRequest req) {
        User user = new User();
        if (req.getSession().getAttribute(ATTRIBUTE_USER_KEY) != null) {
            user = (User) req.getSession().getAttribute(ATTRIBUTE_USER_KEY);
        }
        return user;
    }
}
