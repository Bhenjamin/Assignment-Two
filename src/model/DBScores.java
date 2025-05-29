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

    public DBScores(DBManager dbManager) {
        this.dbManager = dbManager;
        this.conn = dbManager.getConnection();
    }

    // Creates the Scores table if doesn't already exist
    public void createScoresTable() {
        String tableName = "SCORESTABLE";

        try (Statement table = conn.createStatement();) {

            dropATableIfExists(tableName);

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
        try {
            statement = conn.createStatement();
            String sqlQuery = "SELECT NAME, SCORE FROM SCORESTABLE";
            rs = statement.executeQuery(sqlQuery);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return (rs);
    }

    public void newPlayerEntry(Player player) {
        String sql = "INSERT INTO SCORESTABLE (NAME, SCORE) VALUES (?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, player.getUsername());
            pstmt.setInt(2, player.getOfferTaken());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
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
