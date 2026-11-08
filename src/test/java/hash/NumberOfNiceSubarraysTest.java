package hash;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberOfNiceSubarraysTest {

    private void check(int expected, int[] nums, int k) {
        assertEquals(expected, NumberOfNiceSubarrays.numberOfSubarrays(nums, k));
        assertEquals(expected, NumberOfNiceSubarrays.numberOfSubarraysSliding(nums, k));
    }

    @Test void example1() {
        check(2, new int[]{1, 1, 2, 1, 1}, 3);
    }

    @Test void example2() {
        check(0, new int[]{2, 4, 6}, 1);
    }

    @Test void example3() {
        check(16, new int[]{2, 2, 2, 1, 2, 2, 1, 2, 2, 2}, 2);
    }

    @Test void allOdds() {
        check(3, new int[]{1, 3, 5, 7}, 2);
    }

    @Test void evenRunsAroundOneOdd() {
        check(9, new int[]{2, 4, 3, 6, 8}, 1);
    }

    @Test void evenRunsBetweenOdds() {
        check(12, new int[]{2, 1, 2, 2, 3, 2}, 1);
    }

    @Test void kEqualsTotalOddCount() {
        check(4, new int[]{2, 1, 2, 3, 2}, 2);
    }

    @Test void kGreaterThanTotalOddCount() {
        check(0, new int[]{2, 1, 4, 6}, 2);
    }

    @Test void kEqualsLength() {
        check(1, new int[]{1, 3, 5}, 3);
    }

    @Test void singleOdd() {
        check(1, new int[]{99999}, 1);
    }

    @Test void largeEvenRunsAroundOneOdd() {
        int[] nums = new int[49999];
        Arrays.fill(nums, 100000);
        nums[24999] = 99999;
        check(625000000, nums, 1);
    }
}
