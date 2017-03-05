package com.realaicy.prod.jc.realglobal.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by realaicy on 16/8/15.
 * xxx
 */
public class SessionCounterListener implements HttpSessionListener {

    private static int totalActiveSessions;

    private static Logger logger = LoggerFactory.getLogger(SessionCounterListener.class);


    public static int getTotalActiveSession() {
        return totalActiveSessions;
    }

    @Override
    public void sessionCreated(HttpSessionEvent arg0) {
        totalActiveSessions++;
        logger.info("sessionCreated - add one session into counter: totalActiveSessions is {}", totalActiveSessions);
    }


    @Override
    public void sessionDestroyed(HttpSessionEvent arg0) {
        totalActiveSessions--;
        logger.info("sessionDestroyed - deduct one session from counter,"
                + "totalActiveSessions is {}", totalActiveSessions);
    }
}
