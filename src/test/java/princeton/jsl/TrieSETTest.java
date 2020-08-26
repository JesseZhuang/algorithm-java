package princeton.jsl;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.CollectionUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TrieSETTest {
    static TrieSET toBeTested;

    @BeforeAll
    static void setup() {
        toBeTested = new TrieSET();
        toBeTested.add("she");
        toBeTested.add("shell");
    }

    @Test
    void testContains() {
        assertTrue(toBeTested.contains("shell"));
        assertTrue(toBeTested.contains("she"));
        assertFalse(toBeTested.contains("shel"));
    }

    @Test
    void testLongestPrefixOf() {
        assertEquals("shell", toBeTested.longestPrefixOf("shellfish"));
    }

    @Test
    void testKeysWithPrefix() {
        assertEquals(ImmutableList.of("she", "shell"), CollectionUtil.toList(toBeTested.keysWithPrefix("sh")));
    }
}
