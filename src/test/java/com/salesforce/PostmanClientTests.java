package com.salesforce;

import com.salesforce.utils.RandomData;
import com.salesforce.http.clients.*;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class PostmanClientTests extends BaseTests {
    private PostmanClient postmanClient;
    private static final Logger logger = Logger.getLogger(PostmanClientTests.class);

    @Before
    public void setUp() {
        super.setUp();
        postmanClient = new PostmanClient();
    }

    @Test
    public void multipleCallsForStatus() {
        int n = RandomData.getInt(1, 30);
        logger.info(String.format("%d calls about to fire up", n));
        for (int i = 0; i < n; i++) {
            postmanClient.status(RandomData.getRandomHttpStatusCode());
        }
    }

    @Test
    public void multipleCallsForDelay() {
        int n = RandomData.getInt(1, 30);
        logger.info(String.format("%d calls about to fire up", n));
        for (int i = 0; i < n; i++) {
            postmanClient.delay(RandomData.getInt(0, 10));
        }
    }
}
