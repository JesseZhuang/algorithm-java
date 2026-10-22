package hash;

import java.util.Arrays;

public final class DetermineIfTwoStringsAreClose {
    private DetermineIfTwoStringsAreClose() {}

    public static boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }

        int[] firstCounts = new int[26];
        int[] secondCounts = new int[26];
        for (int index = 0; index < word1.length(); index++) {
            firstCounts[word1.charAt(index) - 'a']++;
            secondCounts[word2.charAt(index) - 'a']++;
        }

        for (int index = 0; index < 26; index++) {
            if ((firstCounts[index] == 0) != (secondCounts[index] == 0)) {
                return false;
            }
        }

        Arrays.sort(firstCounts);
        Arrays.sort(secondCounts);
        return Arrays.equals(firstCounts, secondCounts);
    }
}
