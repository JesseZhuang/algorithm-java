package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * LeetCode 295, hard, tags: two pointers, design, sorting, heap, data stream.
 * <p>
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value,
 * and the median is the mean of the two middle values.
 * <p>
 * For example, for arr = [2,3,4], the median is 3.
 * For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 * Implement the MedianFinder class:
 * <p>
 * MedianFinder() initializes the MedianFinder object.
 * void addNum(int num) adds the integer num from the data stream to the data structure.
 * double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer
 * will be accepted.
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
 * [[], [1], [2], [], [3], []]
 * Output
 * [null, null, null, 1.5, null, 2.0]
 * <p>
 * Explanation
 * MedianFinder medianFinder = new MedianFinder();
 * medianFinder.addNum(1);    // arr = [1]
 * medianFinder.addNum(2);    // arr = [1, 2]
 * medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
 * medianFinder.addNum(3);    // arr[1, 2, 3]
 * medianFinder.findMedian(); // return 2.0
 * <p>
 * Constraints:
 * <p>
 * -10^5 <= num <= 10^5
 * There will be at least one element in the data structure before calling findMedian.
 * At most 5 * 104 calls will be made to addNum and findMedian.
 * <p>
 * Follow up:
 * <p>
 * If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
 * If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
 */
public class MedianFinder {

    PriorityQueue<Long> left; // peek max
    PriorityQueue<Long> right; // peek min
    boolean even;

    // 144ms, 71.7Mb. two heaps. O(N) space.
    public MedianFinder() {
        left = new PriorityQueue<>(Comparator.reverseOrder()); // Comparator.<Integer>natualOrder().reversed()
        right = new PriorityQueue<>();
        even = true;
    }

    public void addNum(int num) {// O(LgN) time
        if (even) {
            right.add((long) num);
            left.add(right.remove());
        } else {
            left.add((long) num);
            right.add(left.remove());
        }
        even = !even;
    }

    public double findMedian() { // O(1) time
        return even ? (left.peek() + right.peek()) / 2.0 : left.peek();
    }
}
