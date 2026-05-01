package math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountGoodSubsequencesTest {

    @Test void example1() {
        assertEquals(11, CountGoodSubsequences.countGoodSubsequences("aabb"));
    }

    @Test void example2() {
        assertEquals(12, CountGoodSubsequences.countGoodSubsequences("leet"));
    }

    @Test void example3() {
        assertEquals(15, CountGoodSubsequences.countGoodSubsequences("abcd"));
    }
}
