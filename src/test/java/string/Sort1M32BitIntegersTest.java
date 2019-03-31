package string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Sort1M32BitIntegersTest {

    @Test
    void testSortIntegers() {
        int[] test = new int[]{4, 3, 2, 1, 4, 3, 2, 1};
        int[] expected = new int[]{1, 1, 2, 2, 3, 3, 4, 4};
        Sort1M32BitIntegers.sort(test);
        assertArrayEquals(expected, test);
    }

    @Test
    void testSortDirectlyIntegers() {
        int[] test = new int[]{4, 3, 2, 1, 4, 3, 2, 1};
        int[] expected = new int[]{1, 1, 2, 2, 3, 3, 4, 4};
        Sort1M32BitIntegers.sortDirectly(test);
        assertArrayEquals(expected, test);
    }

}
