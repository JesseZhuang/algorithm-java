package array;

import java.util.Arrays;

/**
 * LintCode 1889, medium, tags: simulation; companies: apple.
 * Now give you two string intervals (in lexicographic order), please judge whether the two intervals can be merged.
 * The string intervals [a, b) includes all strings that begin with a.
 * For example:
 * <p>
 * The interval [a, b) and the interval [b, c] can be merged
 * The interval [a, b) and the interval [ab, c) can be merged
 * If two string intervals can be merged, return true, otherwise, return false.
 * Assumptions:
 * all characters are lowercase letters.
 * string average length n
 */
public class IntervalMerge {
    // 301 ms, 24.5 Mb. O(n) time, O(1) space.
    public boolean mergeJudge(String a, String b) {
        String[] ia = processInterval(a);
        String[] ib = processInterval(b);
        // ia[0] not necessarily < ib[0], e.g., (b,c), (a,b)
        return !(ib[0].compareTo(ia[1]) > 0 || ia[0].compareTo(ib[1]) > 0);
    }

    private static String[] processInterval(String s) {
        char[] cs = s.toCharArray();
        int commaIndex = -1;
        for (int i = 0; i < cs.length; i++) if (cs[i] == ',') commaIndex = i;
        int right = commaIndex;
        if (cs[0] == '(') {
            cs[commaIndex] = 'a';
            right = commaIndex + 1;
        }
        String[] res = new String[2];
        res[0] = new String(cs, 1, right - 1);
        int end = cs.length - 1;
        if (cs[end] == ']') cs[end++] = 'a'; // note modify if ], not )
        res[1] = new String(cs, commaIndex + 1, end - commaIndex - 1);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(processInterval("[a,b)"))); // a, b
        System.out.println(Arrays.toString(processInterval("[a,bc]"))); // a, bca
        System.out.println(Arrays.toString(processInterval("(a,ac]"))); // aa, aca
        System.out.println(Arrays.toString(processInterval("(ab,ae)"))); // aba, ae
    }
}
