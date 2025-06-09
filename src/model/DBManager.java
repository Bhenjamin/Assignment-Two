/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author benma
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class DBManager {

    private static final String USER_NAME = "PDC"; //your DB username
    private static final String PASSWORD = "PDC"; //your DB password
    private static final String URL = "jdbc:derby:DOND_db;create=true";  //url of the DB host
    
    Connection conn;

    public DBManager() {
        establishConnection();
    }

    public Connection getConnection() {
        return this.conn;
    }

    //Establish connection
    public void establishConnection() {
        //Establish a connection to Database
        try{
            conn = DriverManager.getConnection(URL,USER_NAME,PASSWORD);
            System.out.println("Connection Successful");
        }catch(SQLException e){
            System.err.println("SQLException: "+e.getMessage());
            e.printStackTrace();
        }
    }

    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    } 

}
