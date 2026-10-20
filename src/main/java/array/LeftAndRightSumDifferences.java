package array;

public final class LeftAndRightSumDifferences {
    private LeftAndRightSumDifferences() {
    }

    public static int[] leftRightDifference(int[] nums) {
        int right = 0;
        for (int value : nums) {
            right += value;
        }

        int left = 0;
        int[] result = new int[nums.length];
        for (int index = 0; index < nums.length; index++) {
            int value = nums[index];
            right -= value;
            result[index] = Math.abs(left - right);
            left += value;
        }
        return result;
    }
}
