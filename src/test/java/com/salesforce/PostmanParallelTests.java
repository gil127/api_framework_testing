package com.salesforce;

import com.salesforce.utils.ThreadUtil;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class PostmanParallelTests extends BaseTests {

    private static final Logger logger = Logger.getLogger(PostmanParallelTests.class);
    private ExecutorService executorService = Executors.newFixedThreadPool(5);

    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void submitTaskUsingThread() {
        StringBuilder sb = new StringBuilder();
        sb.append(ThreadUtil.submitTask(null));
        sb.append(ThreadUtil.submitTask("1"));
        sb.append(ThreadUtil.submitTask("2"));
        sb.append(ThreadUtil.submitTask("3"));
        sb.append(ThreadUtil.submitTask("3"));
       logger.info(sb.toString());
    }
}
