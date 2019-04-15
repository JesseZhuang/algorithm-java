package math;

/**
 * LeetCode 38. Easy.
 * <p>
 * The count-and-say sequence is the sequence of integers beginning as follows: 1, 11, 21, 1211, 111221, ...
 * A005150 in OEIS (Online Encyclopedia of Integer Sequences). Idea is similar to run-length encoding. If we start
 * with digit d, d, 1d, 111d, 311d, 13211d, 111312211d, 31131122211d, …
 * Conway sequence for (d = 3) A006715 in the OEIS). (for d = 2, see OEIS: A006751).
 * <p>
 * 1 is read off as "one 1" or 11. 11 is read off as "two 1s" or 21. 21 is read
 * off as "one 2, then one 1" or 1211. Given an integer n, generate the nth sequence.
 * <p>
 * Note: The sequence of integers will be represented as a string.
 * <p>
 * Questions:
 * <ul>
 * <li>Growth: the sequence grows indefinitely. Except 22, 22, 22, 22, … (sequence A010861 in the OEIS).</li>
 * <li>What's the max single digit? No digits other than 1, 2, and 3 appear in the sequence, unless the seed
 * number contains such a digit or a run of more than three of the same digit.</li>
 * </ul>
 * <p>
 * <b>Summary:</b>
 * <ul>
 * <li>recursive or iterative. O(N) time, O(1) space.
 * <li>check wikipedia look-and-say sequence, john conway, morris number
 * sequence.
 * <li>note: eclipse has a limit on console output. Which cuts off if the string
 * is too long. For n = 70, string length is 179691598, 234241726.
 * <li>the list function run out of heap space around 70 (-Xms 256m -Xmx 1024m).
 * </ul>
 */
public class CountSay {
    public String countAndSayRecursive(int n) {
        if (n < 1) return null;
        if (n == 1) return "1";
        String s = countAndSayRecursive(n - 1);
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            char num = s.charAt(i++);
            int count = 1;
            while (i < s.length() && s.charAt(i) == num) {
                count++;
                i++;
            }
            sb.append(String.valueOf(count)).append(num);
        }
        return sb.toString();
    }

    public String countAndSayIterative(int n) {
        if (n < 1) return null;
        String res = "1";
        while (--n > 0) {
            StringBuilder cur = new StringBuilder();
            for (int i = 0; i < res.length(); i++) {
                int count = 1;
                while ((i + 1) < res.length()
                        && res.charAt(i) == res.charAt(i + 1)) {
                    i++;
                    count++;
                }
                cur.append(String.valueOf(count)).append(res.charAt(i));
            }
            res = cur.toString();
        }
        return res;
    }

    public void countAndSayList(int n) {
        if (n < 1) return;
        String res = "1";
        int nc = n;
        while (--nc > 0) {
            StringBuilder cur = new StringBuilder();
            for (int i = 0; i < res.length(); i++) {
                int count = 1;
                while ((i + 1) < res.length()
                        && res.charAt(i) == res.charAt(i + 1)) {
                    i++;
                    count++;
                }
                cur.append(String.valueOf(count)).append(res.charAt(i));
            }
            res = cur.toString();
            System.out.println(n - nc + ": " + res.length());
        }
    }

    public static void main(String[] args) {
        CountSay cs = new CountSay();
        for (int i = 1; i < 10; i++) {
            String s = cs.countAndSayIterative(i);
            System.out.println(i + ": " + s);
        }
        cs.countAndSayList(100);
    }
}
