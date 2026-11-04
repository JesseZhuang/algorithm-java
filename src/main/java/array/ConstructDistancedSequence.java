package array;

/**
 * LeetCode 1718, medium, tags: array, backtracking.
 * For 1 <= n <= 20, construct the lexicographically largest sequence of length 2*n-1.
 * Use 1 once and every value k from 2 to n twice, with its two indices exactly k apart.
 */
public final class ConstructDistancedSequence {
    private ConstructDistancedSequence() {}

    public static int[] constructDistancedSequence(int n) {
        int[] sequence = new int[2 * n - 1];
        backtrack(sequence, new boolean[n + 1], 0);
        return sequence;
    }

    private static boolean backtrack(int[] sequence, boolean[] used, int index) {
        while (index < sequence.length && sequence[index] != 0) index++;
        if (index == sequence.length) return true;

        // Conservative worst case: O(n * n!) time (O(n) work per search node), O(n) space.
        // Fill the leftmost empty slot in descending order: the first completion is largest.
        for (int value = used.length - 1; value >= 1; value--) {
            if (used[value]) continue;
            int secondIndex = value == 1 ? index : index + value;
            if (secondIndex >= sequence.length || sequence[secondIndex] != 0) continue;

            sequence[index] = value;
            sequence[secondIndex] = value;
            used[value] = true;
            if (backtrack(sequence, used, index + 1)) return true;
            used[value] = false;
            sequence[index] = 0;
            sequence[secondIndex] = 0;
        }
        return false;
    }
}
