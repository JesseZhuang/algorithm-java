package string;

/**
 * Manacher's algorithm can find the longest palindrome substring. O(n) time and space.
 * <p>
 * References:
 * 1. algs4 https://algs4.cs.princeton.edu/53substring/Manacher.java.html
 * 2. hackermoon https://hackernoon.com/manachers-algorithm-explained-longest-palindromic-substring-22cb27a5e96f
 * 3. GFG https://www.geeksforgeeks.org/manachers-algorithm-linear-time-longest-palindromic-substring-part-4/
 */
public class Manacher {
    public int mppl; // max palindrome prefix length
    public int[] p;  // p[i] = length of longest palindromic substring of t, centered at i, or radius of padded
    public char[] t;  // transformed string
    private String s;  // original string

    public Manacher(String s) {
        this.s = s;
        preprocess();
        p = new int[t.length];
        // palindrome t[center-p[i],center+p[i]] right==center+p[i]
        int center = 0, right = 0; // center and right boundary
        for (int i = 1; i < t.length - 1; i++) {
            int mirror = 2 * center - i; // mirror index of i, e.g., i:2,center:3,mirror:2*3-2==4
            if (right > i) // within current palin, update p[i] to minimum possible expansion length
                p[i] = Math.min(right - i, p[mirror]);
            // attempt to expand palindrome centered at i, 1+p[i] because of padding
            while (t[i + (1 + p[i])] == t[i - (1 + p[i])]) p[i]++;
            // if palindrome centered at i expands past right,
            // adjust center based on expanded palindrome.
            if (i + p[i] > right) {
                center = i;
                right = i + p[i];
            }
            if (i - p[i] == 1) mppl = Math.max(mppl, p[i]); // can reach to beginning
        }
    }

    // Transform s into t.
    // For example, if s = "abba", then t = "$#a#b#b#a#@"
    // the # are interleaved to avoid even/odd-length palindromes uniformly
    // $ and @ are prepended and appended to each end to avoid bounds checking
    private void preprocess() {
        t = new char[s.length() * 2 + 3];
        t[0] = '$';
        for (int i = 0; i < s.length(); i++) {
            t[2 * i + 1] = '#';
            t[2 * i + 2] = s.charAt(i);
        }
        t[s.length() * 2 + 1] = '#';
        t[s.length() * 2 + 2] = '@';
    }

    // longest palindromic substring
    public String longestPalindromicSubstring() {
        int length = 0;   // length of longest palindromic substring
        int center = 0;   // center of longest palindromic substring
        for (int i = 1; i < p.length - 1; i++) {
            if (p[i] > length) {
                length = p[i];
                center = i;
            }
        }
        return s.substring((center - 1 - length) / 2, (center - 1 + length) / 2);
    }

    /**
     * number of palindromes at this center == (p[center] + 1) / 2
     * e.g., aba(b,aba), cnt == 2 == (len:3 +1)/2; a 1 == (1+1)/2; abba(bb,abba) 2 == (4+1)/2;
     *
     * @return total number of palindrome substrings.
     */
    public int cntPalindromeSubstring() {
        int res = 0;
        for (int i = 1; i < p.length - 1; i++) res += (p[i] + 1) / 2;
        return res;
    }

    /**
     * longest palindromic substring centered at index i in padded string (pad # in between letters).
     *
     * @param i, center index, should be in [0,2*s.length()-2]
     * @return longest palindrome centered at index i in padded string. For example string abba:
     * padded string is a#b#b#a
     * i,char,longest palindrome are
     * 0,a,a
     * 1,#,""
     * 2,b,b
     * 3,#,abba
     * 4,b,b
     * 5,#,""
     * 6,a,""
     * {@link ManacherTest} see unit test.
     */
    public String longestPalindromicSubstring(int i) {
        int length = p[i + 2];  //padded $# so shift 2
        int center = i + 2;
        return s.substring((center - 1 - length) / 2, (center - 1 + length) / 2);
    }

}
