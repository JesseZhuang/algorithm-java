package princeton.jsl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TSTTest {
    TST toBeTested;

    @Test
    void testLongestPrefixOf() {
        toBeTested = new TST();
        toBeTested.put("she", 1);
        toBeTested.put("shell", 2);

        assertEquals("shell", toBeTested.longestPrefixOf("shellfish"));
    }
}
