
package in.crud;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.io.*;


public class JDBCCrud {
    public static Connection conn=null;
    
    
    public static void main(String args[]) throws SQLException{
        
        //Register krte ho driver ko
        //Connection estiblish krna (Code or database main connection establish ho jaega)
        //SQL queries ko execute krna hai
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            //database url, user name, password
            String url="jdbc:mysql://localhost:3306/Teacher";
            String username="root";
            String password="Ankit@9608";
            
            conn=DriverManager.getConnection(url, username, password);
            System.out.println("Sucessfully connection");
            
            insert();
            read();
            update();
            delete();
        } catch (ClassNotFoundException ex) {
            
            throw new RuntimeException("Something went wrong");
        }
        
    }
    
    
    private static void insert() throws SQLException{
        String sql="insert into teacher(teacher_name) values(?)";
        PreparedStatement stat=conn.prepareStatement(sql);
        stat.setString(1,"Ravi");
        stat.setString(1, "Kartik");//we can add more record that method
    
        int row=stat.executeUpdate();
       
        if(row>0){
            System.out.println("record inserted sucessfully  OK");
        }
            
    }
    
    private static  void read() throws SQLException{
        
        String sql="select * from teacher";
        PreparedStatement stat=conn.prepareStatement(sql);
        
        ResultSet rs=stat.executeQuery(sql);
        
        while(rs.next()){
            System.out.println(rs.getInt(1)+ " "+ rs.getString(2));
        }
    }
    
    private static void update() throws SQLException{
        
        String sql="update teacher set teacher_name= ? where teacher_id = ?";
        PreparedStatement stat=conn.prepareStatement(sql);
        stat.setString(1,"raju rastogi");
        stat.setInt(2, 3);   //  second colum me third row
        
        int rows=stat.executeUpdate();
        if(rows>0){
            System.out.println("Update is sucessfully");
        }
    }
    
    private static void delete() throws SQLException{
       
        String sql="delete from teacher where teacher_id= 5";
        PreparedStatement stat=conn.prepareStatement(sql);
        int rows=stat.executeUpdate();
        if(rows>0){
            System.out.println("Delete is sucessfully");
        }
        
    }
    
}
