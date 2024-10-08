package dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestCommonSubsequenceTest {

    @Test
    void test() {
        LongestCommonSubsequence.Solution1 tbt = new LongestCommonSubsequence.Solution1();
        assertEquals(1, tbt.longestCommonSubsequence("bsbininm", "jmjkbkjkv"));
    }

}