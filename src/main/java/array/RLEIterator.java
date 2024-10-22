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
public class RLEIterator {
    int index;
    int[] A;

    public RLEIterator(int[] A) {
        this.A = A;
        index = 0;
    }

    public int next(int n) {
        while (index < A.length && n > A[index]) {
            n = n - A[index];
            index += 2;
        }

        if (index >= A.length) {
            return -1;
        }

        A[index] = A[index] - n;
        return A[index + 1];
    }
}
