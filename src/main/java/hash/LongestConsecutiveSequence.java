package hash;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/longest-consecutive-sequence/">LeetCode 128</a>, medium,
 * tags: array, hash table, union find.
 */
public final class LongestConsecutiveSequence {
    private LongestConsecutiveSequence() {}

    /**
     * HashSet approach: only start counting from sequence starts (where num-1 not in set).
     * Time O(n), Space O(n).
     */
    public static int longestConsecutiveSet(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int maxLength = 0;
        for (int num : numSet) {
            // Only start counting from the beginning of a sequence
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentLength = 1;

                while (numSet.contains(currentNum + 1)) {
                    currentNum++;
                    currentLength++;
                }

                maxLength = Math.max(maxLength, currentLength);
            }
        }

        return maxLength;
    }

    /**
     * Union-Find approach: union adjacent numbers.
     * Time O(n * α(n)) ≈ O(n), Space O(n).
     */
    public static int longestConsecutiveUF(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        Map<Integer, Integer> parent = new HashMap<>();
        Map<Integer, Integer> size = new HashMap<>();

        // Initialize: each number is its own parent with size 1
        for (int num : nums) {
            if (!parent.containsKey(num)) {
                parent.put(num, num);
                size.put(num, 1);
            }
        }

        // Union adjacent numbers
        for (int num : nums) {
            // Union with num+1 if it exists
            if (parent.containsKey(num + 1)) {
                union(parent, size, num, num + 1);
            }
        }

        // Find the maximum size
        int maxLength = 0;
        for (int s : size.values()) {
            maxLength = Math.max(maxLength, s);
        }

        return maxLength;
    }

    private static int find(Map<Integer, Integer> parent, int x) {
        if (parent.get(x) != x) {
            parent.put(x, find(parent, parent.get(x))); // Path compression
        }
        return parent.get(x);
    }

    private static void union(Map<Integer, Integer> parent, Map<Integer, Integer> size, int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);

        if (rootX != rootY) {
            // Union by size
            if (size.get(rootX) < size.get(rootY)) {
                parent.put(rootX, rootY);
                size.put(rootY, size.get(rootY) + size.get(rootX));
            } else {
                parent.put(rootY, rootX);
                size.put(rootX, size.get(rootX) + size.get(rootY));
            }
        }
    }
}
