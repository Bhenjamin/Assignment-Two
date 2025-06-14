/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.HashSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Te One
 */
public class BoxModelTest {
    
    private BoxModel boxModel;
    
    public BoxModelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        boxModel = new BoxModel();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of initialiseBoxes method, of class BoxModel.
     */
    @Test
    public void testInitialiseBoxes() {
        boxModel.initialiseBoxes();
        ArrayList<Box> boxes = boxModel.getBoxList();
        assertEquals("There should be 25 boxes initialized.", 25, boxes.size());
        
        HashSet<Integer> values = new HashSet<>();
        for (Box b : boxes) {
            values.add(b.getValue());
        }

        assertEquals("All 25 box values should be unique.", 25, values.size());
    }
    
     @Test
    public void testShuffle() {
        BoxModel b1 = new BoxModel();
        BoxModel b2 = new BoxModel();
        b1.initialiseBoxes();
        b2.initialiseBoxes();

        boolean sameOrder = true;
        for (int i = 0; i < 25; i++) {
            if (b1.getBoxList().get(i).getValue() != b2.getBoxList().get(i).getValue()) {
                sameOrder = false;
                break;
            }
        }

        assertFalse("The box orders shouldnt be the same", sameOrder);
    }
    
}
