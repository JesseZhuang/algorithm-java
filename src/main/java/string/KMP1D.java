package string;

/**
 * This class uses one dimensional restart table (DFA) instead of the two-dimensional array used in
 * {@link princeton.jsl.KMP}. O(n) time and O(m) space.
 * <p>
 * Find needle (m) in haystack (n).
 */
@SuppressWarnings("Unused")
public class KMP1D {
    public int[] restartTable;
    String needle;

    public KMP1D(String needle) {
        this.needle = needle;
        restartTable = restartTable(needle);
    }

    /**
     * A method to find the longest palindrome prefix.
     *
     * @param s the string to look into.
     * @return the longest palindrome prefix from s.
     */
    public static String longestPalindromePrefix(String s) {
        String combine = s + "#" + new StringBuilder(s).reverse();
        KMP1D kmp = new KMP1D(combine);
        return s.substring(0, kmp.restartTable[combine.length() - 1]);
    }

    private int[] restartTable(String needle) {
        int[] restart = new int[needle.length()];
        for (int i = 1, j = 0; i < needle.length(); ) {
            if (needle.charAt(i) == needle.charAt(j)) {
                restart[i] = ++j;
                i++;
            } else if (j == 0) i++;// nowhere to back up
            else j = restart[j - 1];
        }
        return restart;
    }

    /**
     * try to find needle in haystack
     *
     * @param haystack string to search for needle
     * @return whether needle is in haystack
     */
    public boolean inHaystack(String haystack) {
        return search(haystack) != -1;
    }

    /**
     * find needle in haystack
     *
     * @param haystack string to search for needle
     * @return -1 if not found, or the index where needle starts in haystack
     */
    public int search(String haystack) {
        int[] restart = restartTable;
        int i, j, m = needle.length();
        for (i = 0, j = 0; i < haystack.length() && j < m; ) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else if (j == 0) i++;
            else j = restart[j - 1]; // mismatch, back up j, compare with i again
        }
        if (j == m) return i - m;
        else return -1;
    }
}
