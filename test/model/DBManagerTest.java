/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model;

import java.sql.*;
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
public class DBManagerTest {

    private DBManager dbManager;
    private Connection conn;

    public DBManagerTest() {
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
    }

    @After
    public void tearDown() {
        dbManager.closeConnections();
    }

    /**
     * Test of getConnection method, of class DBManager.
     */
    @Test
    public void testGetConnection() {
        System.out.println("getConnection");
        assertNotNull("Database connection should not be null");
    }

    /**
     * Test of establishConnection method, of class DBManager.
     */
    @Test
    public void testEstablishConnection() {
        System.out.println("establishConnection");

        // Checks if connection is null
        assertNotNull("Connection should not be null when establishing connection", conn);
        // Checks if connection doesn't open and if an SQL error is thrown
        try {
            assertFalse("Connection should be open", conn.isClosed());
        } catch (SQLException e) {
            fail("SQLException is thrown when checking connection: \n" + e.getMessage());
        }
    }

}
