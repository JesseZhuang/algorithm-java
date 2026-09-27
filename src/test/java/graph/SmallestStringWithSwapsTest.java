package graph;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SmallestStringWithSwapsTest {

    private static List<List<Integer>> pairs(int[]... ps) {
        List<List<Integer>> list = new java.util.ArrayList<>();
        for (int[] p : ps) list.add(Arrays.asList(p[0], p[1]));
        return list;
    }

    private static void assertBoth(String s, List<List<Integer>> swapPairs, String expected) {
        assertEquals(expected, SmallestStringWithSwaps.smallestStringUF(s, swapPairs), "UF");
        assertEquals(expected, SmallestStringWithSwaps.smallestStringDFS(s, swapPairs), "DFS");
    }

    @Test
    void example1() {
        assertBoth("dcab", pairs(new int[]{0, 3}, new int[]{1, 2}), "bacd");
    }

    @Test
    void example2() {
        assertBoth("dcab", pairs(new int[]{0, 3}, new int[]{1, 2}, new int[]{0, 2}), "abcd");
    }

    @Test
    void example3() {
        assertBoth("cba", pairs(new int[]{0, 1}, new int[]{1, 2}), "abc");
    }

    @Test
    void singleChar() {
        assertBoth("a", Collections.emptyList(), "a");
    }

    @Test
    void noPairs() {
        assertBoth("dcba", Collections.emptyList(), "dcba");
    }

    @Test
    void fullChain() {
        assertBoth("edcba",
                pairs(new int[]{0, 1}, new int[]{1, 2}, new int[]{2, 3}, new int[]{3, 4}),
                "abcde");
    }

    @Test
    void disjointComponents() {
        assertBoth("dcbaf",
                pairs(new int[]{0, 1}, new int[]{2, 3}),
                "cdabf");
    }
}
