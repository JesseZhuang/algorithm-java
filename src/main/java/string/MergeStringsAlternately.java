package string;

/** LeetCode 1768: Merge Strings Alternately. */
public final class MergeStringsAlternately {
    private MergeStringsAlternately() {}

    // O(m + n) time and O(1) auxiliary space, excluding the output buffer.
    public static String mergeAlternately(String word1, String word2) {
        StringBuilder merged = new StringBuilder(word1.length() + word2.length());
        int first = 0;
        int second = 0;
        while (first < word1.length() && second < word2.length()) {
            merged.append(word1.charAt(first++));
            merged.append(word2.charAt(second++));
        }
        merged.append(word1, first, word1.length());
        merged.append(word2, second, word2.length());
        return merged.toString();
    }
}
