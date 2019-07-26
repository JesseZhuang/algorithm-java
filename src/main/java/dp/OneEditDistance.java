package dp;

/**
 * LeetCode 161. Medium. FB Edit Distance. Levenshtein distance.
 * <p>
 * An edit between two strings is one of the following changes.
 * <p>
 * <ul>
 * <li>Add a character
 * <li>Delete a character
 * <li>Change a character
 * </ul>
 * Given two string s1 and s2, find if s1 can be converted to s2 with exactly one edit.
 * <p>
 * <b>Summary:</b>
 * <p>
 * <ul>
 * <li><b>traverse and keep diff count. O(n) time, O(1) space.</b>
 * <li>brute force, O(n^2) time, O(n^2) space.
 * </ul>
 * <p>https://en.wikipedia.org/wiki/Edit_distance
 *
 */
public class OneEditDistance {
    public boolean isEditDistanceOne1(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        if (Math.abs(m - n) > 1) return false;
        int count = 0; // Count of edits
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (s1.charAt(i) != s2.charAt(j)) {
                if (count == 1) return false; // quit fast
                if (m > n) i++;
                else if (m < n) j++;
                else {
                    i++;
                    j++;
                }
                count++;
            } else {
                i++;
                j++;
            }
        }
        // If last character is extra in any string
        if (i < m || j < n) count++;
        return count == 1;
    }

    public boolean isEditDistanceOne2(String s1, String s2) {
        if (s1.length() > s2.length()) {
            String temp = s1;
            s1 = s2;
            s2 = temp;
        }
        if (s2.length() - s1.length() > 1) return false;
        boolean saw_difference = false;
        for (int i = 0, j = 0; i < s1.length(); ++i, ++j) {
            if (s1.charAt(i) != s2.charAt(j)) {
                if (saw_difference) return false;
                saw_difference = true;
                if (s2.length() > s1.length()) --i;
            }
        }
        return saw_difference || s2.length() > s1.length();
    }
}
