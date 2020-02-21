package com.liukhtenko.ticket.command.supportcommand;

import com.liukhtenko.ticket.command.Command;
import com.liukhtenko.ticket.command.PagePath;

import javax.servlet.http.HttpServletRequest;

public class HomeCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        String page = PagePath.PAGE_INDEX;
        return page;
    }
}
