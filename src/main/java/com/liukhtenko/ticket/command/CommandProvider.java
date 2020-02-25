package com.liukhtenko.ticket.command;

public class CommandProvider {
    public static Command defineFrom(String cmd) {
        CommandType commandType;
        try {
            commandType = CommandType.valueOf(cmd);
        } catch (IllegalArgumentException e) {
            return CommandType.ERROR.command;
        }
        return commandType.getCommand();
    }
}
