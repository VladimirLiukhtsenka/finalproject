package com.liukhtenko.ticket.command;

         import com.liukhtenko.ticket.entity.User;

         import javax.servlet.http.HttpServletRequest;

public class CommandHelper { // FIXME: 02.02.2020 Rename
    private static final String ATTRIBUTE_USER_KEY = "user";

    public static User findUserInSession(HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute(ATTRIBUTE_USER_KEY);
        if (user == null)
            return null;
        return user;
    }
}
