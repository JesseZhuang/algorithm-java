package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * LeetCode 128, medium, tags: array, hash table, union find.
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 * <p>
 * You must write an algorithm that runs in O(n) time.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 * Example 2:
 * <p>
 * Input: nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9
 * <p>
 * Constraints:
 * <p>
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 */
public class LongestConsecutiveSeq {
    // O(N) time and space. 47ms, 75.9Mb.
    public int longestConsecutiveSet(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        int max = 0;
        Iterator<Integer> it = set.iterator();
        while (it.hasNext()) {
            int n = it.next();
            if (set.contains(n - 1)) continue; // avoids O(n^2)
            int x = n + 1;
            while (set.contains(x)) x++; // while(set.contains(x++)); increments one past intended
            max = Math.max(max, x - n);
        }
        return max;
    }

    // O(Nlg*N) time, O(N) space. 339 ms, 130.4 Mb.
    public int longestConsecutiveUF(int[] nums) {
        UnionFind uf = new UnionFind(nums);
        for (int num : nums) {
            uf.union(num, num + 1);
            uf.union(num, num - 1);
        }
        return uf.maxSize;
    }

    public static void main(String[] args) {
        LongestConsecutiveSeq lcs = new LongestConsecutiveSeq();
        System.out.println(lcs.longestConsecutiveSet(new int[]{100, 4, 200, 1, 3, 2}));
        // [4,0,-4,-2,2,5,2,0,-8,-8,-8,-8,-1,7,4,5,5,-4,6,6,-3], expected 5
    }
}

class UnionFind {
    Map<Integer, Integer> parent;
    Map<Integer, Integer> size;
    int maxSize;

    UnionFind(int[] nums) {
        if (nums.length == 0) return;
        parent = new HashMap<>();
        size = new HashMap<>();
        for (int num : nums) {
            parent.put(num, num);
            size.put(num, 1);
        }
        maxSize = 1;
    }

    Integer find(int p) {
        int root = p;
        while (parent.get(root) != root) root = parent.get(root);
        while (parent.get(p) != root) {
            int temp = parent.get(p);
            parent.put(p, root);
            size.remove(p);
            p = temp;
        }
        return root;
    }

    void union(int p, int q) {
        if (!parent.containsKey(p) || !parent.containsKey(q)) return;
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        int newSize = size.get(rootP) + size.get(rootQ);
        if (newSize > maxSize) maxSize = newSize;
        if (size.get(rootP) < size.get(rootQ)) {
            parent.put(rootP, rootQ);
            size.put(rootQ, newSize);
        } else {
            parent.put(rootQ, rootP);
            size.put(rootP, newSize);
        }
    }
}
