package com.liukhtenko.ticket.command;

import javax.servlet.http.HttpServletRequest;

public class CommandSignUp implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return "/signup.jsp";
    }
}
