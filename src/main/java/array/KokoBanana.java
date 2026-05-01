package array;

/**
 * LeetCode 875, LintCode 1492, medium, tags: array, binary search.
 * <p>
 * O(n log n) time, O(1) space.
 */
public final class KokoBanana {
    private KokoBanana() {}

    public static int minEatingSpeed(int[] piles, int h) {
        int l = 1, r = 0;
        for (int p : piles) r = Math.max(r, p);
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (feasible(piles, h, mid)) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    static boolean feasible(int[] piles, int h, int speed) {
        int sum = 0;
        for (int p : piles) sum += (p - 1) / speed + 1;
        return sum <= h;
    }
}
