<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List,com.notificationmanager.model.Notification" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8"><meta name="viewport" content="width=device-width,initial-scale=1">
<title>Notification Manager</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
<%
  List<Notification> notifications=(List<Notification>)request.getAttribute("notifications");
  Integer unreadCount=(Integer)request.getAttribute("unreadCount");
  String filter=(String)request.getAttribute("filter");
  String msg=(String)request.getAttribute("msg");
  String searchQuery=(String)request.getAttribute("searchQuery");
  if(unreadCount==null)unreadCount=0;
  if(filter==null)filter="all";
%>
<aside class="sidebar" id="sidebar">
  <div class="sidebar-header">
    <div class="logo"><i class="fa-solid fa-bell"></i><span>NotifyHub</span></div>
  </div>
  <nav class="sidebar-nav">
    <a href="NotificationServlet?action=list" class="nav-item <%="all".equals(filter)?"active":""%>"><i class="fa-solid fa-inbox"></i> All</a>
    <a href="NotificationServlet?action=unread" class="nav-item <%="unread".equals(filter)?"active":""%>">
      <i class="fa-solid fa-envelope"></i> Unread
      <%if(unreadCount>0){%><span class="badge"><%=unreadCount%></span><%}%>
    </a>
    <div class="nav-section">By Type</div>
    <a href="NotificationServlet?action=filterType&type=INFO"    class="nav-item <%="INFO".equals(filter)?"active":""%>"><i class="fa-solid fa-circle-info"></i> Info</a>
    <a href="NotificationServlet?action=filterType&type=SUCCESS" class="nav-item <%="SUCCESS".equals(filter)?"active":""%>"><i class="fa-solid fa-circle-check"></i> Success</a>
    <a href="NotificationServlet?action=filterType&type=WARNING" class="nav-item <%="WARNING".equals(filter)?"active":""%>"><i class="fa-solid fa-triangle-exclamation"></i> Warning</a>
    <a href="NotificationServlet?action=filterType&type=ERROR"   class="nav-item <%="ERROR".equals(filter)?"active":""%>"><i class="fa-solid fa-circle-xmark"></i> Error</a>
  </nav>
  <div class="sidebar-actions">
    <a href="NotificationServlet?action=markAllRead" class="btn-sidebar"><i class="fa-solid fa-check-double"></i> Mark All Read</a>
    <a href="NotificationServlet?action=clearRead" class="btn-sidebar btn-danger-outline" onclick="return confirm('Clear all read notifications?')"><i class="fa-solid fa-trash"></i> Clear History</a>
  </div>
</aside>
<main class="main">
  <header class="topbar">
    <div class="topbar-left">
      <button class="hamburger" id="menuToggle"><i class="fa-solid fa-bars"></i></button>
      <h1 class="page-title"><%="all".equals(filter)?"All Notifications":"unread".equals(filter)?"Unread":"search".equals(filter)?"Search Results":filter+" Notifications"%></h1>
    </div>
    <div class="topbar-right">
      <form class="search-bar" action="NotificationServlet" method="get">
        <input type="hidden" name="action" value="search">
        <input type="text" name="q" placeholder="Search..." value="<%=searchQuery!=null?searchQuery:""%>" class="search-input">
        <button type="submit" class="search-btn"><i class="fa-solid fa-search"></i></button>
      </form>
      <button class="btn-primary" id="openModalBtn"><i class="fa-solid fa-plus"></i> New</button>
    </div>
  </header>
  <%if(msg!=null){%>
  <div class="toast <%="error".equals(msg)?"toast-error":""%>" id="toast">
    <i class="fa-solid <%="error".equals(msg)?"fa-xmark":"fa-check"%>"></i>
    <%="created".equals(msg)?"Notification created!":"deleted".equals(msg)?"Deleted.":"marked_read".equals(msg)?"Marked as read.":"marked_unread".equals(msg)?"Marked as unread.":"all_read".equals(msg)?"All marked read.":"cleared".equals(msg)?"History cleared.":"error".equals(msg)?"An error occurred.":msg%>
  </div>
  <%}%>
  <div class="notifications-container">
    <%if(notifications==null||notifications.isEmpty()){%>
    <div class="empty-state"><i class="fa-solid fa-bell-slash"></i><h3>No Notifications</h3><p>Create one to get started.</p></div>
    <%}else{for(Notification n:notifications){%>
    <div class="notification-card <%=!n.isRead()?"unread":""%> type-<%=n.getType().name().toLowerCase()%>-card">
      <div class="card-indicator"></div>
      <div class="card-icon">
        <i class="fa-solid <%="INFO".equals(n.getTypeLabel())?"fa-circle-info":"SUCCESS".equals(n.getTypeLabel())?"fa-circle-check":"WARNING".equals(n.getTypeLabel())?"fa-triangle-exclamation":"fa-circle-xmark"%>"></i>
      </div>
      <div class="card-body">
        <div class="card-header-row">
          <span class="card-title"><%=n.getTitle()%></span>
          <div class="card-badges">
            <span class="badge-type type-<%=n.getType().name().toLowerCase()%>"><%=n.getTypeLabel()%></span>
            <span class="badge-priority prio-<%=n.getPriority().name().toLowerCase()%>"><%=n.getPriorityLabel()%></span>
            <%if(!n.isRead()){ %><span class="dot-unread"></span><% } %>
          </div>
        </div>
        <p class="card-message"><%=n.getMessage()%></p>
        <div class="card-meta">
          <span><i class="fa-solid fa-tower-broadcast"></i> <%=n.getSource()%></span>
          <span><i class="fa-regular fa-clock"></i> <%=n.getCreatedAt()%></span>
        </div>
      </div>
      <div class="card-actions">
        <%if(!n.isRead()){ %>
        <a href="NotificationServlet?action=markRead&id=<%=n.getId()%>" class="action-btn" title="Mark read"><i class="fa-solid fa-envelope-open"></i></a>
        <% } else { %>
        <a href="NotificationServlet?action=markUnread&id=<%=n.getId()%>" class="action-btn" title="Mark unread"><i class="fa-solid fa-envelope"></i></a>
        <% } %>
        <a href="NotificationServlet?action=delete&id=<%=n.getId()%>" class="action-btn action-delete" title="Delete" onclick="return confirm('Delete this?')"><i class="fa-solid fa-trash"></i></a>
      </div>
    </div>
    <%}}%>
  </div>
</main>
<div class="modal-overlay" id="modalOverlay">
  <div class="modal">
    <div class="modal-header">
      <h2><i class="fa-solid fa-bell"></i> Create Notification</h2>
      <button class="modal-close" id="closeModalBtn"><i class="fa-solid fa-xmark"></i></button>
    </div>
    <form action="NotificationServlet" method="post" class="modal-form">
      <input type="hidden" name="action" value="create">
      <div class="form-group"><label>Title *</label><input type="text" name="title" placeholder="Notification title" required></div>
      <div class="form-group"><label>Message *</label><textarea name="message" rows="3" placeholder="Notification message" required></textarea></div>
      <div class="form-row">
        <div class="form-group"><label>Type *</label>
          <select name="type" required>
            <option value="INFO">ℹ Info</option><option value="SUCCESS">✅ Success</option>
            <option value="WARNING">⚠ Warning</option><option value="ERROR">❌ Error</option>
          </select>
        </div>
        <div class="form-group"><label>Priority *</label>
          <select name="priority" required>
            <option value="LOW">🟢 Low</option><option value="MEDIUM" selected>🟡 Medium</option>
            <option value="HIGH">🟠 High</option><option value="CRITICAL">🔴 Critical</option>
          </select>
        </div>
      </div>
      <div class="form-group"><label>Source</label><input type="text" name="source" value="System" placeholder="e.g. System, Analytics..."></div>
      <div class="modal-footer">
        <button type="button" class="btn-outline" id="cancelBtn">Cancel</button>
        <button type="submit" class="btn-primary"><i class="fa-solid fa-paper-plane"></i> Send</button>
      </div>
    </form>
  </div>
</div>
<script src="${pageContext.request.contextPath}/js/app.js"></script>
</body>
</html>
