package array;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ProductExceptSelfTest {
    ProductExceptSelf.Solution tbt;
    ProductExceptSelf.Solution2 tbt2;

    @BeforeEach
    void setUp() {
        tbt = new ProductExceptSelf.Solution();
        tbt2 = new ProductExceptSelf.Solution2();
    }

    static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3, 4}, new int[]{24, 12, 8, 6}),
                Arguments.of(new int[]{-1, 1, 0, -3, 3}, new int[]{0, 0, 9, 0, 0}),
                Arguments.of(new int[]{0, 0}, new int[]{0, 0}),
                Arguments.of(new int[]{1, 0}, new int[]{0, 1}),
                Arguments.of(new int[]{2, 3}, new int[]{3, 2}),
                Arguments.of(new int[]{5}, new int[]{1}),
                Arguments.of(new int[]{1, 2, 3, 4, 5}, new int[]{120, 60, 40, 30, 24})
        );
    }

    @ParameterizedTest
    @MethodSource("cases")
    void test(int[] nums, int[] exp) {
        assertArrayEquals(exp, tbt.productExceptSelf(nums));
        assertArrayEquals(exp, tbt2.productExceptSelf(nums));
    }
}
