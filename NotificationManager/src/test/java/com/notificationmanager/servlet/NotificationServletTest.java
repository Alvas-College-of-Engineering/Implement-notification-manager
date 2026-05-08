package com.notificationmanager.servlet;

import org.junit.Test;
import static org.junit.Assert.*;

public class NotificationServletTest {

    @Test
    public void testServletInstantiation() {
        NotificationServlet servlet = new NotificationServlet();
        assertNotNull(servlet);
    }

    // More tests would require mocking HttpServletRequest, etc.
}