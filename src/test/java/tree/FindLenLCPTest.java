package tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindLenLCPTest {

    FindLenLCP.Solution1 sol1 = new FindLenLCP.Solution1();
    FindLenLCP.Solution2 sol2 = new FindLenLCP.Solution2();

    @Test
    void testExample1() {
        int[] arr1 = {1, 10, 100}, arr2 = {1000};
        assertEquals(3, sol1.longestCommonPrefix(arr1, arr2));
        assertEquals(3, sol2.longestCommonPrefix(arr1, arr2));
    }

    @Test
    void testExample2() {
        int[] arr1 = {1, 2, 3}, arr2 = {4, 4, 4};
        assertEquals(0, sol1.longestCommonPrefix(arr1, arr2));
        assertEquals(0, sol2.longestCommonPrefix(arr1, arr2));
    }
}
