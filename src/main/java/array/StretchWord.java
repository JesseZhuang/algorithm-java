package array;

/**
 * LintCode 1887, easy, tags: math, simulation.
 * <p>
 * Description
 * Given a string, you can get a new string by manipulating the same consecutive characters in the string.
 * You are only allowed to do the following:
 * Keep 1 or 2 characters of the same character whose continuous times are greater than or equal to 2,
 * and delete the rest.
 * <p>
 * You have to make sure that there are no more than two consecutive identical characters in the new string.
 * If the input string meets the requirements, you don't need to do anything with it.
 * <p>
 * The length of the world is between [1,35].
 * <p>
 * Example
 * Example 1:
 * <p>
 * Input:
 * S = "helllllooo"
 * Output:
 * 4
 * Explanation:
 * The answers are "hello", "helo","heloo","helloo"
 * Example 2:
 * <p>
 * Input:
 * S = "bbaa"
 * Output:
 * 4
 * Explanation:
 * The answers are "bbaa", "bba","baa","ba"
 */
public class StretchWord {
    // 230ms, 21.20Mb.
    public long stretchWord(String S) {
        int cnt = 1;
        long ans = 1;
        for (int i = 1; i < S.length(); i++) {
            if (S.charAt(i) != S.charAt(i - 1)) {
                if (cnt >= 2) {
                    ans *= 2;
                    cnt = 1;
                }
            } else cnt++;
        }
        if (cnt >= 2) ans *= 2;
        return ans;
    }

    // 239ms, 20.82Mb.
    public long stretchWord2(String S) {
        char c = '!';
        int p = 0;
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == S.charAt(i - 1) && S.charAt(i) != c) {
                c = S.charAt(i);
                p++;
            } else if (S.charAt(i) != S.charAt(i - 1) && c != '!') c = '!';
        }
        return (long) Math.pow(2, p);
    }
}
