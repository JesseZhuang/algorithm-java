package heap;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindKPairsSmallestSumsTest {

    /** Sort by sum then by first element for deterministic comparison of tied sums. */
    private static List<List<Integer>> sorted(List<List<Integer>> pairs) {
        List<List<Integer>> copy = new ArrayList<>(pairs);
        copy.sort(Comparator.<List<Integer>>comparingInt(p -> p.get(0) + p.get(1))
                .thenComparingInt(p -> p.get(0)));
        return copy;
    }

    @Test
    void testExample1() {
        List<List<Integer>> expected = List.of(List.of(1, 2), List.of(1, 4), List.of(1, 6));
        assertEquals(expected, FindKPairsSmallestSums.kSmallestPairs(new int[]{1, 7, 11}, new int[]{2, 4, 6}, 3));
        assertEquals(expected, FindKPairsSmallestSums.kSmallestPairsBFS(new int[]{1, 7, 11}, new int[]{2, 4, 6}, 3));
    }

    @Test
    void testExample2() {
        List<List<Integer>> expected = List.of(List.of(1, 1), List.of(1, 1));
        assertEquals(expected, FindKPairsSmallestSums.kSmallestPairs(new int[]{1, 1, 2}, new int[]{1, 2, 3}, 2));
        assertEquals(expected, FindKPairsSmallestSums.kSmallestPairsBFS(new int[]{1, 1, 2}, new int[]{1, 2, 3}, 2));
    }

    @Test
    void testExample3() {
        List<List<Integer>> expected = List.of(List.of(1, 3), List.of(2, 3));
        assertEquals(expected, FindKPairsSmallestSums.kSmallestPairs(new int[]{1, 2}, new int[]{3}, 3));
        assertEquals(expected, FindKPairsSmallestSums.kSmallestPairsBFS(new int[]{1, 2}, new int[]{3}, 3));
    }

    @Test
    void testKLargerThanTotalPairs() {
        List<List<Integer>> expected = List.of(List.of(1, 3), List.of(1, 4), List.of(2, 3), List.of(2, 4));
        assertEquals(sorted(expected), sorted(FindKPairsSmallestSums.kSmallestPairs(new int[]{1, 2}, new int[]{3, 4}, 100)));
        assertEquals(sorted(expected), sorted(FindKPairsSmallestSums.kSmallestPairsBFS(new int[]{1, 2}, new int[]{3, 4}, 100)));
    }

    @Test
    void testSingleElementArrays() {
        List<List<Integer>> expected = List.of(List.of(5, 7));
        assertEquals(expected, FindKPairsSmallestSums.kSmallestPairs(new int[]{5}, new int[]{7}, 1));
        assertEquals(expected, FindKPairsSmallestSums.kSmallestPairsBFS(new int[]{5}, new int[]{7}, 1));
    }

    @Test
    void testEmptyArrays() {
        assertEquals(List.of(), FindKPairsSmallestSums.kSmallestPairs(new int[]{}, new int[]{1, 2}, 3));
        assertEquals(List.of(), FindKPairsSmallestSums.kSmallestPairsBFS(new int[]{}, new int[]{1, 2}, 3));
        assertEquals(List.of(), FindKPairsSmallestSums.kSmallestPairs(new int[]{1}, new int[]{}, 3));
        assertEquals(List.of(), FindKPairsSmallestSums.kSmallestPairsBFS(new int[]{1}, new int[]{}, 3));
    }

    @Test
    void testNegativeNumbers() {
        List<List<Integer>> expected = List.of(
                List.of(-5, -2), List.of(-5, 0), List.of(-3, -2), List.of(-3, 0));
        assertEquals(sorted(expected), sorted(FindKPairsSmallestSums.kSmallestPairs(new int[]{-5, -3, 0}, new int[]{-2, 0, 4}, 4)));
        assertEquals(sorted(expected), sorted(FindKPairsSmallestSums.kSmallestPairsBFS(new int[]{-5, -3, 0}, new int[]{-2, 0, 4}, 4)));
    }
}
