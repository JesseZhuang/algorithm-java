package array;

/**
 * LeetCode 1013, easy, tags: array, greedy.
 */
public final class PartitionArrayIntoThreePartsWithEqualSum {
    private PartitionArrayIntoThreePartsWithEqualSum() {}

    // O(n) time and O(1) extra space.
    public static boolean canThreePartsEqualSum(int[] arr) {
        int total = 0;
        for (int value : arr) {
            total += value;
        }
        if (total % 3 != 0) {
            return false;
        }

        int target = total / 3;
        int sum = 0;
        int parts = 0;
        // Leave at least one element for the third part.
        for (int i = 0; i < arr.length - 1; i++) {
            sum += arr[i];
            if (sum == target) {
                parts++;
                if (parts == 2) {
                    return true;
                }
                sum = 0;
            }
        }
        return false;
    }
}
