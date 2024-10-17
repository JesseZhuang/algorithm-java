package heap;

import util.Pair;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * LeetCode 1405, medium, tags: string, greedy, heap.
 * <p>
 * A string s is called happy if it satisfies the following conditions:
 * <p>
 * s only contains the letters 'a', 'b', and 'c'.
 * s does not contain any of "aaa", "bbb", or "ccc" as a substring.
 * s contains at most a occurrences of the letter 'a'.
 * s contains at most b occurrences of the letter 'b'.
 * s contains at most c occurrences of the letter 'c'.
 * Given three integers a, b, and c, return the longest possible happy string. If there are multiple longest happy
 * strings, return any of them. If there is no such string, return the empty string "".
 * <p>
 * A substring is a contiguous sequence of characters within a string.
 * <p>
 * Example 1:
 * <p>
 * Input: a = 1, b = 1, c = 7
 * Output: "ccaccbcc"
 * Explanation: "ccbccacc" would also be a correct answer.
 * Example 2:
 * <p>
 * Input: a = 7, b = 1, c = 0
 * Output: "aabaa"
 * Explanation: It is the only correct answer in this case.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= a, b, c <= 100
 * a + b + c > 0
 * <p>
 * Hint 1
 * Use a greedy approach.
 * Hint 2
 * Use the letter with the maximum current limit that can be added without breaking the condition.
 */
@SuppressWarnings("unused")
public class LongestHappyS {

    // @xxxxkav (python) intuition is to choose a char different from the previous one with the max remaining cnt
    // if cur char has only one left or it is not the max, take one. otherwise take two.
    // (a+b+c)lgk, k
    static class Solution1 {
        public String longestDiverseString(int a, int b, int c) {
            StringBuilder sb = new StringBuilder();
            Queue<Pair<Integer, Character>> pq = new PriorityQueue<>(
                    Comparator.<Pair<Integer, Character>>comparingInt(Pair::getKey).reversed());
            pq.add(new Pair<>(a, 'a'));
            pq.add(new Pair<>(b, 'b'));
            pq.add(new Pair<>(c, 'c'));
            var cur = pq.remove();
            while (cur.getKey() > 0) {
                int n = cur.getKey();
                char ch = cur.getValue();
                if (n == 1 || pq.peek().getKey() > n) {
                    sb.append(ch);
                    n--;
                } else {
                    sb.append(ch).append(ch);
                    n -= 2;
                }
                cur = pq.remove();
                pq.add(new Pair<>(n, ch));
            }
            return sb.toString();
        }
    }

    // greedy, a+b+c, 1.
    static class Solution2 {
        public String longestDiverseString(int a, int b, int c) {
            int cura = 0, curb = 0, curc = 0;
            // max total iterations possible is given by the sum of a, b and c.
            int total = a + b + c;
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < total; i++) {
                if ((a >= b && a >= c && cura < 2) || (a > 0 && (curb == 2 || curc == 2))) {
                    // If 'a' is maximum and its streak less than 2,
                    // or if streak of 'b' or 'c' is 2, then 'a' will be the next character.
                    res.append('a');
                    a--;
                    cura++;
                    curb = 0;
                    curc = 0;
                } else if ((b >= a && b >= c && curb < 2) || (b > 0 && (curc == 2 || cura == 2))) {
                    res.append('b');
                    b--;
                    curb++;
                    cura = 0;
                    curc = 0;
                } else if ((c >= a && c >= b && curc < 2) || (c > 0 && (cura == 2 || curb == 2))) {
                    res.append('c');
                    c--;
                    curc++;
                    cura = 0;
                    curb = 0;
                }
            }
            return res.toString();
        }
    }

    // For [11,5,3], as an example, we first generate 'aabaabaab', and our piles become [5,2,3].
    // At this time, c becomes the medium pile, and we generate '..aac' ([3,2,2]).
    // we add one more '..aa', c becomes the largest pile and we pull two characters from it '..cc' ([1,2,0]).
    // We add the rest '..bba', and the resulting string is 'aabaabaabaacaaccbba'.
    // a+b+c time and space. @votrubac
    static class Solution3 {
        String generate(int a, int b, int c, String aa, String bb, String cc) {
            if (a < b) return generate(b, a, c, bb, aa, cc); // a>=b
            if (b < c) return generate(a, c, b, aa, cc, bb); // b>=c
            if (b == 0) return aa.repeat(Math.min(2, a));
            int use_a = Math.min(2, a), use_b = a - use_a >= b ? 1 : 0;
            return aa.repeat(use_a) + bb.repeat(use_b) + generate(a - use_a, b - use_b, c, aa, bb, cc);
        }

        public String longestDiverseString(int a, int b, int c) {
            return generate(a, b, c, "a", "b", "c");
        }
    }


}
