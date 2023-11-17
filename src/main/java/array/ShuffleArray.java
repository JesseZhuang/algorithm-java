package array;

import java.util.Random;

/**
 * LeetCode 384, medium, tags: array, math, randomized.
 * <p>
 * Given an integer array nums, design an algorithm to randomly shuffle the array. All permutations of the array
 * should be equally likely as a result of the shuffling.
 * <p>
 * Implement the Solution class:
 * <p>
 * Solution(int[] nums) Initializes the object with the integer array nums.
 * int[] reset() Resets the array to its original configuration and returns it.
 * int[] shuffle() Returns a random shuffling of the array.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["Solution", "shuffle", "reset", "shuffle"]
 * [[[1, 2, 3]], [], [], []]
 * Output
 * [null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]
 * <p>
 * Explanation
 * Solution solution = new Solution([1, 2, 3]);
 * solution.shuffle();    // Shuffle the array [1,2,3] and return its result.
 * // Any permutation of [1,2,3] must be equally likely to be returned.
 * // Example: return [3, 1, 2]
 * solution.reset();      // Resets the array back to its original configuration [1,2,3]. Return [1, 2, 3]
 * solution.shuffle();    // Returns the random shuffling of array [1,2,3]. Example: return [1, 3, 2]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 50
 * -10^6 <= nums[i] <= 10^6
 * All the elements of nums are unique.
 * At most 10^4 calls in total will be made to reset and shuffle.
 * <p>
 * Hint1
 * <p>
 * The solution expects that we always use the original array to shuffle() else some test cases fail.
 * (Credits; @snehasingh31)
 */
public class ShuffleArray {
    /**
     * Your Solution object will be instantiated and called as such:
     * Solution obj = new Solution(nums);
     * int[] param_1 = obj.reset();
     * int[] param_2 = obj.shuffle();
     * <p>
     * solution 1, fisher yates shuffle. 53ms, 49.64 Mb.
     */
    class Solution {
        int[] nums;
        int[] original;
        Random r;

        public Solution(int[] nums) {
            this.nums = nums;
            original = nums.clone();
            r = new Random();
        }

        public int[] reset() {
            nums = original;
            original = original.clone();
            return nums;
        }

        public int[] shuffle() {
            shuffle(nums);
            return nums;
        }

        void shuffle(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                int j = r.nextInt(i + 1);
                if (i == j) continue; // note not return
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
    }
}
