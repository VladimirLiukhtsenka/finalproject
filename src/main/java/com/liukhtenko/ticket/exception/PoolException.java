package com.liukhtenko.ticket.exception;

/**
 * Exception pool class.
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class PoolException extends RuntimeException {

    public PoolException(String message) {
        super(message);
    }

    public PoolException() {
        super();
    }

    public PoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public PoolException(Throwable cause) {
        super(cause);
    }
}
