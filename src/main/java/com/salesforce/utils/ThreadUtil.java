package com.salesforce.utils;

import org.apache.log4j.Logger;
import com.salesforce.http.clients.*;

import java.util.concurrent.*;

public class ThreadUtil {

    private static final Logger logger = Logger.getLogger(ThreadUtil.class);

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

    public static String submitTask(String n) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<String> callable = () -> new PtsvClient().datoError(n);
        Future<String> future = executor.submit(callable);
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            logger.error(String.format("[Error] error while executing submit task. exception message: %s", e.getMessage()));
        }
        return null;
    }
}
