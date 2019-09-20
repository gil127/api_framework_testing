package com.salesforce;

import com.salesforce.http.clients.*;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PtsvClientTests {
    private PtsvClient ptsvClient;
    private static final Logger logger = Logger.getLogger(PtsvClientTests.class);

    @Before
    public void setUp() {
        ptsvClient = new PtsvClient();
    }

    @Test
    public void iWasWrongTest() {
        String actual = ptsvClient.datoError(null);
        logger.info(String.format("datoError response: %s", actual));
        String expected = String.format("I was wrong");
        Assert.assertTrue(actual.contains(expected));
    }

    @Test
    public void toiletDatoerror1() {
        String actual = ptsvClient.datoError("1");
        logger.info(String.format("datoError response: %s", actual));
        String expected = String.format("Toilet 'datoerror1'");
        Assert.assertTrue(actual.contains(expected));
    }

    @Test
    public void toiletDatoerror2() {
        String actual = ptsvClient.datoError("2");
        logger.info(String.format("datoError response: %s", actual));
        String expected = String.format("Toilet 'datoerror2'");
        Assert.assertTrue(actual.contains(expected));
    }


    @Test
    public void toiletDatoerror3() {
        String actual = ptsvClient.datoError("3");
        logger.info(String.format("datoError response: %s", actual));
        String expected = String.format("Toilet 'datoerror3'");
        Assert.assertTrue(actual.contains(expected));
    }
}
