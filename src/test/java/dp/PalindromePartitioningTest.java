package dp;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PalindromePartitioningTest {

    private final PalindromePartitioning solution = new PalindromePartitioning();

    @Test
    void testAab() {
        List<List<String>> result = solution.partition("aab");
        assertEquals(2, result.size());
        assertTrue(result.contains(List.of("a", "a", "b")));
        assertTrue(result.contains(List.of("aa", "b")));
    }

    @Test
    void testSingleChar() {
        List<List<String>> result = solution.partition("a");
        assertEquals(List.of(List.of("a")), result);
    }

    @Test
    void testAaa() {
        List<List<String>> result = solution.partition("aaa");
        assertEquals(4, result.size());
    }

    @Test
    void testAbc() {
        List<List<String>> result = solution.partition("abc");
        assertEquals(List.of(List.of("a", "b", "c")), result);
    }

    @Test
    void testAbba() {
        List<List<String>> result = solution.partition("abba");
        assertEquals(3, result.size());
        assertTrue(result.contains(List.of("a", "b", "b", "a")));
        assertTrue(result.contains(List.of("a", "bb", "a")));
        assertTrue(result.contains(List.of("abba")));
    }

    @Test
    void testAabDFS() {
        List<List<String>> result = solution.partitionDFS("aab");
        assertEquals(2, result.size());
        assertTrue(result.contains(List.of("a", "a", "b")));
        assertTrue(result.contains(List.of("aa", "b")));
    }

    @Test
    void testSingleCharDFS() {
        List<List<String>> result = solution.partitionDFS("a");
        assertEquals(List.of(List.of("a")), result);
    }

    @Test
    void testAaaDFS() {
        List<List<String>> result = solution.partitionDFS("aaa");
        assertEquals(4, result.size());
    }

    @Test
    void testAbcDFS() {
        List<List<String>> result = solution.partitionDFS("abc");
        assertEquals(List.of(List.of("a", "b", "c")), result);
    }

    @Test
    void testAbbaDFS() {
        List<List<String>> result = solution.partitionDFS("abba");
        assertEquals(3, result.size());
        assertTrue(result.contains(List.of("a", "b", "b", "a")));
        assertTrue(result.contains(List.of("a", "bb", "a")));
        assertTrue(result.contains(List.of("abba")));
    }
}
