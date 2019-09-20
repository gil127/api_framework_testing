package com.salesforce.utils;

import org.apache.log4j.Logger;

public class ThreadUtil {

    private static final Logger logger = Logger.getLogger(ThreadUtil.class);

    private ThreadUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void sleepTime(int sleepTimeInSec) {
        try {
            Thread.sleep(sleepTimeInSec * 1000L);
        } catch (Exception e) {
            String message = "ERROR!! Thread.sleep() exception: " + e;
            logger.error(message);
        }
    }
}
