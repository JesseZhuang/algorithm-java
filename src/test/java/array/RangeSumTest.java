package array;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RangeSumTest {
    private RangeSum tbt;

    @BeforeEach
    void setup() {
        tbt = new RangeSum();
    }

    @Test()
    void testGetFromEmptyStream() {
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> tbt.get(2));
        assertEquals("no integers available", e.getMessage());
    }

    @Test
    void testAddAndGetHappyCase() {
        tbt.add(5);
        tbt.add(1);
        assertEquals(1, tbt.get(1));
    }

    @Test
    void testAddAndGetHappyCaseOneElement() {
        tbt.add(5);
        assertEquals(5, tbt.get(0));
    }

    @Test
    void testAddAndGetIndexOutOfBound() {
        tbt.add(5);
        tbt.add(1);
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> tbt.get(3));
        assertEquals("index is out of bound", e.getMessage());
    }

    @Test
    void testAddAndRangeSumHappyCase() {
        tbt.add(5);
        tbt.add(3);
        tbt.add(2);
        tbt.add(7);
        assertEquals(17, tbt.sum(0, 3));
        assertEquals(5, tbt.sum(1, 2));
    }
}
