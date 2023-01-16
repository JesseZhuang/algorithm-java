package string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestSSNRCTest {
    @Test
    void testSolution() {
        assertEquals(3, LongestSSNRC.lengthOfLongestSubstring("dvdf")); // note not 2:df but 3:vdf
        assertEquals(3, LongestSSNRC.lengthOfLongestSubstringMap("abcabcbb")); // note not 4:abcb but 3:abc
    }
}