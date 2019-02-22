package dp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import util.IntegerArrayConverter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.IntArrayUtil.unBoxIntegerArray;

class HouseRobberTest {
    private static HouseRobber toBeTested;

    @BeforeAll
    static void setup() {
        toBeTested = new HouseRobber();
    }

    @ParameterizedTest(name = "rob {0} best profit {1}")
    @CsvFileSource(resources = {"/HouseRobber.csv"}, delimiter = ' ', numLinesToSkip = 2)
    void testRob(@ConvertWith(IntegerArrayConverter.class) Integer[] nums, int count) {
        assertEquals(toBeTested.rob(unBoxIntegerArray(nums)), count);
    }
}
