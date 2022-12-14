package princeton.jsl;

/******************************************************************************
 *  Compilation:  javac LongestCommonSubstringConcatenate.java
 *  Execution:    java  LongestCommonSubstringConcatenate file1.txt file2.txt
 *  Dependencies: SuffixArray.java StdOut.java In.java
 *
 *  Reads in two text strings, replaces all consecutive blocks of
 *  whitespace with a single space, and then computes the longest
 *  common substring.
 *
 *  Assumes that the character '\1' does not appear in either text.
 *  Perhaps, search for a character that does not appear in either text
 *  (and make sure SuffixArray.java doesn't choose the same one).
 *
 *  % java LongestCommonSubstringConcatenate tale.txt mobydick.txt
 *  ' seemed on the point of being '
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.SuffixArray;

/**
 * The {@code LongestCommonSubstringConcatenate} class provides a {@link SuffixArray}
 * client for computing the longest common string that appears in two
 * given strings.
 * <p>
 * This implementation computes the suffix array of a single string (the concatenation
 * of the stwo string with the character '\1' in the middle).
 * It assumes that the character '\1' does not appear in either string.
 * For an alternate implementation, see
 * <a href = "https://algs4.cs.princeton.edu/63suffix/LongestCommonSubstring.java.html">LongestCommonSubstring.java</a>.
 * <p>
 * For additional documentation,
 * see <a href="https://algs4.cs.princeton.edu/63suffix">Section 6.3</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class LongestCommonSubstringConcatenate {

    // Do not instantiate.
    private LongestCommonSubstringConcatenate() {
    }

    /**
     * Returns the longest common string of the two specified strings.
     *
     * @param s one string
     * @param t the other string
     * @return the longest common string that appears as a substring
     * in both {@code s} and {@code t}; the empty string
     * if no such string
     */
    public static String lcs(String s, String t) {
        int n1 = s.length();

        // concatenate two string with intervening '\1'
        String text = s + '\1' + t;
        int n = text.length();

        // compute suffix array of concatenated text
        SuffixArray suffix = new SuffixArray(text);

        // search for longest common substring
        String lcs = "";
        for (int i = 1; i < n; i++) {

            // adjacent suffixes both from first text string
            if (suffix.index(i) < n1 && suffix.index(i - 1) < n1) continue;

            // adjacent suffixes both from secondt text string
            if (suffix.index(i) > n1 && suffix.index(i - 1) > n1) continue;

            // check if adjacent suffixes longer common substring
            int length = suffix.lcp(i);
            if (length > lcs.length()) {
                lcs = text.substring(suffix.index(i), suffix.index(i) + length);
            }
        }
        return lcs;
    }

    /**
     * Unit tests the {@code lcs()} method.
     * Reads in two strings from files specified as command-line arguments;
     * computes the longest common substring; and prints the results to
     * standard output.
     * <p>
     * For simplicity, this client assumes that the character '\1'
     * does not appear in either string.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        In in1 = new In(args[0]);
        In in2 = new In(args[1]);
        String s = in1.readAll().trim().replaceAll("\\s+", " ");
        String t = in2.readAll().trim().replaceAll("\\s+", " ");
        StdOut.println("'" + lcs(s, t) + "'");
    }
}
