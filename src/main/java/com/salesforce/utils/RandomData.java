package com.salesforce.utils;

import org.apache.log4j.Logger;
import java.util.Random;

import static java.net.HttpURLConnection.*;

public class RandomData {

    private static final Logger logger = Logger.getLogger(RandomData.class);
    private static final Random random = new Random();

    private RandomData() {
        throw new IllegalStateException("Utility class");
    }

    public static int getInt(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    public static int getRandomHttpStatusCode() {
        int[] codes = {HTTP_OK, HTTP_NOT_FOUND, HTTP_INTERNAL_ERROR, HTTP_UNAVAILABLE};
        return codes[getInt(0, codes.length - 1)];
    }

}
