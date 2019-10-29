package array;

/**
 * A. Easy.
 * <p>
 * Tags: Array, Binary Search.
 * <p>
 * Given an array with first increasing and decreasing ordered elements. Return the index for the max element.
 * It's guaranteed that the peak element exists. And the peak element is not at 0 or {@code n - 1} index.
 * It's known that there is no duplicating elements.
 * <p>
 * Example: [1,2,3,1] -> 2, [1,2,0] -> 1, [1,2,3,4,5,2,1] -> 4
 * <p>
 * <b>Summary:</b>
 * <p>
 * <ul>
 * <li><b>Binary Search, O(logN) time, O(1) space.</b>
 * </ul>
 */
public class FindArrayPeak {
    public int peakIndex(Integer[] a) {
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid - 1] < a[mid]) {
                if (a[mid + 1] < a[mid]) return mid;
                else lo = mid + 1;
            } else hi = mid - 1;
        }
        throw new IllegalStateException("Did not find peak element.");
    }
}
