package array;

import java.util.Arrays;

/**
 * LeetCode 274: compute a researcher's h-index from citation counts.
 */
public final class HIndex {

    private HIndex() {
    }

    /**
     * Counts citation buckets, grouping every count greater than n into bucket n.
     * O(n) time and O(n) extra space.
     */
    public static int hIndex(int[] citations) {
        int n = citations.length;
        int[] counts = new int[n + 1];
        for (int citationCount : citations) {
            counts[Math.min(citationCount, n)]++;
        }

        int papersAtLeastH = 0;
        for (int h = n; h >= 0; h--) {
            papersAtLeastH += counts[h];
            if (papersAtLeastH >= h) return h;
        }
        return 0;
    }

    /**
     * Sorts a copy of the citations and checks the largest qualifying suffix.
     * O(n log n) time and O(n) extra space.
     */
    public static int hIndexSort(int[] citations) {
        int[] sortedCitations = citations.clone();
        Arrays.sort(sortedCitations);

        int n = sortedCitations.length;
        for (int i = 0; i < n; i++) {
            int papersAtLeastH = n - i;
            if (sortedCitations[i] >= papersAtLeastH) return papersAtLeastH;
        }
        return 0;
    }
}
