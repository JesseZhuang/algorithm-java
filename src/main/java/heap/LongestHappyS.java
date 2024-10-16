package heap;

import util.Pair;

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
    static class Solution1 {
        String generate(int a, int b, int c, String aa, String bb, String cc) {
            if (a < b)
                return generate(b, a, c, bb, aa, cc);
            if (b < c)
                return generate(a, c, b, aa, cc, bb);
            if (b == 0)
                return aa.repeat(Math.min(2, a));
            int use_a = Math.min(2, a), use_b = a - use_a >= b ? 1 : 0;
            return aa.repeat(use_a) + bb.repeat(use_b) +
                    generate(a - use_a, b - use_b, c, aa, bb, cc);
        }

        public String longestDiverseString(int a, int b, int c) {
            return generate(a, b, c, "a", "b", "c");
        }
    }

    static class Solution2 {
        public String longestDiverseString(int a, int b, int c) {
            StringBuilder sb = new StringBuilder();
            Queue<Pair<Character, Integer>> pq = new PriorityQueue<>((c1, c2) -> (c2.getValue() - c1.getValue()));

            if (a > 0)
                pq.add(new Pair<>('a', a));
            if (b > 0)
                pq.add(new Pair<>('b', b));
            if (c > 0)
                pq.add(new Pair<Character, Integer>('c', c));

            while (!pq.isEmpty()) {
                Pair<Character, Integer> first = pq.poll();
                if (!sb.isEmpty() && sb.charAt(sb.length() - 1) == first.getKey()) {
                    if (pq.isEmpty())
                        return sb.toString();
                    Pair<Character, Integer> second = pq.poll();
                    sb.append(second.getKey());
                    if (second.getValue() - 1 > 0) {
                        pq.add(new Pair<>(second.getKey(), second.getValue() - 1));
                    }
                    pq.add(first);
                } else {
                    int limit = Math.min(2, first.getValue());
                    int counter = 0;
                    while (counter++ < limit) {
                        sb.append(first.getKey());
                    }
                    if (first.getValue() - limit > 0)
                        pq.add(new Pair<>(first.getKey(), first.getValue() - limit));
                }
            }
            return sb.toString();
        }
    }
}
