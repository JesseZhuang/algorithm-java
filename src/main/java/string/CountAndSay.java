package string;

/**
 * <a href="https://leetcode.com/problems/count-and-say/">LeetCode 38</a>, medium,
 * tags: math, string, simulation.
 * <p>
 * Build the run-length encoding sequence iteratively from the base case "1".
 */
public final class CountAndSay {
    private CountAndSay() {}

    /**
     * Iterative RLE building. Time O(1.3^n) (Conway's constant), Space O(1.3^n).
     */
    public static String countAndSay(int n) {
        StringBuilder res = new StringBuilder("1");
        for (int round = 1; round < n; round++) { // build (round+1)-th term from round-th
            StringBuilder next = new StringBuilder();
            int i = 0, m = res.length();
            while (i < m) {                       // O(|res|)
                int count = 1;
                while (i + 1 < m && res.charAt(i) == res.charAt(i + 1)) {
                    count++;
                    i++;
                }
                next.append(count).append(res.charAt(i));
                i++;
            }
            res = next;
        }
        return res.toString();
    }
}
