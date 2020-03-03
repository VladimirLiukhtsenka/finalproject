package com.liukhtenko.ticket.pool;

import java.util.Timer;
import java.util.TimerTask;

/**
 * The class that runs
 * {@link com.liukhtenko.ticket.pool.CustomTimer}
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class StartWatcher {
    private static final int PERIOD = 60000;
    private static final int DELAY = 10;
    private static Timer timer;

    /**
     * This method starts
     * <p>
     * {@link CustomTimer}
     */
    public static void start() {
        TimerTask timerTask = new CustomTimer();
        timer = new Timer();
        timer.schedule(timerTask, DELAY, PERIOD);
    }

    /**
     * This method stop
     *
     * @see Watcher
     */
    public static void stop() {
        //terminates this timer,discarding any currently scheduled tasks.
        timer.cancel();
        //removes all cancelled tasks from this timer's task queue.
        timer.purge();
    }
}
