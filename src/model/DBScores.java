/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author benma
 */
public class DBScores {

    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;

    public DBScores(DBManager dbManager) {
        this.dbManager = dbManager;
        this.conn = dbManager.getConnection();
    }

    // Creates the Scores table if doesn't already exist
    public void createScoresTable() {
        String tableName = "SCORESTABLE";

        try (Statement table = conn.createStatement();) {

            String tableCategories = ("CREATE TABLE " + tableName + " "
                    + "(NAME VARCHAR(50), SCORE INT)");

            table.executeUpdate(tableCategories);
            table.close();
            System.out.println("SCORESTABLE created successfully");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public ResultSet getScores() {
        ResultSet rs = null;
        String sqlQuery = "SELECT NAME, SCORE FROM SCORESTABLE";
        try {
            statement = conn.createStatement();
            rs = statement.executeQuery(sqlQuery);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return (rs);
    }

    public void newPlayerEntry(Player player) {
        System.out.println("Player data entered sucessfully");
        String sql = "INSERT INTO SCORESTABLE (NAME, SCORE) VALUES (?,?)";
        
        String playerName = player.getUsername();
        int playerScore = player.getOfferTaken();
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, playerName);
            pstmt.setInt(2, playerScore);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public ArrayList<Player> getAllPlayers(){
        ArrayList<Player> allPlayers = new ArrayList<>();
        String sql = "SELECT NAME, SCORE FROM SCORESTABLE";
        try{
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            
            while(rs.next()){
                String username = rs.getString("NAME");
                int score = rs.getInt("SCORE");
                allPlayers.add(new Player(username, score));
                
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        
        Collections.sort(allPlayers, new sortByScore());
        return allPlayers;
    }
    
    public ArrayList<Player> getLeaderBoard(){
        ArrayList<Player> leaderBoardPlayers = getAllPlayers();
        
        while(leaderBoardPlayers.size() > 10){
            leaderBoardPlayers.remove(leaderBoardPlayers.size()-1);
        }
        
        // If is less than ten players the list will fill with placeholders
        while(leaderBoardPlayers.size() < 10){
            leaderBoardPlayers.add(new Player("No Player Yet", 0));
        }
            
        return leaderBoardPlayers;
    }
    


    public void dropATableIfExists(String tableName) {
        try (Statement table = conn.createStatement(); 
                ResultSet rs = conn.getMetaData().getTables(null, null,tableName,new String[]{"TABLE"});) {

            // metaData returns a result set with the tables
            if (rs.next()) {// returns true if exists
                table.executeUpdate("DROP TABLE " + tableName);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
