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
    private String tableName = "SCORESTABLE"; // Default table name
    
    public DBScores(DBManager dbManager) {
        this.dbManager = dbManager;
        this.conn = dbManager.getConnection();
    }
    
    // If you want to change the name of the score table
    public void setScoreTableName(String tableName) {
        this.tableName = tableName;
    }

    // Creates the Scores table if doesn't already exist
    public void createScoresTable() {

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
    
    // Gets a specifc player data from the database
    public Player getPlayer(String playerName) {
        String sqlQuery = "SELECT NAME, SCORE FROM " + tableName + " WHERE NAME = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {
            pstmt.setString(1, playerName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("NAME");
                int score = rs.getInt("SCORE");
                return new Player(name, score);
            } else {
                return new Player("Player not found", 0);
            }

        } catch (SQLException ex) {
            System.out.println("SQL Exception for getPlayer()" + ex.getMessage());
            return null;
        }

    }
    
    // Adds a new player entry into the database
    public void newPlayerEntry(Player player) {
        String sql = "INSERT INTO " + tableName + " (NAME, SCORE) VALUES (?,?)";

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
    
    // Returns an ArrayList of all players from the database
    public ArrayList<Player> getAllPlayers() {
        ArrayList<Player> allPlayers = new ArrayList<>();
        String sql = "SELECT NAME, SCORE FROM " + tableName;
        try (
                Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql);) {

            while (rs.next()) {
                String username = rs.getString("NAME");
                int score = rs.getInt("SCORE");
                allPlayers.add(new Player(username, score));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        Collections.sort(allPlayers, new sortByScore());
        return allPlayers;
    }
    
    // Returns the top ten players and adds fillers if needed
    public ArrayList<Player> getLeaderBoard() {
        ArrayList<Player> leaderBoardPlayers = getAllPlayers();

        while (leaderBoardPlayers.size() > 10) {
            leaderBoardPlayers.remove(leaderBoardPlayers.size() - 1);
        }

        // If is less than ten players the list will fill with placeholders
        while (leaderBoardPlayers.size() < 10) {
            leaderBoardPlayers.add(new Player("No Player Yet", 0));
        }

        return leaderBoardPlayers;
    }
    
    // Deletes the table if it already exsists
    public void dropATableIfExists(String tableName) {
        try (Statement table = conn.createStatement(); ResultSet rs = conn.getMetaData().getTables(null, null, tableName, new String[]{"TABLE"});) {

            // metaData returns a result set with the tables
            if (rs.next()) {// returns true if exists
                table.executeUpdate("DROP TABLE " + tableName);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
