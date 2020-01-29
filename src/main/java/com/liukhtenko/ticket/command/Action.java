package com.liukhtenko.ticket.command;

import javax.servlet.http.HttpServletRequest;

public abstract class Action {
    public abstract String execute(HttpServletRequest request);
//    public String getJsp() {
//        return "/" + this.toString() + ".jsp";
//    }

//    @Override
//    public String toString() {
//        String nameCommand = this
//                .getClass()
//                .getSimpleName()
//                .replace("Command", "")   // FIXME: 28.01.2020
//                .toLowerCase();
//        return nameCommand;
//    }
}
