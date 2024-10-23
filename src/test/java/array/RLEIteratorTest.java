package array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RLEIteratorTest {

    Solution2.RLEIterator tbt;

    @Test
    void next() {
        int[] encoding = {3, 8, 2, 5};
        tbt = new Solution2.RLEIterator(encoding);
        assertEquals(8, tbt.next(2));
        assertEquals(5, tbt.next(2));
        assertEquals(5, tbt.next(1));
        assertEquals(-1, tbt.next(1));
    }
}