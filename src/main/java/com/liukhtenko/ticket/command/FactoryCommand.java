package com.liukhtenko.ticket.command;

import javax.servlet.http.HttpServletRequest;

public class FactoryCommand {
    private static CmdLogin login = new CmdLogin();
    private static CmdLogOut logOut = new CmdLogOut();
    private static CmdSignUp signUp = new CmdSignUp();
    private static CmdProfile profile = new CmdProfile();
    private static CmdCreateEvent createEvent = new CmdCreateEvent();
    private static CmdViewEvent viewEvent = new CmdViewEvent();
    private static CmdError error = new CmdError();

    private enum Command {
        LOGIN,
        LOGOUT,
        SIGNUP,
        PROFILE,
        CREATEEVENT,
        VIEWEVENT,
        ERROR
    }

    public static Action defineFrom(String cmd) {
        Command command;
        try {
            command = Command.valueOf(cmd);
        } catch (IllegalArgumentException e) {
            return new CmdError();
        }
        switch (command) {
            case LOGIN:
                return login;
            case LOGOUT:
                return logOut;
            case SIGNUP:
                return signUp;
            case PROFILE:
                return profile;
            case VIEWEVENT:
                return viewEvent;
            case CREATEEVENT:
                return createEvent;
            default:
                return error;
        }
    }
}
