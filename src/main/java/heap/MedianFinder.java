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
 * At most 5 * 10^4 calls will be made to addNum and findMedian.
 * <p>
 * Follow up:
 * <p>
 * If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
 * <p>
 * We can maintain an integer array of length 100 to store the count of each number along with a total count.
 * Then, we can iterate over the array to find the middle value to get our median.
 * Time and space complexity would be O(100) = O(1).
 * <p>
 * If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
 * <p>
 * but couldn't we just keep a count of how many numbers are above 100 and how many numbers are below 0,
 * since these numbers could never get to be the median and are therefore not important to keep?
 */
public class MedianFinder {

    PriorityQueue<Integer> left; // peek max heap
    PriorityQueue<Integer> right; // peek min heap, size difference 0 or 1
    boolean odd;

    // 144ms, 71.7Mb. two heaps. O(N) space.
    public MedianFinder() {
        // Comparator.<Integer>natualOrder().reversed(), do not forget left is max heap
        left = new PriorityQueue<>(Comparator.reverseOrder());
        right = new PriorityQueue<>();
    }

    // does not matter add to left or right first, just need to shift after add
    public void addNum(int num) {// O(LgN) time
        if (odd) {
            right.add(num);
            left.add(right.remove());
        } else {
            left.add(num);
            right.add(left.remove());
        }
        odd = !odd;
    }

    // need to consider when odd, which q has more elements
    public double findMedian() { // O(1) time
        return odd ? (right.peek() + right.peek()) / 2.0 : left.peek();
    }
}
