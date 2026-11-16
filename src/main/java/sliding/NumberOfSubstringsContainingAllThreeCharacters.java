package sliding;

/**
 * LeetCode 1358, medium, tags: string, sliding window.
 * Count substrings containing at least one occurrence of each of 'a', 'b', and 'c'.
 * The input contains only these three characters and has length at most 50,000.
 */
public final class NumberOfSubstringsContainingAllThreeCharacters {

    private NumberOfSubstringsContainingAllThreeCharacters() {
    }

    // O(n) time for one scan, O(1) extra space for three last-seen indices.
    public static int numberOfSubstrings(String s) {
        int[] lastSeen = {-1, -1, -1};
        int result = 0;
        for (int right = 0; right < s.length(); right++) {
            lastSeen[s.charAt(right) - 'a'] = right;
            // Every start through the earliest last-seen index gives a valid substring ending here.
            // If a character is missing, the minimum is -1 and contributes zero.
            result += 1 + Math.min(lastSeen[0], Math.min(lastSeen[1], lastSeen[2]));
        }
        return result;
    }

    // O(n) time: both pointers advance at most n times; O(1) extra space for three counts.
    public static int numberOfSubstringsSlidingWindow(String s) {
        int[] counts = new int[3];
        int left = 0;
        int result = 0;
        for (int right = 0; right < s.length(); right++) {
            counts[s.charAt(right) - 'a']++;
            while (counts[0] > 0 && counts[1] > 0 && counts[2] > 0) {
                counts[s.charAt(left) - 'a']--;
                left++;
            }
            // After shrinking, exactly the starts in [0, left) form valid substrings ending here.
            result += left;
        }
        return result;
    }
}
