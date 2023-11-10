package array;

import java.util.Arrays;

/**
 * LeetCode 287, medium, tags: two pointers, array, binary search, bit manipulation.
 * <p>
 * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
 * <p>
 * There is only one repeated number in nums, return this repeated number.
 * <p>
 * You must solve the problem without modifying the array nums and uses only constant extra space.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,3,4,2,2]
 * Output: 2
 * Example 2:
 * <p>
 * Input: nums = [3,1,3,4,2]
 * Output: 3
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 10^5
 * nums.length == n + 1
 * 1 <= nums[i] <= n
 * All the integers in nums appear only once except for precisely one integer which appears two or more times.
 * <p>
 * Follow up:
 * <p>
 * How can we prove that at least one duplicate number must exist in nums? pigeonhole principle.
 * Can you solve the problem in linear runtime complexity?
 */
public class FindDuplicate {

    // solution 1, 2p, O(n) time, O(1) space, 4ms, 55,95Mb.
    public static int findDuplicate1(int[] nums) { // 1,3,4,2,2
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast); // first collision
        //1 3 -> 3 4 -> 2 4 -> 4 4
        slow = 0; // [2,5,9,6,9,3,8,9,7,1] stopped at slow == fast == 8 but nums[8] == 7
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        } // 1 2 -> 3 4 -> 2 2
        return slow; // entry point of the circle, second collision
    }

    // solution 2, modify input, index sort, O(n) time, O(1) space. 5ms, 56.6Mb.
    public static int findDuplicate2(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; ) { // note do not auto increment i
            int n = nums[i];
            if (n == i + 1) i++; // in place nums[i]==i+1, e.g., [1,2]
            else if (n == nums[n - 1]) return n; // nums[n-1] in place, nums[n-1]==(n-1)+1
            else {
                // swap nums[i] nums[n-1] 3,1,2,2->2,1,3,2->1,2,3,2
                nums[i] = nums[n - 1];
                nums[n - 1] = n;
            }
        }
        throw new IllegalArgumentException("not found");
    }


    // solution 3, sort, O(nLgn) time, O(lgn, stack) space. 35ms, 56.43Mb.
    public static int findDuplicate3(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) if (nums[i] == nums[i - 1]) return nums[i];
        throw new IllegalArgumentException("duplicate no found");
    }

    public static void main(String[] args) {
//        System.out.println(findDuplicate3(new int[]{1, 3, 4, 2, 2}));
        System.out.println(findDuplicate2(new int[]{3, 1, 2, 2}));
    }
}
