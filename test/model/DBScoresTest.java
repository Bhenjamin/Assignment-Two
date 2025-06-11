/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author benma
 */
public class DBScoresTest {

    private DBManager dbManager;
    private Connection conn;
    private DBScores dbscores;
    private ArrayList<Player> unorderedPlayers;

    public DBScoresTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        dbManager = new DBManager();
        conn = dbManager.getConnection();
        dbscores = new DBScores(dbManager);

        dbscores.setScoreTableName("SCORESTABLETEST");
        // Incase table already exists
        dbscores.dropATableIfExists("SCORESTABLETEST");
        dbscores.createScoresTable();

        unorderedPlayers = new ArrayList<>();
        // Creates an unordered order table and adds to the table
        for (int i = 0; i < 8; i++) {
            unorderedPlayers.add(new Player("Jim" + i, i * 100));
        }
        Collections.shuffle(unorderedPlayers);

        // Adds the randomised list of player to the table
        for (Player p : unorderedPlayers) {
            dbscores.newPlayerEntry(p);
        }
    }

    @After
    public void tearDown() {
        // Removes test table after completing tests
        dbscores.dropATableIfExists("SCORESTABLETEST");
        dbManager.closeConnections();
    }

    /**
     * Test of createScoresTable method, of class DBScores.
     */
    @Test
    public void testCreateScoresTable() {
        System.out.println("createScoresTable");
        boolean tableFound = false;

        try {
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet tables = meta.getTables(null, null, "SCORESTABLETEST", null);
            if (tables.next()) {
                tableFound = true;
            } else {
                // Table does not exist
                fail("Unable to locate table");
            }

        } catch (SQLException e) {
            fail("SQLException:" + e.getMessage());
        }

        assertTrue(tableFound);

    }

    /**
     * Test of getPlayer method, of class DBScores.
     */
    @Test
    public void testGetPlayer() {
        System.out.println("getScores");
        Player expResult = new Player("Jim1", 100);

        Player result = dbscores.getPlayer("Jim1");
        assertNotNull(result);
        // This test  accounts for the newPlayerEntry() as well
        assertEquals(expResult, result);

    }

    /**
     * Test of getAllPlayers method, of class DBScores.
     */
    @Test
    public void testGetAllPlayers() {
        System.out.println("getAllPlayers");

        // The List should ordered
        ArrayList<Player> expected = new ArrayList<>(unorderedPlayers);
        Collections.sort(expected, new sortByScore());

        // This test  accounts for the newPlayerEntry() as well
        ArrayList<Player> actual = dbscores.getAllPlayers();
        assertEquals(expected, actual);

    }

    /**
     * Test of getLeaderBoard method, of class DBScores.
     */
    @Test
    public void testGetLeaderBoard() {
        System.out.println("getLeaderBoard");

        ArrayList<Player> expResult = new ArrayList<>();

        // Creates a list is in order of highest score to lowest
        for (int i = 0; i < 8; i++) {
            expResult.add(new Player("Jim" + i, i * 100));
        }
        // Add 2 placeholder players
        expResult.add(new Player("No Player Yet", 0));
        expResult.add(new Player("No Player Yet", 0));
        Collections.sort(expResult, new sortByScore());

        ArrayList<Player> actualResult = dbscores.getLeaderBoard();

        // Checks that the list is exactly ten players
        assertTrue(actualResult.size() == 10);

        // Compares the lists to see if they match
        assertEquals(expResult, actualResult);

    }

}
