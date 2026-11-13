package sliding;

public final class EqualSubstringWithinBudget {

    private EqualSubstringWithinBudget() {
    }

    public static int equalSubstring(String s, String t, int maxCost) {
        int left = 0;
        int cost = 0;
        int maximum = 0;

        // O(n) time: each position enters and leaves the window at most once; O(1) extra space.
        for (int right = 0; right < s.length(); right++) {
            cost += Math.abs(s.charAt(right) - t.charAt(right));
            while (cost > maxCost) {
                cost -= Math.abs(s.charAt(left) - t.charAt(left));
                left++;
            }
            maximum = Math.max(maximum, right - left + 1);
        }
        return maximum;
    }
}
