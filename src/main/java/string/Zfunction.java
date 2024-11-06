package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * find all occurrences of the pattern inside the text.
 * O(n) time, Z array O(n) space, otherwise O(1) space.
 * <p>
 * For a string str[0..n-1], Z array is of same length as string. An element Z[i] of Z array stores length of the
 * longest substring starting from str[i] which is also a prefix of str[0..n-1]. The first entry of Z
 * array is meaning less as complete string is always prefix of itself.
 * <p>
 * Example:
 * <pre>
 * Index            0   1   2   3   4   5   6   7   8   9  10  11
 * Text             a   a   b   c   a   a   b   x   a   a   a   z
 * Z values         X   1   0   0   3   1   0   0   2   2   1   0
 * </pre>
 * <p>
 * Reference: CP-algorithms, GFG
 */
@SuppressWarnings("unused")
public class Zfunction {

    public static int[] zfunction(String s) {
        int n = s.length();
        int[] Z = new int[n];
        int l = 0, r = 0; // keep rightmost segment match [l,r), beyond r is not scanned
        for (int i = 1; i < n; i++) {
            // i inside of [l,r)
            if (i < r) Z[i] = Math.min(r - i, Z[i - l]);
            // trivial match algorithm
            while (i + Z[i] < n && s.charAt(Z[i]) == s.charAt(i + Z[i])) Z[i]++;
            if (i + Z[i] > r) { // extend, similar to Manacher's algorithm
                l = i;
                r = i + Z[i];
            }
        }
        return Z;
    }

    /**
     * m+n time and space.
     *
     * @param haystack text to look into, length n.
     * @param needle   pattern to look for, length m.
     * @return all the indices in haystack where needle is found.
     */
    public static List<Integer> search(String haystack, String needle) {
        // Create concatenated string "P$T"
        String concat = needle + "$" + haystack;
        int n = haystack.length(), m = needle.length();
        int[] Z = zfunction(concat);
        List<Integer> res = new ArrayList<>();
        // now looping through Z array for matching condition
        for (int i = 0; i < n; ++i)
            if (Z[i + m + 1] == m) res.add(i);
        return res;
    }

    /**
     * n^2, n.
     *
     * @param s string to count distinct substrings
     * @return the count.
     */
    public static int distinctSubstrings(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) { // adding a new character in each iteration
            // t:s[0,i] x:reverse of t, e.g., i=2, t:abb x:bba
            String x = new StringBuilder(s.substring(0, i + 1)).reverse().toString();
            int[] Z = zfunction(x); // [0,1,0]
            // zmax is how many prefixes of x are not found anywhere else in x
            // x's prefix of length zmax occur somewhere in x
            int zmax = Arrays.stream(Z).max().getAsInt();
            // new substrings adding ith character is len(t)-zmax ab->abb +2: abb,bb
            res += i + 1 - zmax;
        }
        return res;
    }

    /**
     * Find a string t of shortest length such that s can be concatenated from one or more copies of t.
     * See also prefix function (kmp), cp algorithms.
     *
     * @param s the input string.
     * @return the shortest such length.
     */
    public static int compression(String s) {
        int[] Z = zfunction(s);
        int i = 0, n = s.length();
        while (++i < n)
            if (n % i == 0 && i + Z[i] == n) return i;
        return n;
    }

}
