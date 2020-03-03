package com.liukhtenko.ticket.pool;

import java.util.concurrent.Callable;

/**
 * The class that counts the number of available connections.
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class Watcher implements Callable<Integer> {
    /**
     * This method counts the number of available connections
     *
     * @return umber of available connections
     */
    @Override
    public Integer call() {
        CustomConnectionPool connectionPool = CustomConnectionPool.INSTANCE;
        Integer result = connectionPool.getFreeConnectionsSize() + connectionPool.getGivenAwayConnectionsSize();
        return result;
    }
}
