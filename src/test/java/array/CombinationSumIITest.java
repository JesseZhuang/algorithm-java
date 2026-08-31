package array;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CombinationSumIITest {
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
        assertCombinations(List.of(List.of(1,1,6), List.of(1,2,5), List.of(1,7), List.of(2,6)),
                CombinationSumII.combinationSum2(new int[]{10,1,2,7,6,1,5}, 8));
        assertCombinations(List.of(List.of(1,2,2), List.of(5)),
                CombinationSumII.combinationSum2(new int[]{2,5,2,1,2}, 5));
        assertCombinations(List.of(),
                CombinationSumII.combinationSum2(new int[]{2,4,6}, 1));
        assertCombinations(List.of(List.of(1)),
                CombinationSumII.combinationSum2(new int[]{1}, 1));
        assertCombinations(List.of(List.of(1,1,1)),
                CombinationSumII.combinationSum2(new int[]{1,1,1,1,1}, 3));
        assertCombinations(List.of(List.of(3,3,3)),
                CombinationSumII.combinationSum2(new int[]{3,3,3}, 9));
        assertCombinations(List.of(List.of(1,1,2), List.of(2,2)),
                CombinationSumII.combinationSum2(new int[]{1,1,1,2,2}, 4));
    }

    @Test
    void testCounter() {
        assertCombinations(List.of(List.of(1,1,6), List.of(1,2,5), List.of(1,7), List.of(2,6)),
                CombinationSumII.combinationSum2Counter(new int[]{10,1,2,7,6,1,5}, 8));
        assertCombinations(List.of(List.of(1,2,2), List.of(5)),
                CombinationSumII.combinationSum2Counter(new int[]{2,5,2,1,2}, 5));
        assertCombinations(List.of(),
                CombinationSumII.combinationSum2Counter(new int[]{2,4,6}, 1));
        assertCombinations(List.of(List.of(1)),
                CombinationSumII.combinationSum2Counter(new int[]{1}, 1));
        assertCombinations(List.of(List.of(1,1,1)),
                CombinationSumII.combinationSum2Counter(new int[]{1,1,1,1,1}, 3));
        assertCombinations(List.of(List.of(3,3,3)),
                CombinationSumII.combinationSum2Counter(new int[]{3,3,3}, 9));
        assertCombinations(List.of(List.of(1,1,2), List.of(2,2)),
                CombinationSumII.combinationSum2Counter(new int[]{1,1,1,2,2}, 4));
    }
}
