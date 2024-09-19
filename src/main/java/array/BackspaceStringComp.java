package array;

/**
 * LeetCode 844, easy, tags: two pointers, string, stack, simulation.
 * <p>
 * Given two strings s and t, return true if they are equal when both are typed into empty text editors.
 * '#' means a backspace character.
 * <p>
 * Note that after backspacing an empty text, the text will continue empty.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "ab#c", t = "ad#c"
 * Output: true
 * Explanation: Both s and t become "ac".
 * Example 2:
 * <p>
 * Input: s = "ab##", t = "c#d#"
 * Output: true
 * Explanation: Both s and t become "".
 * Example 3:
 * <p>
 * Input: s = "a#c", t = "b"
 * Output: false
 * Explanation: s becomes "c" while t becomes "b".
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length, t.length <= 200
 * s and t only contain lowercase letters and '#' characters.
 * <p>
 * Follow up: Can you solve it in O(n) time and O(1) space?
 */
public class BackspaceStringComp {

    // solution 1, scan backward, O(n) time O(1) space. 0ms,41.46mb.
    public boolean backspaceCompare(String s, String t) {
        for (int i = s.length() - 1, j = t.length() - 1; ; i--, j--) {
            i = move(i, s);
            j = move(j, t);
            if (!(i >= 0 && j >= 0 && s.charAt(i) == t.charAt(j)))
                return (i == j) && (i == -1);
        }
    }

    /**
     * move index to a real char to compare
     *
     * @param i starting index
     * @param s the string to look at
     * @return the index of the real char to compare
     */
    int move(int i, String s) {
        int back = 0;
        while (i >= 0) {
            if (s.charAt(i) == '#') {
                back++;
                i--;
            } else if (back > 0) {
                back--;
                i--;
            } else break;
        }
        return i;
    }
}
