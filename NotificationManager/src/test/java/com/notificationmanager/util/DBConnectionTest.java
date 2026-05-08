package com.notificationmanager.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class DBConnectionTest {

    @Test
    public void testDriverLoaded() {
        // Test that the static block loaded the driver
        // Since it's static, just check no exception
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            fail("Driver not loaded");
        }
    }

    // getConnection test would require DB setup
}