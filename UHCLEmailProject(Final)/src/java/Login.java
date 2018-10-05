/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author gaura
 */
@ManagedBean
@SessionScoped
public class Login implements Serializable {
    private String emailid;
    private String password;
    private int notlogin;
    private Account loggedInId;
    private String firstname;
    private String lastname;
    

    public Account getLoggedInId() {
        return loggedInId;
    }

    
    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
   public String Login()
   {
       try
       {
          Class.forName("com.mysql.jdbc.Driver");
       }
       catch(Exception e)
       {
           return("Please check your driver");
       }
       
       Connection conn=null;
       Statement st=null;
       ResultSet rs=null;
       
       final String DB_URL="jdbc:mysql://mis-sql.uhcl.edu/umares3563";
       
       try
       {
           conn=DriverManager.getConnection(DB_URL,"umares3563","1548736");
           st=conn.createStatement();
           rs=st.executeQuery("Select * from users where emailid='"+emailid+"'");
           if(rs.next())
           {
               if(!rs.getString("password").equals(password))
               {
                   emailid = "";
                   password ="";
                   notlogin = 2;
                   return("conlogin");
               }
               emailid = "";
               password ="";
                firstname = rs.getString("firstname");
                lastname = rs.getString("lastname");
               loggedInId = new Account(rs.getString("emailid") ,rs.getString("firstname"), rs.getString("lastname"));

               return("Welcome");
           }
           else
           {
               emailid = "";
               password ="";
               notlogin = 1;
               return "conlogin";
               
           }
       }
       catch(SQLException e)
       {
           e.printStackTrace();
           return(e.toString());
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
       
   }
   
   public String loginUn()
   {
       if(notlogin == 1)
       {
           return "Your email id does not exists! You need to register first!";
       }
       else
       {
           return "Your password is incorrect. Please try again!";
       }
   }
   
   
   
    
    
}
