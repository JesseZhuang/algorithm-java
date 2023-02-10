package hash;

import java.util.Arrays;
import java.util.HashMap;

/**
 * LeetCode 205. Easy.
 * <p>
 * Given two strings s and t, determine if they are isomorphic. Two strings are isomorphic if the
 * characters in s can be replaced to get t. All occurrences of a character must be replaced with another character
 * while preserving the order of characters. No two characters may map to the same character but a character may map to
 * itself.
 * <p>
 * For example,
 * <p>
 * <ul>
 * <li>Given "egg", "add", return true (e->a, g->d).
 * <li>Given "foo", "bar", return false (oo x -> ar).
 * <li>Given "paper", "title", return true (p->t, a->i, e->l, r->e).
 * </ul>
 * Note: You may assume both s and t have the same length.
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 5 * 10^4, N
 * t.length == s.length, N
 * s and t consist of any valid ascii character. R = 128
 * M: number of unique characters in s and t.
 */
public class IsomorphicStrings {

    private final static int R = 128;

    // 11ms, 42.4 Mb. O(N) time, O(R) space. Must use 2 maps.
    public boolean isIsomorphicMap(String s, String t) {
        HashMap<Character, Integer> map1 = new HashMap<>(), map2 = new HashMap<>();
        for (Integer i = 0; i < s.length(); i++) // note use boxed type to handle null cases
            if (map1.put(s.charAt(i), i) != map2.put(t.charAt(i), i)) return false;
        return true;
    }

    // 5ms, 42 Mb. O(N) time, O(R) space.
    public boolean isIsomorphicArray(String s, String t) {
        int[] map1 = new int[R], map2 = new int[R];
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i), b = t.charAt(i);
            if (map1[a] != map2[b]) return false;
            map1[a] = -i - 1; // avoid overflow
            map2[b] = -i - 1;
        }
        return true;
    }

    // 3ms, 42.1 Mb. O(N) time, O(R) space.
    public boolean isIsomorphicArray2(String s, String t) {
        short[] map1 = new short[R], map2 = new short[R]; // short 2 bytes
        Arrays.fill(map1, (short) -1);
        Arrays.fill(map2, (short) -1);
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i), b = t.charAt(i);
            if (map1[a] == -1 && map2[b] == -1) {
                map1[a] = (short) b; // map to short, not index
                map2[b] = (short) a;
            } else if (map1[a] != b || map2[b] != a) return false;
        }
        return true;
    }

}
