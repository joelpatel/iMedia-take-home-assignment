package com.joelpatel.iMediatakehomeassignment;

import com.joelpatel.iMediatakehomeassignment.Data.Points;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests for the Points Bean/Class.
 * Methods tested: consumePoints(), resetPoints()
 */
public class PointsTest {

    Points points;

    @BeforeEach
    public void setUp() {
        points = new Points();
    }

    @Test
    public void testSetUp() {
        assertEquals(points.getRemaining(), 1000L);
        assertEquals(points.getTotal(), 0L);
    }

    @Test
    public void testConsumeAll() {
        long value = 1000;
        points.consumePoints(1000);
        assertEquals(points.getRemaining(), 0L);
        assertEquals(points.getTotal(), value);
    }

    @Test
    public void testConsumeAllRepetitive() {
        long initialRemaining = points.getRemaining();
        long initialTotal = points.getTotal();
        long value = 100;
        for (int i = 1; i <= 10; i++) {
            points.consumePoints(100);
            assertEquals(points.getTotal(), initialTotal + value * i);
            assertEquals(points.getRemaining(), initialRemaining - value * i);
        }
    }

    @Test
    public void testReset() {
        long quota = 2000L;
        points.resetPoints(quota);
        assertEquals(points.getRemaining(), quota);
        assertEquals(points.getTotal(), 0L);
    }
}
