package hash;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class FindUniqueBinaryStringTest {

    private final FindUniqueBinaryString solver = new FindUniqueBinaryString();

    private void assertValid(String[] nums) {
        String result = solver.findDifferentBinaryString(nums);
        assertEquals(nums.length, result.length());
        for (String s : nums) {
            assertNotEquals(s, result);
        }

        String resultSet = solver.findDifferentBinaryStringSet(nums);
        assertEquals(nums.length, resultSet.length());
        for (String s : nums) {
            assertNotEquals(s, resultSet);
        }
    }

    @Test
    void example1() {
        String[] nums = {"01", "10"};
        assertValid(nums);
    }

    @Test
    void example2() {
        String[] nums = {"00", "01"};
        assertValid(nums);
    }

    @Test
    void example3() {
        String[] nums = {"111", "011", "001"};
        assertValid(nums);
    }

    @Test
    void edgeCaseSingleZero() {
        String[] nums = {"0"};
        assertValid(nums);
    }

    @Test
    void edgeCaseSingleOne() {
        String[] nums = {"1"};
        assertValid(nums);
    }
}
