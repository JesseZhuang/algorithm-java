package dp;

import junit.converter.IntegerArrayConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import util.IntArrayUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxConsecutiveOnesIITest {

    MaxConsecutiveOnesII.Solution2 tbt2;
    MaxConsecutiveOnesII.Solution tbt1;

    @BeforeEach
    void setUp() {
        tbt2 = new MaxConsecutiveOnesII.Solution2();
        tbt1 = new MaxConsecutiveOnesII.Solution();
    }

    @ParameterizedTest
    @CsvSource({
            "'1,0,1,1,0,0,0,0', 4",
            "'1,0,1,1,0,1', 4",
            "'1,0,1,1,0', 4",
            "'1,0,1,0,1', 3",
            "'1,1,1', 3"
    })
    void test(@ConvertWith(IntegerArrayConverter.class) Integer[] nums, int expected) {
        int[] numsP = IntArrayUtil.unBoxIntegerArray(nums);
        assertEquals(expected, tbt2.findMaxConsecutiveOnes(numsP));
        assertEquals(expected, tbt1.findMaxConsecutiveOnes(numsP));
        assertEquals(expected, tbt2.followUp(numsP));
    }
}
