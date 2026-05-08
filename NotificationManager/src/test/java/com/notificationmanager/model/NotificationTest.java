package com.notificationmanager.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class NotificationTest {

    @Test
    public void testConstructorWithAllParameters() {
        Notification n = new Notification("Test Title", "Test Message", Notification.Type.INFO, "Test Source", Notification.Priority.HIGH);
        assertEquals("Test Title", n.getTitle());
        assertEquals("Test Message", n.getMessage());
        assertEquals(Notification.Type.INFO, n.getType());
        assertEquals("Test Source", n.getSource());
        assertEquals(Notification.Priority.HIGH, n.getPriority());
        assertFalse(n.isRead());
    }

    @Test
    public void testFullConstructor() {
        Notification n = new Notification(1, "Title", "Message", Notification.Type.ERROR, true, null, null, "Source", Notification.Priority.LOW);
        assertEquals(1, n.getId());
        assertEquals("Title", n.getTitle());
        assertEquals("Message", n.getMessage());
        assertEquals(Notification.Type.ERROR, n.getType());
        assertTrue(n.isRead());
        assertEquals("Source", n.getSource());
        assertEquals(Notification.Priority.LOW, n.getPriority());
    }

    @Test
    public void testSettersAndGetters() {
        Notification n = new Notification();
        n.setId(2);
        n.setTitle("New Title");
        n.setMessage("New Message");
        n.setType(Notification.Type.WARNING);
        n.setSource("New Source");
        n.setPriority(Notification.Priority.MEDIUM);
        n.setRead(true);

        assertEquals(2, n.getId());
        assertEquals("New Title", n.getTitle());
        assertEquals("New Message", n.getMessage());
        assertEquals(Notification.Type.WARNING, n.getType());
        assertEquals("New Source", n.getSource());
        assertEquals(Notification.Priority.MEDIUM, n.getPriority());
        assertTrue(n.isRead());
    }

    @Test
    public void testLabelMethods() {
        Notification n = new Notification("Title", "Message", Notification.Type.SUCCESS, "Source", Notification.Priority.CRITICAL);
        assertEquals("SUCCESS", n.getTypeLabel());
        assertEquals("CRITICAL", n.getPriorityLabel());
    }
}