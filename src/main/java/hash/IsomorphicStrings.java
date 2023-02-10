package hash;

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
 * s and t consist of any valid ascii character.
 * M: number of unique characters in s and t.
 * <p>
 * <b>Summary:</b>
 * <p>
 * <ul>
 * <li>use a map, O(n) time, O(m) space.
 * <li>use radix array, O(n) time, O(m) space, smaller space for large inputs.
 * </ul>
 */
public class IsomorphicStrings {

    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            if (map.containsKey(a)) {// else corresponds to immediate if, so {} is necessary
                if (map.get(a) != t.charAt(i)) return false;
            } else {
                char b = t.charAt(i);
                // line below is O(map size) not O(1)
                if (map.values().contains(b)) return false;// do not forget 2 cannot be mapped to the same
                else map.put(a, b);
            }
        }
        return true;
    }

    /**
     * assuming no unicode characters.
     */
    public boolean isIsomorphic2(String s, String t) {
        int[] map1 = new int[256], map2 = new int[256]; // could use byte array but need cast
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i), b = t.charAt(i);
            if (map1[a] != map2[b]) return false;
            map1[a] = -i - 1; // avoid overflow
            map2[b] = -i - 1;
        }
        return true;
    }

    public boolean isIsomorphic3(String s, String t) {
        short[] map1 = new short[256], map2 = new short[256];
        for (int j = 0; j < 256; j++) {
            map1[j] = -1;
            map2[j] = -1;
        }
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i), b = t.charAt(i);
            if (map1[a] == -1 && map2[b] == -1) {
                map1[a] = (short) b;
                map2[b] = (short) a;
            } else if (map1[a] != b || map2[b] != a) return false;
        }
        return true;
    }

}
