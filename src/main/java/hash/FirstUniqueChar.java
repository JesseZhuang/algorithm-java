package hash;

import java.util.HashMap;

/**
 * LeetCode 387, easy, tags: hash table, string, queue, counting.
 * <p>
 * Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "leetcode"
 * Output: 0
 * Example 2:
 * <p>
 * Input: s = "loveleetcode"
 * Output: 2
 * Example 3:
 * <p>
 * Input: s = "aabb"
 * Output: -1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 10^5, n
 * s consists of only lowercase English letters, r = 26
 */
public class FirstUniqueChar {
    // another solution look at string twice, find first count == 1, 2n time, r space, 6ms, 44.15MB with count array

    // solution 1, 17ms, 44.1Mb. n+r time, r space.
    public int firstUniqChar1(String s) {
        int l = s.length();
        HashMap<Character, int[]> map = new HashMap<>();
        for (int i = 0; i < l; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                int[] a = map.get(c);
                a[0]++;
            } else map.put(c, new int[]{1, i});
        }
        int res = l;
        for (int[] a : map.values()) {
            if (a[0] == 1) res = Math.min(res, a[1]);
        }
        return res == l ? -1 : res;
    }

    // solution 2, 5ms, 44.35Mb. n+r time, r space.
    public int firstUniqChar2(String s) {
        int l = s.length();
        int[] count = new int[26];
        int[] ind = new int[26];
        for (int i = 0; i < l; i++) {
            char c = s.charAt(i);
            count[c - 'a']++;
            ind[c - 'a'] = i;
        }
        int res = l;
        for (int i = 0; i < 26; i++) {
            if (count[i] == 1) res = Math.min(res, ind[i]);
        }
        return res == l ? -1 : res;
    }

    // solution 1, 23ms, 44.10Mb. Map, sentinel, n+r time, r space.
    public int firstUniqChar3(String s) {
        HashMap<Character, Integer> charIndex = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (charIndex.containsKey(c)) charIndex.put(c, Integer.MAX_VALUE);
            else charIndex.put(c, i);
        }
        int res = Integer.MAX_VALUE;
        if (!charIndex.isEmpty())
            for (int i : charIndex.values()) res = Math.min(res, i);
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
