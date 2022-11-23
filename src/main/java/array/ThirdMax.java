package array;

import util.IntArrayUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Find third distinct maximum in the array.
 * <p>
 * kth distinct Maximum
 * <p>
 * O(N) for quick select, need to handle duplicates
 */
public class ThirdMax {
    /**
     * O(1) space, O(N) time.
     */
    public static int thirdMaxCandies(int[] candyNumbers) {
        int max = -1;
        int secondMax = -1;
        int thirdMax = -1;
        for (int i = 0; i < candyNumbers.length; i++) {
            if (candyNumbers[i] > max) {
                thirdMax = secondMax;
                secondMax = max;
                max = candyNumbers[i];
            } else if (candyNumbers[i] > secondMax && candyNumbers[i] < max) {
                thirdMax = secondMax;
                secondMax = candyNumbers[i];
            } else if (candyNumbers[i] > thirdMax && candyNumbers[i] < secondMax) {
                thirdMax = candyNumbers[i];
            }
        }
        if (thirdMax > -1) {
            return thirdMax;
        } else {
            return max;
        }
    }

    /**
     * O(N) time, O(N) space. Uses quick select.
     */
    public static int thirdMax2(int[] candyNumbers) {
        Set<Integer> set = new HashSet<>();
        int max = -1;
        for (int num : candyNumbers) {
            if(num > max) max = num;
            set.add(num);
        }
        candyNumbers = IntArrayUtil.unBoxIntegerArray(set.toArray(new Integer[0]));
        if(set.size() < 3) return max;
        else return QuickSelect.kthLargest(candyNumbers, 0, candyNumbers.length - 1, 3);
    }


    public static void main(String[] args) {
        int[][] candies = new int[][]{
                {3, 2, 1}, {1, 2}, {4, 3, 3, 2}, {}, {1, 2, 3}
                // 1,        2,      2,          -1,  1
        };
        for (int i = 0; i < candies.length; i++) {
            System.out.println(thirdMaxCandies(candies[i]));
            System.out.println("dedup with quick select");
            System.out.println(thirdMax2(candies[i]));
            System.out.println("done index: " + i);
        }

    }
}
