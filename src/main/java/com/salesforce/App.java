package com.salesforce;

import com.salesforce.utils.RandomData;
import com.salesforce.http.clients.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        PostmanClient postmanClient = new PostmanClient();
        int statusCode = RandomData.getRandomHttpStatusCode();
    }
}
