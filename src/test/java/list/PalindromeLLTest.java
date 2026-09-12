package list;

import org.junit.jupiter.api.Test;
import struct.ListNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PalindromeLLTest {

    private void verify(int[] input, boolean expected) {
        assertEquals(expected, PalindromeLL.isPalindrome(ListNode.createFromArray(input)));
        assertEquals(expected, PalindromeLL.isPalindromeStack(ListNode.createFromArray(input)));
    }

    @Test
    void testEvenPalindrome() {
        verify(new int[]{1, 2, 2, 1}, true);
    }

    @Test
    void testNotPalindrome() {
        verify(new int[]{1, 2}, false);
    }

    @Test
    void testSingleNode() {
        verify(new int[]{1}, true);
    }

    @Test
    void testOddPalindrome() {
        verify(new int[]{1, 2, 1}, true);
    }

    @Test
    void testOddNotPalindrome() {
        verify(new int[]{1, 2, 3}, false);
    }

    @Test
    void testAllSame() {
        verify(new int[]{5, 5, 5, 5}, true);
    }

    @Test
    void testTwoSame() {
        verify(new int[]{1, 1}, true);
    }

    @Test
    void testBoundaryValues() {
        verify(new int[]{0, 9, 0}, true);
        verify(new int[]{0, 9, 1}, false);
    }
}
