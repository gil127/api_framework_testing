package com.salesforce.http.clients;

import com.salesforce.http.HttpHandler;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.junit.Assert;

import static org.hamcrest.Matchers.lessThan;
import static java.net.HttpURLConnection.*;


public class PostmanClient {

    private static final String URL = "https://postman-echo.com/";
    private static final Logger logger = Logger.getLogger(PostmanClient.class);
    private static final long MAXIMUM_RUN_TIME = 10_000L;

    public PostmanClient() {
        RestAssured.baseURI = URL;
    }

    public void status(int statusCode) {
        logger.info("sending status request");
        String path = "status/{status_code}";
        Response response = RestAssured.given().pathParam("status_code", statusCode).get(path);
        Assert.assertEquals(response.getStatusCode(), statusCode);
        response.then().time(lessThan(MAXIMUM_RUN_TIME));
        HttpHandler.handleErrorsCode(statusCode);
    }

    public void delay(int delayTime) {
        logger.info("sending delay request");
        String path = "delay/{delay_time}";
        RestAssured.given().pathParam("delay_time", delayTime).get(path).then().statusCode(HTTP_OK)
                .and().time(lessThan(MAXIMUM_RUN_TIME));
    }

    public String delayWithBody(int delayTime) {
        logger.info("sending delay request");
        String path = "delay/{delay_time}";
        return RestAssured.given().pathParam("delay_time", delayTime).get(path).getBody().prettyPrint();
    }
}
