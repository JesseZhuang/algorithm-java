package hash;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * LeetCode 710, hard, tags: array, hash table, math, binary search, sorting, randomized.
 * <p>
 * You are given an integer n and an array of unique integers blacklist. Design an algorithm to pick a random integer
 * in the range [0, n - 1] that is not in blacklist. Any integer that is in the mentioned range and not in blacklist
 * should be equally likely to be returned.
 * <p>
 * Optimize your algorithm such that it minimizes the number of calls to the built-in random function of your language.
 * <p>
 * Implement the Solution class:
 * <p>
 * Solution(int n, int[] blacklist) Initializes the object with the integer n and the blacklisted integers blacklist.
 * int pick() Returns a random integer in the range [0, n - 1] and not in blacklist.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["Solution", "pick", "pick", "pick", "pick", "pick", "pick", "pick"]
 * [[7, [2, 3, 5]], [], [], [], [], [], [], []]
 * Output
 * [null, 0, 4, 1, 6, 1, 0, 4]
 * <p>
 * Explanation
 * Solution solution = new Solution(7, [2, 3, 5]);
 * solution.pick(); // return 0, any integer from [0,1,4,6] should be ok. Note that for every call of pick,
 * // 0, 1, 4, and 6 must be equally likely to be returned (i.e., with probability 1/4).
 * solution.pick(); // return 4
 * solution.pick(); // return 1
 * solution.pick(); // return 6
 * solution.pick(); // return 1
 * solution.pick(); // return 0
 * solution.pick(); // return 4
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 10^9
 * 0 <= blacklist.length <= min(10^5, n - 1), B
 * 0 <= blacklist[i] < n
 * All the values of blacklist are unique.
 * At most 2 * 10^4 calls will be made to pick.
 */
public class RandomPickBlacklist {
    public static void main(String[] args) {
        System.out.println(new RandomPickBlacklist.Solution(7, new int[]{2, 3, 5}).pick());
    }

    // hashmap, O(B) time and space.
    static class Solution {
        int m; // n-size(blacklist)
        Random r;
        Map<Integer, Integer> map;

        // n=7, b=[2,3,5] m=4 {2:-1,3:-1,5:-1} -> {2:6,3:4,5:-1} [0,1,4,6]
        public Solution(int n, int[] blacklist) {
            map = new HashMap<>();
            for (int b : blacklist) // O(B)
                map.put(b, -1);
            m = n - map.size();
            for (int b : blacklist) { // O(B), remapping
                if (b >= m) continue; // do not forget
                while (map.containsKey(n - 1)) n--;
                map.put(b, n - 1);
                n--; // do not forget this line
            }
            r = new Random();
        }

        public int pick() {
            int p = r.nextInt(m);
            return map.getOrDefault(p, p);
        }
    }
}
