package com.notificationmanager.model;
import java.sql.Timestamp;
public class Notification {
    public enum Type { INFO, WARNING, ERROR, SUCCESS }
    public enum Priority { LOW, MEDIUM, HIGH, CRITICAL }
    private int id;
    private String title, message, source;
    private Type type;
    private Priority priority;
    private boolean isRead;
    private Timestamp createdAt, updatedAt;

    public Notification() {}
    public Notification(String title, String message, Type type, String source, Priority priority) {
        this.title=title; this.message=message; this.type=type;
        this.source=source; this.priority=priority; this.isRead=false;
    }
    public Notification(int id,String title,String message,Type type,boolean isRead,
                        Timestamp createdAt,Timestamp updatedAt,String source,Priority priority){
        this.id=id; this.title=title; this.message=message; this.type=type;
        this.isRead=isRead; this.createdAt=createdAt; this.updatedAt=updatedAt;
        this.source=source; this.priority=priority;
    }
    public int getId(){return id;} public void setId(int id){this.id=id;}
    public String getTitle(){return title;} public void setTitle(String t){this.title=t;}
    public String getMessage(){return message;} public void setMessage(String m){this.message=m;}
    public String getSource(){return source;} public void setSource(String s){this.source=s;}
    public Type getType(){return type;} public void setType(Type t){this.type=t;}
    public Priority getPriority(){return priority;} public void setPriority(Priority p){this.priority=p;}
    public boolean isRead(){return isRead;} public void setRead(boolean r){this.isRead=r;}
    public Timestamp getCreatedAt(){return createdAt;} public void setCreatedAt(Timestamp t){this.createdAt=t;}
    public Timestamp getUpdatedAt(){return updatedAt;} public void setUpdatedAt(Timestamp t){this.updatedAt=t;}
    public String getTypeLabel(){return type!=null?type.name():"INFO";}
    public String getPriorityLabel(){return priority!=null?priority.name():"MEDIUM";}
}
