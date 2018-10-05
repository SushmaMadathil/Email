/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author sonal
 */
@Named(value = "emails")
@SessionScoped
public class emails implements Serializable {

    /**
     * Creates a new instance of emails
     */
    private int id;
    private int replyid;
    private String toid;
    private String fromid;
//    private String fromname;
    private String date;
    private String title;
    private String content;
    private int status;
    private int notify;

    public emails(int id, int replyid, String toid, String fromid, String date, String title, String content, int status, int notify) {
        this.id = id;
        this.replyid = replyid;
        this.toid = toid;
        this.fromid = fromid;
        
        this.date = date;
        this.title = title;
        this.content = content;
        this.status = status;
        this.notify = notify;
    }

   

    public String getFromid() {
        return fromid;
    }

    public void setFromid(String fromid) {
        this.fromid = fromid;
    }

    public String getToid() {
        return toid;
    }

    public void setToid(String toid) {
        this.toid = toid;
    }

    public int getReplyid() {
        return replyid;
    }

    public void setReplyid(int replyid) {
        this.replyid = replyid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

//    public String getFromname() {
//        return fromname;
//    }
//
//    public void setFromname(String fromname) {
//        this.fromname = fromname;
//    }
//    

    public int getNotify() {
        return notify;
    }

    public void setNotify(int notify) {
        this.notify = notify;
    }
    
    
    
}
