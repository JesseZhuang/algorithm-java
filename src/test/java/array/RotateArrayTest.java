package array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class RotateArrayTest {

    private void testBoth(int[] input, int k, int[] expected) {
        int[] copy1 = input.clone();
        int[] copy2 = input.clone();
        RotateArray.rotate(copy1, k);
        assertArrayEquals(expected, copy1);
        RotateArray.rotate2(copy2, k);
        assertArrayEquals(expected, copy2);
    }

    @Test
    void testExample1() {
        testBoth(new int[]{1, 2, 3, 4, 5, 6, 7}, 3, new int[]{5, 6, 7, 1, 2, 3, 4});
    }

    @Test
    void testExample2() {
        testBoth(new int[]{-1, -100, 3, 99}, 2, new int[]{3, 99, -1, -100});
    }

    @Test
    void testSingleElementKZero() {
        testBoth(new int[]{1}, 0, new int[]{1});
    }

    @Test
    void testSingleElementKOne() {
        testBoth(new int[]{1}, 1, new int[]{1});
    }

    @Test
    void testTwoElementsKOne() {
        testBoth(new int[]{1, 2}, 1, new int[]{2, 1});
    }

    @Test
    void testTwoElementsKTwo() {
        testBoth(new int[]{1, 2}, 2, new int[]{1, 2});
    }

    @Test
    void testTwoElementsKThree() {
        testBoth(new int[]{1, 2}, 3, new int[]{2, 1});
    }

    @Test
    void testKZero() {
        testBoth(new int[]{1, 2, 3, 4, 5, 6, 7}, 0, new int[]{1, 2, 3, 4, 5, 6, 7});
    }

    @Test
    void testKEqualsLength() {
        testBoth(new int[]{1, 2, 3, 4, 5, 6, 7}, 7, new int[]{1, 2, 3, 4, 5, 6, 7});
    }

    @Test
    void testKGreaterThanLength() {
        testBoth(new int[]{1, 2, 3, 4, 5, 6, 7}, 10, new int[]{5, 6, 7, 1, 2, 3, 4});
    }
}
