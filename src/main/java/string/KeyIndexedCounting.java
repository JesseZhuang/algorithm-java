package string;

/**
 * Sort an array a[] of N integers between 0 and R - 1. Used in LSD and MSD.
 * <p>
 * Complexity: ~ 11N + 4R array accesses and N + R space.
 */
public class KeyIndexedCounting {
    public static void sort(int[] ints, int R) {
        int N = ints.length;
        int[] count = new int[R + 1], aux = new int[N];
        // Count frequencies of each letter using key as index
        for (int i = 0; i < N; i++) count[ints[i] + 1]++;
        // Compute frequency accumulates which specify destinations
        for (int r = 0; r < R; r++) count[r + 1] += count[r];
        // Access accumulates using key as index to move items
        for (int i = 0; i < N; i++) aux[count[ints[i]]++] = ints[i];
        // Copy back into original array
        for (int i = 0; i < N; i++) ints[i] = aux[i];
    }
}
