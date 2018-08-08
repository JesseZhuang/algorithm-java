package hash;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * LeetCode 217. Easy.
 * <p>
 * Given an array of integers, find if the array contains any
 * duplicates. Your function should return true if any value appears at least
 * twice in the array, and it should return false if every element is distinct.
 * <pre>
 * Example 1:
 * Input: [1,2,3,1]
 * Output: true
 *
 * Example 2:
 * Input: [1,2,3,4]
 * Output: false
 *
 * Example 3:
 * Input: [1,1,1,3,3,4,3,2,4,2]
 * Output: true
 * </pre>
 * <p>
 * <b>Summary</b>:
 * <p>
 * <ul>
 * <li>use a HashSet, O(N) time, O(N) space.
 * <li>brute force quadratic time, no space.
 * <li>sorting
 * <ul>
 * <li>heap sort O(NlgN) time, in place, not stable doesn't matter here.
 * <li>quick sort O(NlgN) probabilistic guarantee, O(N^2) worst case, in place, not stable.
 * <li>merge sort O(NlgN), stable, not in place O(N) space.
 * </ul>
 * <li>maybe create BST O(NlgN) time, O(N) space.
 * </ul>
 */
public class ContainsDuplicate {

    /**
     * hash set add operation takes constant time(average case, worst case lgN can
     * typically be avoided), so overall O(N) time, O(N) space.
     */
    public boolean containsDuplicateSet(int[] numbers) {
        if (numbers == null || numbers.length == 0) return false;
        final Set<Integer> distinct = new HashSet<>();
        for (int num : numbers) {
            // good trick on using the return boolean from add()
            if (!distinct.add(num)) {
                return true;
            }
        }
        return false;
    }

    // syntactic sugar, functional version
    public boolean containsDuplicate2(int[] numbers) {
        if (numbers == null || numbers.length == 0) return false;
        Set<Integer> seen = new HashSet<>();
        return Arrays.stream(numbers).anyMatch(num -> !seen.add(num));
    }

    // test machine glitch, should have passed
    public boolean containsDuplicate4(int[] numbers) {
        // [] empty array considered false!
        if (numbers != null) {
            HashSet<Integer> set = new HashSet<>();
            for (int num : numbers) {
                if (set.contains(num)) return true;
                else set.add(num);
            }
        }
        return false;
    }

    // time limit exceeded, n lgn time, 0 space
    public boolean containsDuplicate(int[] numbers) {
        if (numbers == null || numbers.length == 0) return false;
        if (numbers.length > 1) {
            Arrays.sort(numbers);
            for (int i = 0; i < numbers.length - 1; i++) {
                if (numbers[i] == numbers[i + 1]) return true;
            }
        }
        return false;

    }

    // functional approach
    public boolean containsDuplicate1(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        // uses linked hash set, states not efficient recommends int specific map/set
        // see java.util.stream package, Stream interface, IntPipeline class
        return nums.length != Arrays.stream(nums).distinct().count();
    }
}
