package sliding;

public final class LongestSemiRepetitiveSubstring {

    private LongestSemiRepetitiveSubstring() {
    }

    public static int longestSemiRepetitiveSubstring(String s) {
        int left = 0;
        int equalPairs = 0;
        int maximum = 0;

        // O(n) time: each character enters and leaves the window at most once.
        for (int right = 0; right < s.length(); right++) {
            if (right > 0 && s.charAt(right) == s.charAt(right - 1)) {
                equalPairs++;
            }

            // O(n) total shrinking across the full scan; the window uses O(1) space.
            while (equalPairs > 1) {
                if (s.charAt(left) == s.charAt(left + 1)) {
                    equalPairs--;
                }
                left++;
            }

            maximum = Math.max(maximum, right - left + 1);
        }
        return maximum;
    }
}
