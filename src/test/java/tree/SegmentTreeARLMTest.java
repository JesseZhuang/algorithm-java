package tree;

import edu.princeton.cs.algs4.SegmentTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SegmentTreeARLMTest {

    private SegmentTreeARLM tbt;
    private SegmentTree tbt2;

    @BeforeEach
    void setUp() {
        tbt = new SegmentTreeARLM(new int[]{1, 3, 5, 7, 9, 11});
        tbt2 = new SegmentTree(new int[]{1, 3, 5, 7, 9, 11});
    }

    @Test
    void testRangeLazyUpdateAndQuery() {
        tbt.update(0, 5, 1);
        tbt.update(0, 5, 2);
        tbt.update(0, 5, 2);
        assertEquals(66, tbt.rSumQ(0, 5));
        // should not trigger propagation for 4 lines above, debugger to check
        assertEquals(24, tbt.rSumQ(0, 2)); // trigger propagation for node[0] only
        tbt.update(0, 3, 5); // propagates nodes: 2,5
        assertEquals(15, tbt.rSumQ(2, 2)); // propagate 1, not 3
    }

    @Test
    void testUpdateAndQuery() {
        SegmentTreeARLM tbt = new SegmentTreeARLM(new int[]{1, 3, 5});
        assertEquals(9, tbt.rSumQ(0, 2));
        tbt.update(1, 1, -1);
        assertEquals(8, tbt.rSumQ(0, 3));
    }

    @Test
    void testRangeSumQuery() {
        assertEquals(15, tbt.rSumQ(1, 3));
        assertEquals(1, tbt2.rsq(0, 0));
        assertEquals(11, tbt2.rsq(5, 5));
        assertEquals(36, tbt2.rsq(0, 5));
    }

    @Test
    void testRangeMinQuery() {
        assertEquals(1, tbt.rMinQ(0, 5));
        assertEquals(3, tbt.rMinQ(1, 4));
    }

    @Test
    void testRangeMaxQuery() {
        assertEquals(11, tbt.rMaxQ(0, 5));
        assertEquals(9, tbt.rMaxQ(1, 4));
    }

    @Test
    void testPrincetonSegmentTreeRangeSumAndUpdate() {
        assertEquals(15, tbt2.rsq(1, 3));
        assertEquals(1, tbt2.rsq(0, 0));
        // note that the method update the range to the new value, not adding delta
        tbt2.update(1, 1, 10);
        assertEquals(22, tbt2.rsq(1, 3));
        tbt2.update(1, 3, 10);
        assertEquals(30, tbt2.rsq(1, 3));
    }
}