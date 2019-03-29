package string;

/**
 * Princeton class.
 * <p>
 * Sort 1 million 32 bit integers.
 */
public class Sort1M32BitIntegers {

    public static void sort(int[] ints) {
        String[] strings = new String[ints.length];
        for(int i = 0; i < ints.length; i++) strings[i] = intToString(ints[i]);
        int[] index = LSDStringSort.indexSort(strings, 4);
        int[] aux = new int[ints.length];
        for(int i = 0; i < ints.length; i++) aux[i] = ints[index[i]];
        for(int i = 0; i < ints.length; i++) ints[i] = aux[i];
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

    private static char intToChar(int i, int mask) {
        return (char) (i & mask);
    }

    public static void main(String[] args) {

    }

}
