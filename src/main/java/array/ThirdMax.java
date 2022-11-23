package array;

/**
 * Find third distinct maximum in the array.
 * <p>
 * kth distinct Maximum
 * <p>
 * O(N) for quick select, need to handle duplicates
 */
public class ThirdMax {
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
        if (max == -1) {
            return max;
        }
        if (thirdMax > -1) {
            return thirdMax;
        } else {
            return max;
        }
    }

    public static void main(String[] args) {
        int[][] candies = new int[][]{
                {3, 2, 1}, {1, 2}, {4, 3, 3, 2}, {}, {1, 2, 3}
                // 1,        2,      2,          -1,  1
        };
        for (int i = 0; i < candies.length; i++) {
            System.out.println(thirdMaxCandies(candies[i]));
        }

    }
}
