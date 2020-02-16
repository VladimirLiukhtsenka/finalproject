package com.liukhtenko.ticket.command.impl;

import com.liukhtenko.ticket.command.Command;
import com.liukhtenko.ticket.command.PagePath;

import javax.servlet.http.HttpServletRequest;

public class HomeCommand extends Command {

    @Override
    public String execute(HttpServletRequest request) {
        String page = PagePath.PAGE_INDEX;
        return page;
    }
}
