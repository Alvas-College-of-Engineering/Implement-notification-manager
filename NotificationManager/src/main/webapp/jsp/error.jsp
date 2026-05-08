<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>Error</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head><body>
<div class="error-page">
  <i class="fa-solid fa-triangle-exclamation"></i>
  <h1>Something went wrong</h1>
  <p>An unexpected error occurred. Please try again.</p>
  <a href="${pageContext.request.contextPath}/NotificationServlet" class="btn-primary"><i class="fa-solid fa-house"></i> Back to Home</a>
</div></body></html>
