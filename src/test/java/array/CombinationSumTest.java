package array;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CombinationSumTest {
    private void assertCombinations(List<List<Integer>> expected, List<List<Integer>> actual) {
        List<List<Integer>> sortedExpected = expected.stream()
                .map(l -> l.stream().sorted().toList())
                .sorted(Comparator.comparing(Object::toString))
                .toList();
        List<List<Integer>> sortedActual = actual.stream()
                .map(l -> l.stream().sorted().toList())
                .sorted(Comparator.comparing(Object::toString))
                .toList();
        assertEquals(sortedExpected, sortedActual);
    }

    @Test
    void testBacktracking() {
        assertCombinations(List.of(List.of(2,2,3), List.of(7)), CombinationSum.combinationSum(new int[]{2,3,6,7}, 7));
        assertCombinations(List.of(List.of(2,2,2,2), List.of(2,3,3), List.of(3,5)), CombinationSum.combinationSum(new int[]{2,3,5}, 8));
        assertCombinations(List.of(), CombinationSum.combinationSum(new int[]{2}, 1));
        assertCombinations(List.of(List.of(1)), CombinationSum.combinationSum(new int[]{1}, 1));
        assertCombinations(List.of(List.of(1,1)), CombinationSum.combinationSum(new int[]{1}, 2));
        assertCombinations(List.of(List.of(1,1,1,1), List.of(1,1,2), List.of(2,2)), CombinationSum.combinationSum(new int[]{1,2}, 4));
    }

    @Test
    void testDP() {
        assertCombinations(List.of(List.of(2,2,3), List.of(7)), CombinationSum.combinationSumDP(new int[]{2,3,6,7}, 7));
        assertCombinations(List.of(List.of(2,2,2,2), List.of(2,3,3), List.of(3,5)), CombinationSum.combinationSumDP(new int[]{2,3,5}, 8));
        assertCombinations(List.of(), CombinationSum.combinationSumDP(new int[]{2}, 1));
        assertCombinations(List.of(List.of(1)), CombinationSum.combinationSumDP(new int[]{1}, 1));
        assertCombinations(List.of(List.of(1,1)), CombinationSum.combinationSumDP(new int[]{1}, 2));
        assertCombinations(List.of(List.of(1,1,1,1), List.of(1,1,2), List.of(2,2)), CombinationSum.combinationSumDP(new int[]{1,2}, 4));
    }
}
