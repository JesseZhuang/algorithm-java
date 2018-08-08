package list;

/**
 * LeetCode 278. Easy.
 * <p>
 * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version
 * of your product fails the quality check. Since each version is developed based on the previous version,
 * all the versions after a bad version are also bad.
 * <p>
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the
 * following ones to be bad.
 * <p>
 * You are given an API bool isBadVersion(version) which will return whether version is bad.
 * Implement a function to find the first bad version. You should minimize the number of calls to the API.
 * <p>
 * Example:
 * <p>
 * Given n = 5, and version = 4 is the first bad version.
 * <pre>
 * call isBadVersion(3) -> false
 * call isBadVersion(5) -> true
 * call isBadVersion(4) -> true
 * <pre>
 * Then 4 is the first bad version.
 * <p>
 * <b>Summary</b>:
 * <p>
 * <ul>
 * <li>binary search, O(log2_n) time, O(1) space.
 * </ul>
 */
public class FirstBadVersion {
    /*
     * The isBadVersion API is defined in the parent class VersionControl. boolean
     * isBadVersion(int version);
     */

    private final int firstBadVersion;

    public FirstBadVersion(int firstBadVersion) {
        this.firstBadVersion = firstBadVersion;
    }

    public int firstBadVersion3(int n) {
        int start = 1, end = n;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (!isBadVersion(mid)) start = mid + 1;
            else end = mid;
        }
        return start;
    }

    public int firstBadVersion(int n) {
        if (n == 1) return 1;
        long lo = 1; // overflow possibility
        long hi = n;
        int mid = (int) ((lo + hi) / 2);
        while (isBadVersion(mid) == isBadVersion(mid + 1)) {
            if (isBadVersion(mid)) hi = mid;
            else lo = mid;
            mid = (int) ((lo + hi) / 2);
            if (hi == lo) return mid;
        }
        return mid + 1;
    }

    // failed initial version
    @Deprecated
    public int firstBadVersion2(int n) {
        if (n == 1) return 1;
        long lo = 1, hi = n;
        long mid = (lo + hi) / 2;
        while (!isBadVersion((int) mid) && hi - lo > 1) {
            lo = mid;
            mid = (lo + hi) / 2;
        }
        while (isBadVersion((int) mid) && hi - lo > 1) {
            hi = mid;
            mid = (lo + hi) / 2;
            System.out.println(mid);
        }
        int res = (int) mid;
        return isBadVersion(res) ? res : res + 1;
    }

    private boolean isBadVersion(int version) {
        return version >= firstBadVersion;
    }

    public static void test(int answer, int n) {
        FirstBadVersion fbv = new FirstBadVersion(answer);
        System.out.println(fbv.firstBadVersion(n));
    }

    public static void main(String[] args) {
        test(1702766719, 2126753390);
        test(1,1);
        test(1, 2);
        test(5, 11);
        test(5, 5);
    }
}
