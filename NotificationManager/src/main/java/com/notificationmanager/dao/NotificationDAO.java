package com.notificationmanager.dao;
import com.notificationmanager.model.Notification;
import com.notificationmanager.model.Notification.*;
import com.notificationmanager.util.DBConnection;
import java.sql.*;
import java.util.*;
public class NotificationDAO {
    public boolean create(Notification n) {
        String sql="INSERT INTO notifications(title,message,type,is_read,source,priority)VALUES(?,?,?,?,?,?)";
        try(Connection c=DBConnection.getConnection();PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,n.getTitle());ps.setString(2,n.getMessage());
            ps.setString(3,n.getType().name());ps.setBoolean(4,n.isRead());
            ps.setString(5,n.getSource());ps.setString(6,n.getPriority().name());
            return ps.executeUpdate()>0;
        }catch(SQLException e){e.printStackTrace();return false;}
    }
    public List<Notification> getAll() { return query("SELECT * FROM notifications ORDER BY created_at DESC",null); }
    public List<Notification> getUnread() { return query("SELECT * FROM notifications WHERE is_read=FALSE ORDER BY created_at DESC",null); }
    public List<Notification> getByType(String type) {
        String sql="SELECT * FROM notifications WHERE type=? ORDER BY created_at DESC";
        try(Connection c=DBConnection.getConnection();PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,type); return map(ps.executeQuery());
        }catch(SQLException e){e.printStackTrace();return new ArrayList<>();}
    }
    public List<Notification> search(String kw) {
        String sql="SELECT * FROM notifications WHERE title LIKE ? OR message LIKE ? OR source LIKE ? ORDER BY created_at DESC";
        try(Connection c=DBConnection.getConnection();PreparedStatement ps=c.prepareStatement(sql)){
            String k="%"+kw+"%"; ps.setString(1,k);ps.setString(2,k);ps.setString(3,k);
            return map(ps.executeQuery());
        }catch(SQLException e){e.printStackTrace();return new ArrayList<>();}
    }
    public boolean markRead(int id){return setRead(id,true);}
    public boolean markUnread(int id){return setRead(id,false);}
    private boolean setRead(int id,boolean v){
        try(Connection c=DBConnection.getConnection();
            PreparedStatement ps=c.prepareStatement("UPDATE notifications SET is_read=? WHERE id=?")){
            ps.setBoolean(1,v);ps.setInt(2,id);return ps.executeUpdate()>0;
        }catch(SQLException e){e.printStackTrace();return false;}
    }
    public boolean markAllRead(){
        try(Connection c=DBConnection.getConnection();
            PreparedStatement ps=c.prepareStatement("UPDATE notifications SET is_read=TRUE WHERE is_read=FALSE")){
            ps.executeUpdate();return true;
        }catch(SQLException e){e.printStackTrace();return false;}
    }
    public boolean delete(int id){
        try(Connection c=DBConnection.getConnection();
            PreparedStatement ps=c.prepareStatement("DELETE FROM notifications WHERE id=?")){
            ps.setInt(1,id);return ps.executeUpdate()>0;
        }catch(SQLException e){e.printStackTrace();return false;}
    }
    public boolean clearRead(){
        try(Connection c=DBConnection.getConnection();
            PreparedStatement ps=c.prepareStatement("DELETE FROM notifications WHERE is_read=TRUE")){
            ps.executeUpdate();return true;
        }catch(SQLException e){e.printStackTrace();return false;}
    }
    public int countUnread(){
        try(Connection c=DBConnection.getConnection();
            PreparedStatement ps=c.prepareStatement("SELECT COUNT(*) FROM notifications WHERE is_read=FALSE");
            ResultSet rs=ps.executeQuery()){
            return rs.next()?rs.getInt(1):0;
        }catch(SQLException e){e.printStackTrace();return 0;}
    }
    private List<Notification> query(String sql,Object p){
        try(Connection c=DBConnection.getConnection();PreparedStatement ps=c.prepareStatement(sql)){
            return map(ps.executeQuery());
        }catch(SQLException e){e.printStackTrace();return new ArrayList<>();}
    }
    private List<Notification> map(ResultSet rs) throws SQLException {
        List<Notification> list=new ArrayList<>();
        while(rs.next()){
            Notification n=new Notification();
            n.setId(rs.getInt("id"));n.setTitle(rs.getString("title"));
            n.setMessage(rs.getString("message"));n.setType(Type.valueOf(rs.getString("type")));
            n.setRead(rs.getBoolean("is_read"));n.setCreatedAt(rs.getTimestamp("created_at"));
            n.setUpdatedAt(rs.getTimestamp("updated_at"));n.setSource(rs.getString("source"));
            n.setPriority(Priority.valueOf(rs.getString("priority")));
            list.add(n);
        }
        return list;
    }
}
