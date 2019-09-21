package com.salesforce.utils;

import org.apache.log4j.Logger;
import java.util.concurrent.*;

public class ThreadUtil {

    private static final Logger logger = Logger.getLogger(ThreadUtil.class);
    private static ExecutorService executorService = Executors.newFixedThreadPool(5);;

    private ThreadUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void sleepTime(int sleepTimeInSec) {
        try {
            Thread.sleep(sleepTimeInSec * 1000L);
        } catch (Exception e) {
            logger.error(String.format("Error while executing thread sleep exception: %s", e.getMessage()));
        }
    }

    public static Future<String> submitTask(Callable<String> callable) {
        Future<String> future = executorService.submit(callable);
        return future;
    }
}
