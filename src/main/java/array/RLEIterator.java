package array;

/**
 * LeetCode 900, medium, tags: array, design, counting, iterator.
 * <p>
 * We can use run-length encoding (i.e., RLE) to encode a sequence of integers. In a run-length encoded array of even
 * length encoding (0-indexed), for all even i, encoding[i] tells us the number of times that the non-negative integer
 * value encoding[i + 1] is repeated in the sequence.
 * <p>
 * For example, the sequence arr = [8,8,8,5,5] can be encoded to be encoding = [3,8,2,5]. encoding = [3,8,0,9,2,5] and
 * encoding = [2,8,1,8,2,5] are also valid RLE of arr.
 * Given a run-length encoded array, design an iterator that iterates through it.
 * <p>
 * Implement the RLEIterator class:
 * <p>
 * RLEIterator(int[] encoded) Initializes the object with the encoded array encoded.
 * int next(int n) Exhausts the next n elements and returns the last element exhausted in this way. If there is no
 * element left to exhaust, return -1 instead.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["RLEIterator", "next", "next", "next", "next"]
 * [[[3, 8, 0, 9, 2, 5]], [2], [1], [1], [2]]
 * Output
 * [null, 8, 8, 5, -1]
 * <p>
 * Explanation
 * RLEIterator rLEIterator = new RLEIterator([3, 8, 0, 9, 2, 5]); // This maps to the sequence [8,8,8,5,5].
 * rLEIterator.next(2); // exhausts 2 terms of the sequence, returning 8. The remaining sequence is now [8, 5, 5].
 * rLEIterator.next(1); // exhausts 1 term of the sequence, returning 8. The remaining sequence is now [5, 5].
 * rLEIterator.next(1); // exhausts 1 term of the sequence, returning 5. The remaining sequence is now [5].
 * rLEIterator.next(2); // exhausts 2 terms, returning -1. This is because the first term exhausted was 5,
 * but the second term did not exist. Since the last term exhausted does not exist, we return -1.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= encoding.length <= 1000
 * encoding.length is even.
 * 0 <= encoding[i] <= 10^9
 * 1 <= n <= 10^9
 * At most 1000 calls will be made to next.
 */
@SuppressWarnings("unused")
// O(1) init, O(n) next (O(1) amortized); O(1) space does not count input
public class RLEIterator {
    int idx;
    int[] encoding;

    public RLEIterator(int[] encoding) {
        this.encoding = encoding;
        idx = 0;
    }

    public int next(int n) {
        // current val will be exhausted
        while (idx < encoding.length && n > encoding[idx]) {
            n -= encoding[idx];
            idx += 2;
        }
        // all data exhausted
        if (idx >= encoding.length) return -1;
        // exhausting
        encoding[idx] = encoding[idx] - n;
        return encoding[idx + 1];
    }
}

@SuppressWarnings("unused")
class Solution2 {
    // n init, lgn next; n space
    static class RLEIterator {
        long[] sumCnt; // accumulative sum count
        int[] num; // values
        long cur = 0; // overall how many will be exhausted
        int low = 0;  // last state of cur, already exhausted

        public RLEIterator(int[] encoding) { // {3,8,2,5}
            sumCnt = new long[encoding.length / 2]; // {3,5}
            num = new int[encoding.length / 2];  // {8,5}
            for (int i = 0; i < encoding.length; i += 2) {
                int id = i / 2;
                sumCnt[id] = encoding[i];
                if (i > 1) sumCnt[id] += sumCnt[id - 1];
                num[id] = encoding[i + 1];
            }
        }

        public int next(int n) {
            if (sumCnt.length == 0) return -1;
            cur += n;
            if (cur > sumCnt[sumCnt.length - 1]) return -1;
            int l = low, h = sumCnt.length - 1;
            while (l < h) { // bisect left
                int mid = l + (h - l) / 2;
                if (sumCnt[mid] >= cur) h = mid;
                else l = mid + 1;
            }
            low = l;
            return num[l];
        }
    }
}
