package dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestSKRCTest {
    @Test
    void testLongestSKRC() {
        // linked hash map method, when look at 2 unique letters, see a (r==5), when removing c from map
        // one letter b gone, count of b should be decremented, non-trivial to fix
        assertEquals(3, LongestSKRC.longestSubstring1("cbcbbaaa", 3));
    }
}
