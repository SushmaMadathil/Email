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
import java.util.ArrayList;
import javax.faces.context.FacesContext;

/**
 *
 * @author sonal
 */
@Named(value = "account")
@SessionScoped
public class Account implements Serializable {

    /**
     * Creates a new instance of Account
     */
    private String emailid;
    private String firstname;
    private String lastname;
    private ArrayList<emails> inboxemail;
    private ArrayList<emails> sentemail;
    private ArrayList<emails> trashemails;
 
    private ArrayList<users> fromname;

    private ArrayList<emails> allemails;

    private emails originalemail;
    private ArrayList<emails> replyemails;

    private int viewid;
    private int replyid;
    private int check = 00;
    
    //compose email fields
    private String toid;       
    private String title;
    private String content;
    private int notify = 0;
   

 
    
    public Account(String emailid, String firstname, String lastname) 
    {
        
        this.emailid = emailid;
        this.firstname = firstname;
        this.lastname = lastname;
        
      
        fromname = new ArrayList<users>();
      
        
        
        try
        {
           Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e)
        {
            System.out.println("Please check your driver");
        }
        
        

        Connection conn=null;
       
        Statement st1=null;
        ResultSet rs1=null;
        
        

        final String DB_URL="jdbc:mysql://mis-sql.uhcl.edu/umares3563";
        
        try
       {
           conn=DriverManager.getConnection(DB_URL,"umares3563","1548736");
           
//           //Inbox
//            Statement st=null;
//            ResultSet rs=null;
//            
//            st=conn.createStatement();
//            rs =st.executeQuery("Select * from emails where toid ='" + emailid+ "'");
//
//            while(rs.next())
//            {               
//
//                inboxemail.add(new emails(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9)));
//            }
            
           
           
           //Users
               st1 = conn.createStatement();
               rs1 = st1.executeQuery("select * from users");
               while(rs1.next())
               {
                   fromname.add(new users(rs1.getString("emailid") ,rs1.getString("firstname"), rs1.getString("lastname")));
               }
               
               
//               //allemails
//               Statement st2=null;
//               ResultSet rs2=null;
//               
//               st2 = conn.createStatement();
//               rs2 = st2.executeQuery("select * from emails");
//               while(rs2.next())
//                {
//
//                    allemails.add(new emails(rs2.getInt(1), rs2.getInt(2), rs2.getString(3), rs2.getString(4), rs2.getString(5), rs2.getString(6), rs2.getString(7), rs2.getInt(8), rs2.getInt(9)));
//                }
//               
               
             //Sent emails
//              Statement st3=null;
//              ResultSet rs3=null;
//             
//              st3 = conn.createStatement();
//              rs3 = st3.executeQuery("select * from emails where fromid ='" + emailid + "' and replyid = -1");
//              while(rs3.next())
//              {
//                    sentemail.add(new emails(rs3.getInt(1), rs3.getInt(2), rs3.getString(3), rs3.getString(4), rs3.getString(5), rs3.getString(6), rs3.getString(7), rs3.getInt(8), rs3.getInt(9)));
//              }
               
       
       }
       catch(SQLException e)
       {
            e.printStackTrace();
           System.out.println("Internal error");
       }
       finally
       {
           try
           {
               conn.close();
               st1.close();
               rs1.close();
                          }
           catch(Exception e)
           {
               e.printStackTrace();
           }
       }
       
    
    }

    public int getViewid() {
        return viewid;
    }

    public String getEmailid() {
        return emailid;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public ArrayList<emails> getInboxemail() {
        return inboxemail;
    }

    public ArrayList<emails> getSentemail() {
        return sentemail;
    }

    public ArrayList<users> getFromname() {
        return fromname;
    }

    public ArrayList<emails> getReplyemails() {
        return replyemails;
    }

    public int getCheck() {
        return check;
    }
    
    
       public String getToid() {
        return toid;
    }

    public void setToid(String toid) {
        this.toid = toid;
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

    public int getNotify() {
        return notify;
    }

    public void setNotify(int notify) {
        this.notify = notify;
    }
    
    public ArrayList<emails> Inboxemails()
    {
        inboxemail = new ArrayList<emails>();
        
        try
        {
           Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e)
        {
            System.out.println("Please check your driver");
        }
        
        

        Connection conn=null;
        Statement st=null;
        ResultSet rs=null;
        
        
        
        final String DB_URL="jdbc:mysql://mis-sql.uhcl.edu/umares3563";
        
        try
       {
           conn=DriverManager.getConnection(DB_URL,"umares3563","1548736");
           st=conn.createStatement();
           rs =st.executeQuery("Select * from emails where toid ='" + emailid+ "' order by date asc");
           
           while(rs.next())
           {               

               inboxemail.add(new emails(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9)));
           }
           return inboxemail;
       }
        catch(SQLException e)
       {
            e.printStackTrace();
           System.out.println("Internal error");
       }
       finally
       {
           try
           {
               conn.close();
               st.close();
               rs.close();
               
           }
           catch(Exception e)
           {
               e.printStackTrace();
           }
       }
        return null;
    }
//    
//    
    public ArrayList<emails> SentEmails()
    {
        sentemail = new ArrayList<emails>();
        
        try
        {
           Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e)
        {
            System.out.println("Please check your driver");
        }
        
        

        Connection conn=null;
        Statement st3=null;
        ResultSet rs3=null;
       
         

        final String DB_URL="jdbc:mysql://mis-sql.uhcl.edu/umares3563";
        
        try
       {
           conn=DriverManager.getConnection(DB_URL,"umares3563","1548736");
            st3 = conn.createStatement();
               rs3 = st3.executeQuery("select * from emails where fromid ='" + emailid + "' and replyid = -1 order by date asc");
               while(rs3.next())
                {
                    sentemail.add(new emails(rs3.getInt(1), rs3.getInt(2), rs3.getString(3), rs3.getString(4), rs3.getString(5), rs3.getString(6), rs3.getString(7), rs3.getInt(8), rs3.getInt(9)));
                }
               return sentemail;
       
       }
       catch(SQLException e)
       {
            e.printStackTrace();
           System.out.println("Internal error");
       }
       finally
       {
           try
           {
               conn.close();
               
               st3.close();
               rs3.close();
           }
           catch(Exception e)
           {
               e.printStackTrace();
           }
       }
        return null;
    }

    public void allEmails()
    {
        allemails = new ArrayList<emails>();
        
        try
        {
           Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e)
        {
            System.out.println("Please check your driver");
        }
        
        

        Connection conn=null;
       
         Statement st2=null;
        ResultSet rs2=null;

        final String DB_URL="jdbc:mysql://mis-sql.uhcl.edu/umares3563";
        
        try
       {
           conn=DriverManager.getConnection(DB_URL,"umares3563","1548736");
            st2 = conn.createStatement();
               rs2 = st2.executeQuery("select * from emails");
               while(rs2.next())
                {

                    allemails.add(new emails(rs2.getInt(1), rs2.getInt(2), rs2.getString(3), rs2.getString(4), rs2.getString(5), rs2.getString(6), rs2.getString(7), rs2.getInt(8), rs2.getInt(9)));
                }
       
       }
       catch(SQLException e)
       {
            e.printStackTrace();
           System.out.println("Internal error");
       }
       finally
       {
           try
           {
               conn.close();
               
               st2.close();
               rs2.close();
           }
           catch(Exception e)
           {
               e.printStackTrace();
           }
       }
       
        
    }
    
    
    public String ComposeNewEmail(String email , int eid)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e)
        {
            return "Please check your driver";
        }
        
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        Statement st1 = null;
        Statement st2 = null;
        ResultSet rs1 = null;
       allEmails();
        
        int replyid = -1;
        
        final String DB_URL="jdbc:mysql://mis-sql.uhcl.edu/umares3563";
        try
        {
            int id = 0;
            int next = 0;
            con = DriverManager.getConnection(DB_URL, "umares3563" , "1548736");
//            st = con.createStatement();
//            rs = st.executeQuery("Select * from nextid");
//            if(rs.next())
//            {
//                id = rs.getInt(1);
//                next = id+1;
//            }
//            
//            int s = st.executeUpdate("update nextid set ID = " + next);
            
            st1 = con.createStatement();
            st2 = con.createStatement();
            
            if(eid == -100) // -100 -----> Notification Email
            {
                
                rs1 = st1.executeQuery("Select * from emails where ID =" + viewid);
                while(rs1.next())
                {
                    if(rs1.getInt("notify")== 1)
                    {
                        
                        int x = st2.executeUpdate("Update emails set notify = 0 where ID =" + rs1.getInt("ID"));
                        notify = 0;
                      
                        int r = st2.executeUpdate("Insert into emails(replyid , toid , fromid , date , title , content, status , notify) values('-1' , '" + rs1.getString("fromid") + "' , 'System Notification' , '" 
                                 + DateandTime.DateandTime() + "' , 'Your email to " + rs1.getString("toid") + " is read.' , 'This is a System generated email to notify you that the email sent to " + rs1.getString("toid") + " was read on " + rs1.getString("date") + ".' , 1 , 0)" );
                    }
                    
                }
                return "";
                
            }
            else if(eid == -1) // -1 ---> Forward emails
            {                
                for(emails e1 : allemails)
                {
                    if(e1.getToid().equals(toid) && e1.getFromid().equals(email) && e1.getContent().equals(EmailDetails().getContent()))
                    {
                        
                        return "Email sent!";
                    }
                }
                int r = st1.executeUpdate("Insert into emails(replyid , toid , fromid , date , title , content, status , notify) values('-1' , '" + toid + "','" + email + "','" + DateandTime.DateandTime() + "','Fw: " + EmailDetails().getTitle() + "','" + EmailDetails().getContent() + "','1' , '" + 0 +"')");
                return "";
                
            }
            else if(eid == 0) // 0 -----> New Emails
            {        
                
                
                for(emails e1 : allemails)
                {
                    if(e1.getToid().equals(toid) && e1.getFromid().equals(email) && e1.getContent().equals(content))
                    {
                        
                        return "Email sent!";
                    }
                }
                int r = st1.executeUpdate("Insert into emails(replyid , toid , fromid , date , title , content, status , notify) values('-1' , '" + toid + "','" + email + "','" + DateandTime.DateandTime() +"','" + title + "','" + content + "','1', '" + notify +"')");
               
                return "";
            }
            else // Reply emails
            {
                
                rs1 = st1.executeQuery("Select * from emails where ID = " + eid);
                
                if(rs1.next())
                {
                    for(emails e1 : allemails)
                    {
                        if(e1.getToid().equals(rs1.getString("fromid")) && e1.getFromid().equals(email) && e1.getContent().equals(content))
                        {
                            
                            return "Reply Sent!";
                        }
                    }
                    
                    if(rs1.getInt("replyid") == -1)
                    {
                        replyid = rs1.getInt("id");
                    }
                    else
                    {
                        replyid = rs1.getInt("replyid");
                    }
                    int r1 = st1.executeUpdate("Insert into emails(replyid , toid , fromid , date , title , content, status , notify) values('" + replyid + "','" + rs1.getString("fromid") + "','" + email + "','" + DateandTime.DateandTime() + "','Re: " + rs1.getString("title") + "','" + content + "','1' ,'" + notify +"')" );
                    
                    
                    return "";
                }
                
            }
            
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return e.toString();
            
        }
        finally
        {
            try
            {
                con.close();
                st.close();
                st1.close();
                st2.close();
                rs1.close();
               
                
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        return "Email successfully sent!";
    }
    
    public ArrayList<emails> TrashEmails()
    {
        trashemails = new ArrayList<emails>();
        
        try
        {
           Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e)
        {
            System.out.println("Please check your driver");
        }
        
        

        Connection conn=null;
       
         Statement st2=null;
        ResultSet rs2=null;

        final String DB_URL="jdbc:mysql://mis-sql.uhcl.edu/umares3563";
        
        try
       {
           conn=DriverManager.getConnection(DB_URL,"umares3563","1548736");
            st2 = conn.createStatement();
               rs2 = st2.executeQuery("select * from trash where toid ='" + emailid + "' or fromid ='" + emailid + "'");
               while(rs2.next())
                {

                    trashemails.add(new emails(rs2.getInt(1), rs2.getInt(2), rs2.getString(3), rs2.getString(4), rs2.getString(5), rs2.getString(6), rs2.getString(7), rs2.getInt(8), rs2.getInt(9)));
                }
               return trashemails;
       
       }
       catch(SQLException e)
       {
            e.printStackTrace();
           System.out.println("Internal error");
       }
       finally
       {
           try
           {
               conn.close();
               
               st2.close();
               rs2.close();
           }
           catch(Exception e)
           {
               e.printStackTrace();
           }
       }
        return null;
       
    }
    
    
    
    
    public String emailfromname(String emailid)
    {
        for(users u : fromname)
        {
            if(u.getEmailid().equals(emailid))
            {
                return u.getFirstname() + " " + u.getLastname();
            }
            
        }
        return emailid;
    }
    
    public String emailToView(int eid , String type)
    {
        viewid = eid;
        if(type.equals("i"))
        {            
            return "viewemail";
        }
        else if(type.equals("s"))
        {
            return "viewsentemail";
        }
        else if(type.equals("d"))
        {
            String page = Delete();
            return page;
        }
        return null;
    }
    
    public emails readoriginal()
    {
        allEmails();
        replyemails = new ArrayList<emails>();
        for(emails e : inboxemail)
        {
            if(e.getId() == viewid)
            {
                if(e.getStatus() == 1)
                {
                        try
                        {
                           Class.forName("com.mysql.jdbc.Driver");
                        }
                        catch(Exception e3)
                        {
                            System.out.println("Please check your driver");
                        }
        
                    Connection conn=null;
                    Statement st=null;
                    

                    final String DB_URL="jdbc:mysql://mis-sql.uhcl.edu/umares3563";

                        try
                        {
                            conn=DriverManager.getConnection(DB_URL,"umares3563","1548736");
                            st = conn.createStatement();
                            int r = st.executeUpdate("Update emails set status = 0 where ID = " + e.getId());

                        }
                        catch(SQLException e1)
                        {
                            e1.printStackTrace();
                        }
                        finally
                        {
                            try
                           {
                               conn.close();
                               st.close();
                              
                           }
                           catch(Exception e2)
                           {
                               e2.printStackTrace();
                           }
                        }
                }
                

                
                if(e.getReplyid() == -1)
                {
                    for(emails e1 : allemails)
                    {
                        if(e.getId() == e1.getReplyid())
                            replyemails.add(new emails(e1.getId(), e1.getReplyid(), e1.getToid(), e1.getFromid(), e1.getDate(), e1.getTitle(), e1.getContent(), e1.getStatus() , e1.getNotify()));
                    }
                        return e;
                    
                }
                else
                {
                    for(emails e1 : allemails)
                    {
                        if(e1.getId() == e.getReplyid())
                        {
                            for(emails e2 : allemails)
                            {
                                if(e1.getId() == e2.getReplyid())
                                {
                                    replyemails.add(new emails(e2.getId(), e2.getReplyid(), e2.getToid(), e2.getFromid(), e2.getDate(), e2.getTitle(), e2.getContent(), e2.getStatus() , e2.getNotify()));

                                }

                            }
                            return e1;
                        }
                    }
                }
                
            }
        }
        return null;
    }
    
    
    public emails viewsent()
    {        
        for(emails e : sentemail)
        {
            if(e.getId() == viewid)
            {
                return e;
            }
            
            
        }
        return null;
    }
    
    
   public emails EmailDetails()
   {
       allEmails();
       for(emails e : allemails)
       {
           if(e.getId() == viewid)
           {
               return e;
           }
       }
       return null;
   }
   
   public String Status(int id)
   {
       for(emails e : inboxemail)
       {
           if(e.getId() == id)
           {
               if(e.getStatus() == 1)
               {
                   return "(New) :";
               }
               return "";
           }
       }
       return null;
   }
   
   public String Delete()
   {
      try
        {
           Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e)
        {
            System.out.println("Please check your driver");
        }
        
        emails e = EmailDetails();

        Connection conn=null;       
        Statement st=null;
        Statement st1=null;
        

        final String DB_URL="jdbc:mysql://mis-sql.uhcl.edu/umares3563";
        
        try
       {
           conn=DriverManager.getConnection(DB_URL,"umares3563","1548736");
           st = conn.createStatement();
           st1 = conn.createStatement();
          
           
                      
           int r = st.executeUpdate("insert into trash values('"+e.getId()+"','"+e.getReplyid()+"','"+e.getToid()+"','"+e.getFromid()+"','"+e.getDate()+"','"+e.getTitle()+"','"+e.getContent()+"',0 , 0)" );
           int r1 = st1.executeUpdate("Delete from emails where ID ="+e.getId());
           
           
           
           
          return "Inbox"; 
            
       
       }
       catch(SQLException e1)
       {
            e1.printStackTrace();
           return e1.toString();
       }
       finally
       {
           try
           {
               conn.close();
               
               st.close();
               st1.close();
               
           }
           catch(Exception e2)
           {
               e2.printStackTrace();
           }
       }
       
   }

   public String signOut()
    {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index";        
    }
   
    
}