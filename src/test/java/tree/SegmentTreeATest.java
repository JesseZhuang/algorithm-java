package tree;

import edu.princeton.cs.algs4.SegmentTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SegmentTreeATest {

    private SegmentTreeA tbt;
    private SegmentTree tbt2;

    @BeforeEach
    void setUp() {
        tbt = new SegmentTreeA(new int[]{1, 3, 5, 7, 9, 11});
        tbt2 = new SegmentTree(new int[]{1, 3, 5, 7, 9, 11});
    }

    @Test
    void testPrincetonSegmentTreeRangeSumAndUpdate() {
        assertEquals(15, tbt2.rsq(1, 3));
        assertEquals(1, tbt2.rsq(0, 0));
        // note that the method update the range to the new value, not adding delta
        tbt2.update(1, 1, 10);
        assertEquals(22, tbt2.rsq(1, 3));
        tbt2.update(1,3, 10);
        assertEquals(30, tbt2.rsq(1, 3));
    }
}