package com.liukhtenko.ticket.pool;

        import java.util.concurrent.Callable;

public class Watcher implements Callable<Integer> {
    @Override
    public Integer call() {
        CustomConnectionPool connectionPool = CustomConnectionPool.INSTANCE;
        Integer result = connectionPool.freeConnections.size() + connectionPool.givenAwayConnections.size();
        return result;
    }
}
