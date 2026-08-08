package dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1048 - Longest String Chain.
 *
 * Given a list of words, find the longest chain where each word is a predecessor
 * of the next (differs by exactly one inserted character at any position).
 */
public final class LongestStringChain {

    private LongestStringChain() {
    }

    /**
     * Returns the length of the longest string chain.
     * Time: O(n * L^2) where n = number of words, L = max word length.
     * Space: O(n) for the HashMap.
     */
    public static int longestStrChain(String[] words) {
        // Sort words by length so predecessors are processed first — O(n log n)
        Arrays.sort(words, (a, b) -> a.length() - b.length());

        // dp map: word -> longest chain ending at that word — O(n) space
        Map<String, Integer> dp = new HashMap<>();
        int result = 1;

        for (String word : words) { // O(n) iterations
            int best = 1;
            // Try removing each character to form a predecessor — O(L) removals
            for (int i = 0; i < word.length(); i++) {
                // Build predecessor by removing char at index i — O(L) string concat
                String predecessor = word.substring(0, i) + word.substring(i + 1);
                int prevChain = dp.getOrDefault(predecessor, 0);
                best = Math.max(best, prevChain + 1);
            }
            dp.put(word, best);
            result = Math.max(result, best);
        }

        return result;
    }
}
