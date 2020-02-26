package com.liukhtenko.ticket.command;
/**
 * The class that finds a command on request.
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
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
