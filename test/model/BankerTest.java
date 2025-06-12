package model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class BankerTest {

    private Banker banker;
    private ArrayList<Box> boxes;

    @Before
    public void setUp() {
        banker = new Banker();
        boxes = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Box box = new Box(i + 1);
            box.setValue((i + 1) * 10000); // 10k, 20k, ..., 100k
            boxes.add(box);
        }
    }

    @Test
    public void testGetAverage_AllBoxesClosed() {
        double expectedAverage = 55000.0; 
        assertEquals(expectedAverage, banker.getAverage(boxes), 0.01);
    }

    @Test
    public void testGetAverage_SomeBoxesOpened() {
        boxes.get(0).open();
        boxes.get(9).open();
        double expectedAverage = 55000.0;
        assertEquals(expectedAverage, banker.getAverage(boxes), 0.01);
    }

    @Test
    public void testBankerOffer_ReducesOfferBelowAverage() {
        banker.bankerOffer(boxes);
        int offer = banker.getOffer();
        double average = banker.getAverage(boxes);
        assertTrue("Offer should be less than average", offer < average);
        assertTrue("Offer should be non-negative", offer >= 0);
    }

    @Test
    public void testNextRound_Progression() {
        // First round: 10 clicks
        for (int i = 0; i < 9; i++) {
            assertFalse(banker.nextRound()); // not yet changing round
        }

        assertTrue(banker.nextRound()); // Changed round on 10th call
        
        assertEquals(2, banker.getRound()); // should not read as round 2
        assertEquals(6, banker.getOfferCounter()); // counter should be changed

        for (int i = 0; i < 6; i++) banker.nextRound(); // round 2
        assertEquals(3, banker.getRound());

        for (int i = 0; i < 5; i++) banker.nextRound(); // round 3
        assertEquals(4, banker.getRound());

        for (int i = 0; i < 2; i++) banker.nextRound(); // round 4
        assertEquals(5, banker.getRound());

        for (int i = 0; i < 1; i++) banker.nextRound(); // round 5
        assertEquals(6, banker.getRound());

        assertFalse(banker.nextRound()); // game end
    }

    @Test
    public void testGetOfferCounterAndRound() {
        assertEquals(10, banker.getOfferCounter());
        assertEquals(1, banker.getRound());
    }
}
