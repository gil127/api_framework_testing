package com.salesforce.http;

import com.salesforce.http.exceptions.PostmanException;
import org.apache.log4j.Logger;

public class HttpHandler {

    private static final Logger logger = Logger.getLogger(HttpHandler.class);

    private HttpHandler() {
        throw new IllegalStateException("Utility class");
    }

    public static void handleErrorsCode(int statusCode) {
        String errMessage;
        if (statusCode >= 400) {
            errMessage = String.format("response got http error code. code: %d", statusCode);
            logger.error(errMessage);
            //throw new PostmanException(errMessage);
        }
    }
}
