package princeton.jsl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.IntArrayUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ThreeSumTest {
    private ThreeSum tbt;

    @BeforeEach
    void init() {
        tbt = new ThreeSum();
    }

    @Test
    void testBruteForce() {
        for (int i = 0; i < 100; i++) {
            int[] unboxed = IntArrayUtil.unBoxIntegerArray(IntArrayUtil.generateRandomArray(30, 9));
            assertEquals(edu.princeton.cs.algs4.ThreeSum.count(unboxed), tbt.countBF(unboxed, 0));
        }
    }

    @Test
    void testWithDuplicates() {
        int[] a = new int[]{3, 3, 3, 3};
        assertEquals(4, tbt.countBF(a, 9)); // 4 pick 3, 4 possibilities
        assertEquals(4, tbt.count(a, 9));
        assertEquals(4, tbt.countWithMap(a, 9));

        a = new int[]{3, 3, 3, 3, 3};
        assertEquals(10, tbt.countBF(a, 9)); // 5 pick 3, 10 possibilities
        assertEquals(10, tbt.count(a, 9));
        assertEquals(10, tbt.countWithMap(a, 9));
    }
}
