package hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class UniqueNumberOfOccurrences {
    private UniqueNumberOfOccurrences() {}

    // Expected O(n) time and O(n) space.
    public static boolean uniqueOccurrences(int[] numbers) {
        Map<Integer, Integer> frequencies = new HashMap<>();
        for (int number : numbers) {
            frequencies.merge(number, 1, Integer::sum);
        }

        Set<Integer> seenFrequencies = new HashSet<>();
        for (int frequency : frequencies.values()) {
            if (!seenFrequencies.add(frequency)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Uses the problem's value range [-1000, 1000] and the fact that a value's
     * frequency is at most the input length.
     * Time and space: O(n + U), where U is the value range size (2001).
     */
    public static boolean uniqueOccurrencesByFrequencyArray(int[] numbers) {
        int[] frequencies = new int[2001];
        for (int number : numbers) {
            frequencies[number + 1000]++;
        }

        boolean[] seenFrequencies = new boolean[numbers.length + 1];
        for (int frequency : frequencies) {
            if (frequency > 0) {
                if (seenFrequencies[frequency]) {
                    return false;
                }
                seenFrequencies[frequency] = true;
            }
        }
        return true;
    }
}
