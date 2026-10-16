package array;

public final class RunningSumOf1dArray {
    private RunningSumOf1dArray() {
    }

    public static int[] runningSum(int[] nums) {
        for (int index = 1; index < nums.length; index++) {
            nums[index] += nums[index - 1];
        }
        return nums;
    }
}
