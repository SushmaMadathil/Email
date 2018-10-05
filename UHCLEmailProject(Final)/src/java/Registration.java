/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author gaura
 */
@Named(value = "registration")
@ManagedBean
@RequestScoped
public class Registration {
    private String fname;
    private String lname;
    private String password;
    private String emailid;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }
    
    
    

    
    public String Register()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e)
        {
            return("Error:Internal error please check your driver");
        }
        
        if(!emailid.matches("[a-zA-Z0-9]"))
        {
            return "Email Id must contain both letters and numbers.";
        }
        
        if(emailid.length() < 3)
        {
            return "Email Id ---> Too short. Minimum 3 characters required!";
        }
        
        if(emailid.length() > 10)
        {
            return "Email Id ---> Too long. Maximum 10 characters allowed!";
        }
        
        
        
        
        Connection conn=null;
        ResultSet rs=null;
        Statement st=null;
        
        final String DB_URL="jdbc:mysql://mis-sql.uhcl.edu/umares3563";
        try
        {
            conn=DriverManager.getConnection(DB_URL,"umares3563","1548736");
            st=conn.createStatement();
            rs=st.executeQuery("Select * from users where emailid='"+emailid+"'");
            if(rs.next())
            {
                return ("Email already exists");
            }
            else
            {
                int r=st.executeUpdate("Insert into users values('"+fname+"','"+lname+"','"+emailid+"','"+password+"')");
                return ("Registration Successfull");
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return("Please try again later");
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
    
   
}
