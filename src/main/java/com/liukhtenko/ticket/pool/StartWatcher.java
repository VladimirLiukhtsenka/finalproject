package com.liukhtenko.ticket.pool;

import java.util.Timer;
import java.util.TimerTask;

public class StartWatcher {
    private static final int PERIOD = 60000;
    private static final int DELAY = 10;

    public static void start() {
        TimerTask timerTask = new CustomTimer(); // FIXME: 10.02.2020
        Timer timer = new Timer(true);
        timer.schedule(timerTask, DELAY, PERIOD);
    }
}
