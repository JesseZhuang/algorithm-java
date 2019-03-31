package string;

/**
 * Princeton class.
 * <p>
 * Sort 1 million 32 bit integers.
 */
public class Sort1M32BitIntegers {

    // 32 bit converts to 4 8-bit chars, one string with length 4
    private static final int WIDTH = 4;

    public static void sort(int[] ints) {
        String[] strings = new String[ints.length];
        for(int i = 0; i < ints.length; i++) strings[i] = intToString(ints[i]);
        int[] index = LSDStringSort.indexSort(strings, WIDTH);
        int[] aux = new int[ints.length];
        for(int i = 0; i < ints.length; i++) aux[i] = ints[index[i]];
        for(int i = 0; i < ints.length; i++) ints[i] = aux[i];
    }

    public static void sortDirectly(int[] ints) {
        int N = ints.length;
        int R = 256;   // extend ASCII alphabet size
        int[] aux = new int[N];
        String[] a = intsToStrings(ints);
        for (int d = WIDTH - 1; d >= 0; d--) {
            // sort by key-indexed counting on dth character
            // compute frequency counts
            int[] count = new int[R + 1];
            for (int i = 0; i < N; i++)  count[a[i].charAt(d) + 1]++;
            // compute cumulates
            for (int r = 0; r < R; r++) count[r + 1] += count[r];
            // move data
            for (int i = 0; i < N; i++) aux[count[a[i].charAt(d)]++] = ints[i];
            // copy back
            for (int i = 0; i < N; i++) ints[i] = aux[i];
        }
    }

    /**
     * Breaks a 32 bit integer to 4 parts and convert that into a 4-character String. For example,
     * 0x41414141 to "AAAA". 0x41 to character 'A'.
     *
     * @return the String converted from the 32 bit integer.
     */
    private static String intToString(int int32) {
        char[] chars = new char[4];
        for (int i = 0; i < 4; i++) chars[i] = intToChar(int32 >> (8 * (i + 1)), 0xFF);
        return new String(chars);
    }

    private static String[] intsToStrings(int[] ints) {
        String[] result = new String[ints.length];
        for(int i = 0; i < ints.length; i++) result[i] = intToString(ints[i]);
        return result;
    }

    private static char intToChar(int i, int mask) {
        return (char) (i & mask);
    }

}
