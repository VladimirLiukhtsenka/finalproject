package com.liukhtenko.ticket.command;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// FIXME: 03.03.2020 
/**
 * The class that finds a command on request.
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class CommandProvider {
    /**
     *This method defines the command
     * @param cmd command name
     * @return real command
     * @see com.liukhtenko.ticket.controller.FrontController
     */
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
