package com.liukhtenko.ticket.command;

public class CommandProvider {
    public static Command defineFrom(String cmd) {
        CommandType commandType;
        try {
            commandType = CommandType.valueOf(cmd);
        } catch (IllegalArgumentException e) {
            return CommandType.ERROR.command;
        }
        switch (commandType) {
            case LOGIN:
                return CommandType.LOGIN.command;
            case SIGN_UP:
                return CommandType.SIGN_UP.command;
            case PROFILE:
                return CommandType.PROFILE.command;
            case VIEW_TICKET:
                return CommandType.VIEW_TICKET.command;
            case CREATE_EVENT:
                return CommandType.CREATE_EVENT.command;
            case VIEW_CONCERTS_EVENT:
                return CommandType.VIEW_CONCERTS_EVENT.command;
            case VIEW_FESTIVALS_EVENT:
                return CommandType.VIEW_FESTIVALS_EVENT.command;
            case VIEW_SPORT_EVENT:
                return CommandType.VIEW_SPORT_EVENT.command;
            case VIEW_THEATER_EVENT:
                return CommandType.VIEW_THEATER_EVENT.command;
            case VIEW_FOR_CHILDREN_EVENT:
                return CommandType.VIEW_FOR_CHILDREN_EVENT.command;
            case VIEW_FOR_MOVIE_EVENT:
                return CommandType.VIEW_FOR_MOVIE_EVENT.command;
            case VIEW_TICKET_OFFICE:
                return CommandType.VIEW_TICKET_OFFICE.command;
            case DELETE_EVENT:
                return CommandType.DELETE_EVENT.command;
            case EDIT_EVENT:
                return CommandType.EDIT_EVENT.command;
            case HOME:
                return CommandType.HOME.command;
            case ADMIN_PROFILE:
                return CommandType.ADMIN_PROFILE.command;
            case CREATE_TICKET:
                return CommandType.CREATE_TICKET.command;
            case EDIT_TICKET:
                return CommandType.EDIT_TICKET.command;
            case DELETE_TICKET:
                return CommandType.DELETE_TICKET.command;
            case BUY_TICKET:
                return CommandType.BUY_TICKET.command;
            case UPDATE_USER:
                return CommandType.UPDATE_USER.command;
            default:
                return CommandType.ERROR.command;
        }
    }
}
