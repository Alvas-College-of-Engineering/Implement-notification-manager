package com.notificationmanager.util;
import java.sql.*;
public class DBConnection {
    private static final String URL="jdbc:mysql://localhost:3306/notification_manager?useSSL=false&serverTimezone=UTC";
    private static final String USER="root";
    private static final String PASSWORD="root"; // <-- CHANGE THIS
    static {
        try { Class.forName("com.mysql.cj.jdbc.Driver"); }
        catch(ClassNotFoundException e){ throw new RuntimeException("Driver not found",e); }
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }
}
