package com.notificationmanager.servlet;
import com.notificationmanager.dao.NotificationDAO;
import com.notificationmanager.model.Notification;
import com.notificationmanager.model.Notification.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
@WebServlet("/NotificationServlet")
public class NotificationServlet extends HttpServlet {
    private final NotificationDAO dao=new NotificationDAO();
    @Override
    protected void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException {
        String action=req.getParameter("action");
        if(action==null)action="list";
        List<Notification> list=null; String filter=action;
        switch(action){
            case"unread": list=dao.getUnread(); break;
            case"filterType": filter=req.getParameter("type"); list=dao.getByType(filter); break;
            case"search":
                String q=req.getParameter("q");
                list=dao.search(q!=null?q:"");
                req.setAttribute("searchQuery",q); break;
            case"markRead": dao.markRead(Integer.parseInt(req.getParameter("id")));
                res.sendRedirect("NotificationServlet?action=list&msg=marked_read"); return;
            case"markUnread": dao.markUnread(Integer.parseInt(req.getParameter("id")));
                res.sendRedirect("NotificationServlet?action=list&msg=marked_unread"); return;
            case"markAllRead": dao.markAllRead();
                res.sendRedirect("NotificationServlet?action=list&msg=all_read"); return;
            case"delete": dao.delete(Integer.parseInt(req.getParameter("id")));
                res.sendRedirect("NotificationServlet?action=list&msg=deleted"); return;
            case"clearRead": dao.clearRead();
                res.sendRedirect("NotificationServlet?action=list&msg=cleared"); return;
            default: list=dao.getAll(); filter="all";
        }
        req.setAttribute("notifications",list);
        req.setAttribute("filter",filter);
        req.setAttribute("unreadCount",dao.countUnread());
        req.setAttribute("msg",req.getParameter("msg"));
        req.getRequestDispatcher("/jsp/index.jsp").forward(req,res);
    }
    @Override
    protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException {
        try {
            Notification n=new Notification(
                req.getParameter("title"), req.getParameter("message"),
                Type.valueOf(req.getParameter("type")), req.getParameter("source"),
                Priority.valueOf(req.getParameter("priority"))
            );
            dao.create(n);
            res.sendRedirect("NotificationServlet?action=list&msg=created");
        } catch(Exception e){ e.printStackTrace(); res.sendRedirect("NotificationServlet?action=list&msg=error"); }
    }
}
