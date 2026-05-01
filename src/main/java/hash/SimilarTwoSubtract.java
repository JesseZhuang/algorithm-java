package hash;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1 similar (Salesforce variant), medium, tags: array, hash table.
 * <p>
 * Given an array {@code A} and a target {@code T}, return the number of ordered
 * pairs {@code (i, j)} where {@code A[i] - A[j] == T}. Duplicates in the array
 * are counted with multiplicity.
 * <p>
 * Approach: count how many times each value occurs, then for each unique value
 * {@code v} look for {@code v + T} and add {@code cnt[v] * cnt[v + T]}. We only
 * scan toward {@code v + T} (never {@code v - T}) so each pair is counted once.
 */
public final class SimilarTwoSubtract {
    private SimilarTwoSubtract() {}

    /**
     * Count pairs whose difference equals {@code target}.
     * Time O(n), Space O(n) where n = A.length.
     */
    public static int twoSubtract(int[] A, int target) {
        if (A == null || A.length == 0) return 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int v : A) cnt.merge(v, 1, Integer::sum); // O(n)
        int res = 0;
        for (Map.Entry<Integer, Integer> e : cnt.entrySet()) { // O(unique) ≤ O(n)
            int look = e.getKey() + target;
            Integer lookCnt = cnt.get(look);
            if (lookCnt != null) res += e.getValue() * lookCnt;
        }
        return res;
    }
}
