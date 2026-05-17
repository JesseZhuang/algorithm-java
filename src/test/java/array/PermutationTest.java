package array;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PermutationTest {

    /**
     * Normalize results to a set of sorted lists for order-independent comparison.
     */
    private Set<List<Integer>> toSet(List<List<Integer>> result) {
        return result.stream()
                .map(l -> List.copyOf(l))
                .collect(Collectors.toSet());
    }

    private Set<List<Integer>> expectedPerms(int[] nums) {
        // generate expected permutations via simple recursive approach
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        generateExpected(nums, used, new ArrayList<>(), result);
        return new HashSet<>(result);
    }

    private void generateExpected(int[] nums, boolean[] used, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(List.copyOf(current));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                used[i] = true;
                current.add(nums[i]);
                generateExpected(nums, used, current, result);
                current.remove(current.size() - 1);
                used[i] = false;
            }
        }
    }

    @Test
    void testPermuteI_basic() {
        Permutation p = new Permutation();
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = p.permuteI(nums);
        assertEquals(6, result.size());
        assertEquals(expectedPerms(nums), toSet(result));
    }

    @Test
    void testPermuteI_twoElements() {
        Permutation p = new Permutation();
        int[] nums = {0, 1};
        List<List<Integer>> result = p.permuteI(nums);
        assertEquals(2, result.size());
        assertEquals(expectedPerms(nums), toSet(result));
    }

    @Test
    void testPermuteI_singleElement() {
        Permutation p = new Permutation();
        int[] nums = {1};
        List<List<Integer>> result = p.permuteI(nums);
        assertEquals(1, result.size());
        assertEquals(Set.of(List.of(1)), toSet(result));
    }

    @Test
    void testPermuteI_negatives() {
        Permutation p = new Permutation();
        int[] nums = {-1, 0};
        List<List<Integer>> result = p.permuteI(nums);
        assertEquals(2, result.size());
        assertEquals(expectedPerms(nums), toSet(result));
    }

    @Test
    void testPermuteI_four() {
        Permutation p = new Permutation();
        int[] nums = {1, 2, 3, 4};
        List<List<Integer>> result = p.permuteI(nums);
        assertEquals(24, result.size());
        assertEquals(24, toSet(result).size()); // all unique
    }

    @Test
    void testPermuteI_six() {
        Permutation p = new Permutation();
        int[] nums = {1, 2, 3, 4, 5, 6};
        List<List<Integer>> result = p.permuteI(nums);
        assertEquals(720, result.size());
        assertEquals(720, toSet(result).size());
    }

    @Test
    void testSolutionRecur_basic() {
        Permutation.SolutionRecur s = new Permutation.SolutionRecur();
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = s.permute(nums);
        assertEquals(6, result.size());
        assertEquals(expectedPerms(nums), toSet(result));
    }

    @Test
    void testSolutionRecur_twoElements() {
        Permutation.SolutionRecur s = new Permutation.SolutionRecur();
        int[] nums = {0, 1};
        List<List<Integer>> result = s.permute(nums);
        assertEquals(2, result.size());
        assertEquals(expectedPerms(nums), toSet(result));
    }

    @Test
    void testSolutionRecur_singleElement() {
        Permutation.SolutionRecur s = new Permutation.SolutionRecur();
        int[] nums = {1};
        List<List<Integer>> result = s.permute(nums);
        assertEquals(1, result.size());
        assertEquals(Set.of(List.of(1)), toSet(result));
    }

    @Test
    void testSolutionRecur_negatives() {
        Permutation.SolutionRecur s = new Permutation.SolutionRecur();
        int[] nums = {-1, 0};
        List<List<Integer>> result = s.permute(nums);
        assertEquals(2, result.size());
        assertEquals(expectedPerms(nums), toSet(result));
    }

    @Test
    void testSolutionRecur_four() {
        Permutation.SolutionRecur s = new Permutation.SolutionRecur();
        int[] nums = {1, 2, 3, 4};
        List<List<Integer>> result = s.permute(nums);
        assertEquals(24, result.size());
        assertEquals(24, toSet(result).size());
    }

    @Test
    void testSolutionRecur_six() {
        Permutation.SolutionRecur s = new Permutation.SolutionRecur();
        int[] nums = {1, 2, 3, 4, 5, 6};
        List<List<Integer>> result = s.permute(nums);
        assertEquals(720, result.size());
        assertEquals(720, toSet(result).size());
    }
}
