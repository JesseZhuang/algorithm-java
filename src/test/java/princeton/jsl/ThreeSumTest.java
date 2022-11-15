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
    void testBruteForce () {
        for (int i = 0; i < 100; i++) {
            int[] unboxed = IntArrayUtil.unBoxIntegerArray(IntArrayUtil.generateRandomArray(30, 9));
            assertEquals(edu.princeton.cs.algs4.ThreeSum.count(unboxed), tbt.countBF(unboxed, 0));
        }
    }
}
