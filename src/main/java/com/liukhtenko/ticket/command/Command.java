package com.liukhtenko.ticket.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface for processing requests.
 * Each request will be handled by a class that implements this interface.
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public interface Command {
    /**
     * @param request from browser
     * @return String page
     */
    String execute(HttpServletRequest request);
}
