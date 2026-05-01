package string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConstructKPalindromeStringsTest {
    @Test
    void testCanConstruct() {
        assertTrue(ConstructKPalindromeStrings.canConstruct("annabelle", 2));
        assertFalse(ConstructKPalindromeStrings.canConstruct("leetcode", 3));
        assertTrue(ConstructKPalindromeStrings.canConstruct("true", 4));
    }
}
