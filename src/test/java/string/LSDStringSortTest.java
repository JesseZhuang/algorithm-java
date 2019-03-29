package string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class LSDStringSortTest {
    @Test
    void testLSDIndexSort() {
        String[] test = new String[] {"b", "b", "a", "c"};
        assertArrayEquals(new int[] {2, 0, 1, 3}, LSDStringSort.indexSort(test, 1)); // stable!
    }
}
