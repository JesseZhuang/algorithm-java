package string;

public class LSDStringSort {

    /**
     * LSD String sort. Returns a permutation that gives the elements in the array in ascending order.
     * @param a String array to be sorted.
     * @param W number of characters for each String.
     * @return a permutation <tt>p[]</tt> such that <tt>a[p[0]]</tt>, <tt>a[p[1]]</tt>,
     * ..., <tt>a[p[N-1]]</tt> are in ascending order
     */
    public static int[] indexSort(String[] a, int W) {
        int N = a.length;
        int R = 256;   // extend ASCII alphabet size
        int[] aux = new int[N];
        int[] index = new int[N];
        for (int i = 0; i < N; i++) index[i] = i;

        for (int d = W - 1; d >= 0; d--) {
            // sort by key-indexed counting on dth character

            // compute frequency counts
            int[] count = new int[R + 1];
            for (int i = 0; i < N; i++)
                count[a[index[i]].charAt(d) + 1]++;

            // compute cumulates
            for (int r = 0; r < R; r++)
                count[r + 1] += count[r];

            // move data
            for (int i = 0; i < N; i++)
                aux[count[a[index[i]].charAt(d)]++] = index[i];

            // copy back
            for (int i = 0; i < N; i++)
                index[i] = aux[i];
        }
        return index;
    }
}
