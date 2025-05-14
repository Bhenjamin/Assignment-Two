/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;

/**
 *
 * @author benma
 */
public class DBScores {
    
    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;
    
    public DBScores() {
        dbManager = new DBManager();
        conn = dbManager.getConnection();
    }
    
    // Creates the Scores table if doesn't already exist
    public void createScoresTable(){
        String tableName = "SCORESTABLE";
        
        try{
            Statement table = conn.createStatement();
            dropATableIfExists(tableName);

            String tableCategories = ("CREATE TABLE "+tableName+" "
                    + "(NAME VARCHAR(50), SCORE INT)");
            
            table.executeUpdate(tableCategories);
            table.close();
            
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        
    }
    
    public ResultSet getScores(){
        ResultSet rs = null;
        try{
            statement = conn.createStatement();
            String sqlQuery = "SELECT NAME, SCORE, DISCOUNT FROM SCORESTABLE";
            rs = statement.executeQuery(sqlQuery);
        }catch (SQLException ex) {
                System.out.println(ex.getMessage());
        }
        
        return (rs);
    }
    
    public void dropATableIfExists(String tableName){
        try{
            Statement table = conn.createStatement();
            DatabaseMetaData metaData = conn.getMetaData();
            // metaData returns a result set with the tables
            ResultSet rs = metaData.getTables(null, null, tableName, new String[] {"TABLE"});
            
            if(rs.next()){// returns true if exists
                table.executeUpdate("DROP TABLE "+tableName);
            }
            
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }   
    }
}
