package tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinaryIndexedTreeTest {
    private BinaryIndexedTree tbt;

    @BeforeEach
    void setup() {
        tbt = new BinaryIndexedTree(new int[]{2, 1, 1, 2, 5});
    }

    @Test
    void testGetSumAndUpdate() {
        assertEquals(2, tbt.getSum(0));
        assertEquals(0, tbt.getSum(-1));
        assertEquals(6, tbt.getSum(3));
        assertEquals(11, tbt.getSum(4));
        tbt.update(3, 4);
        assertEquals(15, tbt.getSum(4));
        assertEquals(10, tbt.getSum(3));
        tbt.update(3, -3);
        assertEquals(7, tbt.getSum(3));
    }

    @Test
    void testRangeQuery() {
        assertEquals(3, tbt.rsq(0, 1));
        assertEquals(4, tbt.rsq(1, 3));
    }
}
