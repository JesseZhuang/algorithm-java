package array;

import java.util.Arrays;
import java.util.HashSet;

// leet 2559, medium, 7ms, 86 mb
@SuppressWarnings("unused")
public class VowelStringsRanges {

    static class Solution {
        static HashSet<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

        public int[] vowelStrings(String[] words, int[][] queries) {
            int n = words.length, s = 0, m = queries.length;
            int[] psa = new int[n + 1];
            for (int i = 0; i < words.length; i++) {
                String w = words[i];
                if (vowels.contains(w.charAt(0)) && vowels.contains(w.charAt(w.length() - 1))) s++;
                psa[i + 1] = s;
            }
            int[] res = new int[m];
            for (int i = 0; i < m; i++) {
                int l = queries[i][0], r = queries[i][1];
                res[i] = psa[r + 1] - psa[l];
            }
            return res;
        }
    }
}
