package com.salesforce;

import org.apache.log4j.BasicConfigurator;
import org.junit.Before;

public class BaseTests {

    @Before
    public void setUp() {
        // NOTE: in more complex application, consider implement as log listener
        BasicConfigurator.configure();
    }
}
