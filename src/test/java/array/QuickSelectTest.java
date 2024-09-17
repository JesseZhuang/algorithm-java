package array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuickSelectTest {


    @Test
    void kthLargest() {
        int[] a = {3, 2, 1, 5, 6, 4};
        assertEquals(5, QuickSelect.kthLargest(a, 2));
        a = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
        assertEquals(4, QuickSelect.kthLargest(a, 4));
    }

    @Test
    void kthSmallest() {
        int[] a = {3, 2, 1, 5, 6, 4};
        assertEquals(2, QuickSelect.kthSmallest(a, 2));
        a = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
        assertEquals(3, QuickSelect.kthSmallest(a, 4));
        assertEquals(3, QuickSelect.kthSmallest(a, 5));
    }
}