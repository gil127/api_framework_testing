package com.salesforce.http.clients;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;


public class PtsvClient {
    private static final String URL = "http://ptsv2.com/";
    private static final Logger logger = Logger.getLogger(PtsvClient.class);

    static {
        BasicConfigurator.configure();
    }

    public PtsvClient() {
        RestAssured.baseURI = URL;
    }

    public String datoError(String n) {
        logger.info("sending dato error request");
        String path = n == null ? "t/datoerror/post" : "t/datoerror{n}/post";
        RequestSpecification requestSpecification = RestAssured.given().contentType(ContentType.JSON);
        if (n != null) {
            requestSpecification.pathParam("n", n);
        }
        return requestSpecification.post(path).body().asString();
    }
}