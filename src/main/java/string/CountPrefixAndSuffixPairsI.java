package string;

/** LeetCode 3042: Count Prefix and Suffix Pairs I. */
public final class CountPrefixAndSuffixPairsI {
    private CountPrefixAndSuffixPairsI() {}

    // O(n^2 * L) time for n words of maximum length L; O(1) auxiliary space.
    public static int countPrefixSuffixPairs(String[] words) {
        int count = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (words[j].startsWith(words[i]) && words[j].endsWith(words[i])) {
                    count++;
                }
            }
        }
        return count;
    }
}
