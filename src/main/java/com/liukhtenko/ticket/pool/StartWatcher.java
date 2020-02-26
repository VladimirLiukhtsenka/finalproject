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

    public static void start() {
        TimerTask timerTask = new CustomTimer();
        Timer timer = new Timer(true);
        timer.schedule(timerTask, DELAY, PERIOD);
    }
}
