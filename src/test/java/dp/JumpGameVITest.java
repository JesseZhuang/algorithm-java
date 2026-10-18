package dp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JumpGameVITest {

    static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of("sample with skipped negatives", new int[]{1, -1, -2, 4, -7, 3}, 2, 7),
                Arguments.of("sample with long jump", new int[]{10, -5, -2, 4, 0, 3}, 3, 17),
                Arguments.of("all negative", new int[]{-1, -2, -3, -4}, 2, -7),
                Arguments.of("unavoidable negative", new int[]{1, -5, -20, 4}, 2, 0),
                Arguments.of("only adjacent jumps", new int[]{7, -4, 3, -2}, 1, 4),
                Arguments.of("single negative element", new int[]{-10_000}, 100_000, -10_000),
                Arguments.of("equal scores", new int[]{5, 0, 0, 0, 5}, 2, 10),
                Arguments.of("zero plateau", new int[]{0, 0, 0, 0, 0}, 2, 0),
                Arguments.of("expired high score", new int[]{100, -1000, -1000, 1}, 2, -899),
                Arguments.of("window boundary is inclusive", new int[]{10, -5, -10, 10}, 2, 15),
                Arguments.of("jump longer than array", new int[]{10, -100, -100, 7}, 100_000, 17)
        );
    }

    @ParameterizedTest(name = "deque: {0}")
    @MethodSource("cases")
    void dequeFindsMaximumScore(String caseName, int[] nums, int k, int expected) {
        assertEquals(expected, JumpGameVI.maxResultDeque(nums, k));
    }

    @ParameterizedTest(name = "priority queue: {0}")
    @MethodSource("cases")
    void priorityQueueFindsMaximumScore(String caseName, int[] nums, int k, int expected) {
        assertEquals(expected, JumpGameVI.maxResultPriorityQueue(nums, k));
    }

    @Test
    void bothMethodsHandleLargePositiveAndNegativeScores() {
        int[] positive = new int[100_000];
        int[] negative = new int[100_000];
        Arrays.fill(positive, 10_000);
        Arrays.fill(negative, -10_000);

        assertBoth(positive, 1, 1_000_000_000);
        assertBoth(positive, 100_000, 1_000_000_000);
        assertBoth(negative, 1, -1_000_000_000);
        assertBoth(negative, 100_000, -20_000);
    }

    @Test
    void bothMethodsMatchIndependentQuadraticDpOnSmallArrays() {
        Random random = new Random(1696);
        for (int length = 1; length <= 12; length++) {
            for (int trial = 0; trial < 25; trial++) {
                int[] nums = new int[length];
                for (int index = 0; index < length; index++) {
                    nums[index] = random.nextInt(21) - 10;
                }
                int k = 1 + random.nextInt(length + 3);
                assertBoth(nums, k, quadraticMaxResult(nums, k));
            }
        }
    }

    private static void assertBoth(int[] nums, int k, int expected) {
        assertEquals(expected, JumpGameVI.maxResultDeque(nums, k));
        assertEquals(expected, JumpGameVI.maxResultPriorityQueue(nums, k));
    }

    private static int quadraticMaxResult(int[] nums, int k) {
        int[] best = new int[nums.length];
        best[0] = nums[0];
        for (int index = 1; index < nums.length; index++) {
            best[index] = Integer.MIN_VALUE;
            for (int previous = Math.max(0, index - k); previous < index; previous++) {
                best[index] = Math.max(best[index], best[previous] + nums[index]);
            }
        }
        return best[nums.length - 1];
    }
}
