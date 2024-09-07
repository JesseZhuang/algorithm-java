package math;

/**
 * LeetCode 38, easy, tags: string.
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
 * <p>
 * The count-and-say sequence is a sequence of digit strings defined by the recursive formula:
 * <p>
 * countAndSay(1) = "1"
 * countAndSay(n) is the run-length encoding of countAndSay(n - 1).
 * Run-length encoding (RLE) is a string compression method that works by replacing consecutive identical characters
 * (repeated 2 or more times) with the concatenation of the character and the number marking the count of the characters (length of the run). For example, to compress the string "3322251" we replace "33" with "23", replace "222" with "32", replace "5" with "15" and replace "1" with "11". Thus the compressed string becomes "23321511".
 * <p>
 * Given a positive integer n, return the nth element of the count-and-say sequence.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 4
 * <p>
 * Output: "1211"
 * <p>
 * Explanation:
 * <p>
 * countAndSay(1) = "1"
 * countAndSay(2) = RLE of "1" = "11"
 * countAndSay(3) = RLE of "11" = "21"
 * countAndSay(4) = RLE of "21" = "1211"
 * Example 2:
 * <p>
 * Input: n = 1
 * <p>
 * Output: "1"
 * <p>
 * Explanation:
 * <p>
 * This is the base case.
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 30
 * <p>
 * Hint 1
 * Create a helper function that maps an integer to pairs of its digits and their frequencies. For example,
 * if you call this function with "223314444411", then it maps it to an array of pairs
 * [[2,2], [3,2], [1,1], [4,5], [1, 2]].
 * Hint 2
 * Create another helper function that takes the array of pairs and creates a new integer. For example,
 * if you call this function with [[2,2], [3,2], [1,1], [4,5], [1, 2]], it should create
 * "22"+"23"+"11"+"54"+"21" = "2223115421".
 * Hint 3
 * Now, with the two helper functions, you can start with "1" and call the two functions alternatively n-1 times.
 * The answer is the last integer you will obtain.
 * <p>
 * Follow up: Could you solve it iteratively?
 * <b>Summary:</b>
 * <ul>
 * <li>check wikipedia look-and-say sequence, john conway, morris number
 * sequence.
 * <li>note: eclipse has a limit on console output. Which cuts off if the string
 * is too long. For n = 70, string length is 179691598, 234241726.
 * <li>the list function run out of heap space around 70 (-Xms 256m -Xmx 1024m).
 * </ul>
 */
public class CountSay {
    public static void main(String[] args) {
        CountSay cs = new CountSay();
        for (int i = 1; i < 30; i++) {
            String s = cs.countAndSayIterative(i);
            System.out.println(i + ": " + s);
            System.out.println("length: " + s.length());
        }
        // cs.countAndSayList(50); // may run out of heap
    }

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
            sb.append(count).append(num);
        }
        return sb.toString();
    }

    // solution 1, O(2^n) time (worst case string length double each loop), O(2^n) space returned as result.
    public String countAndSayIterative(int n) {
        if (n < 1) return null;
        String res = "1";
        while (--n > 0) {
            StringBuilder cur = new StringBuilder();
            for (int i = 0; i < res.length(); i++) { // note for loop has i++, otherwise inf loop
                int count = 1;
                while ((i + 1) < res.length() && res.charAt(i) == res.charAt(i + 1)) {
                    i++;
                    count++;
                }
                cur.append(count).append(res.charAt(i)); // this way do not need to convert
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
                cur.append(count).append(res.charAt(i));
            }
            res = cur.toString();
            System.out.println(n - nc + ": " + res.length());
        }
    }
}
