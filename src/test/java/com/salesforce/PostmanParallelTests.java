package com.salesforce;

import com.salesforce.http.clients.PostmanClient;
import com.salesforce.http.clients.PtsvClient;
import com.salesforce.utils.ThreadUtil;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


public class PostmanParallelTests extends BaseTests {

    private static final Logger logger = Logger.getLogger(PostmanParallelTests.class);

    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void submitTaskMultiThreading() throws ExecutionException, InterruptedException {
        StringBuilder sb = new StringBuilder();
        List<Future<String>> futures = new ArrayList<>();
        String[] requestParams = {null, "1", "2", "3"};

        for (int i = 0; i < requestParams.length; i++) {
            int finalI = i;
            futures.add(ThreadUtil.submitTask(() -> new PtsvClient().datoError(requestParams[finalI])));
        }
        futures.add(ThreadUtil.submitTask(() -> new PostmanClient().delayWithBody(10)));

        for (int i = 0; i < futures.size(); i++) {
            sb.append(futures.get(i).get());
        }

        logger.info(sb.toString());
    }
}
