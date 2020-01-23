package com.liukhtenko.ticket.command;

import javax.servlet.http.HttpServletRequest;

public class CommandLogOut implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return "/logout.jsp";
    }
}
