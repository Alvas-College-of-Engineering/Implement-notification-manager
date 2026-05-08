# Notification Manager Web Application

## Project Description

The Notification Manager is a Java-based web application developed using JSP, Servlets, JDBC, and MySQL. The application allows users to create, store, display, and manage notifications efficiently.

Users can:
- Add notifications
- View notification history
- Mark notifications as Read or Unread
- Delete notifications
- Store notifications permanently using MySQL database

---

## Technologies Used

- Java
- JSP
- Servlets
- JDBC
- MySQL
- Apache Tomcat 9
- HTML
- CSS
- Eclipse Enterprise Java / VS Code

---

## Project Structure

```text
NotificationWeb/
│
├── src/
│   ├── dao/
│   │      NotificationDAO.java
│   │
│   ├── model/
│   │      Notification.java
│   │
│   ├── servlet/
│   │      NotificationServlet.java
│   │
│   └── util/
│          DBConnection.java
│
├── WebContent/
│   ├── index.jsp
│   │
│   └── WEB-INF/
│        ├── web.xml
│        └── lib/
│             mysql-connector-j.jar
│
└── README.md
