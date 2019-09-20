package com.salesforce.http.exceptions;

public class PostmanException extends RuntimeException {

    public PostmanException(String message) {
        super(message);
    }

    public PostmanException(String message, Exception e) {
        super(message, e);
    }
}
