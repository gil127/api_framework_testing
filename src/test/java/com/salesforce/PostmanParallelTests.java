package com.salesforce;

import com.salesforce.http.clients.PostmanClient;
import com.salesforce.http.clients.PtsvClient;
import com.salesforce.utils.ThreadUtil;
import org.apache.log4j.Logger;
import org.asynchttpclient.*;
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

    @Test
    public void submitTaskAsyncCalls() throws ExecutionException, InterruptedException {
        String[] result = new String[4];
        String[] requestParams = {"http://ptsv2.com/t/datoerror/post", "http://ptsv2.com/t/datoerror1/post",
                "http://ptsv2.com/t/datoerror2/post", "http://ptsv2.com/t/datoerror3/post"};
        AsyncHttpClient client = Dsl.asyncHttpClient();
        List<ListenableFuture<Object>> executables = new ArrayList<>();

        for (int i = 0; i < requestParams.length; i++) {
            executables.add(client.preparePost(requestParams[i]).execute(asyncStringOrderHandler(result, i)));
        }

        for (int i = 0; i < executables.size(); i++) {
            executables.get(0).get();
        }

        logger.info(buildStringFromArray(result));
    }

    private AsyncHandler<Object> asyncStringOrderHandler(String[] result, int i) {
        AsyncCompletionHandler<Object> asyncCompletionHandler = new AsyncCompletionHandler<Object>() {
            @Override
            public Object onCompleted(Response response) {
                result[i] = response.getResponseBody();
                return response;
            }
        };

        return asyncCompletionHandler;
    }

    private String buildStringFromArray(String[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
        }

        return sb.toString();
    }
}
