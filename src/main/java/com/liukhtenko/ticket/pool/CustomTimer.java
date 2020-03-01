package com.liukhtenko.ticket.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * The class that runs
 * {@link com.liukhtenko.ticket.pool.Watcher}
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class CustomTimer extends TimerTask {
    private static Logger logger = LogManager.getLogger();

    @Override
    public void run() {
        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<Integer> future = es.submit(new Watcher());
        es.shutdownNow();
        try {
            logger.log(Level.INFO, "Total connections available: " + future.get());
        } catch (ExecutionException | InterruptedException e) {
            logger.log(Level.ERROR, "Unknown number of connections used", e);
        }
    }
}
