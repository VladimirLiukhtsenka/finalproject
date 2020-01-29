package com.liukhtenko.ticket.command;

import javax.servlet.http.HttpServletRequest;

public abstract class Command {
    public abstract String execute(HttpServletRequest request);
}
