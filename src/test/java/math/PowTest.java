package math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PowTest {

    private final Pow solver = new Pow();

    private void assertPow(double expected, double x, int n) {
        assertEquals(expected, solver.myPow(x, n), 1e-5);
        assertEquals(expected, solver.myPowIter(x, n), 1e-5);
    }

    @Test
    void example1() {
        assertPow(1024.0, 2.0, 10);
    }

    @Test
    void example2() {
        assertPow(9.261, 2.1, 3);
    }

    @Test
    void example3() {
        assertPow(0.25, 2.0, -2);
    }

    @Test
    void zeroExponent() {
        assertPow(1.0, 2.0, 0);
    }

    @Test
    void negativeBaseEven() {
        assertPow(16.0, -2.0, 4);
    }

    @Test
    void negativeBaseOdd() {
        assertPow(-8.0, -2.0, 3);
    }

    @Test
    void fractionalBase() {
        assertPow(0.125, 0.5, 3);
    }

    @Test
    void oneBaseMinInt() {
        assertPow(1.0, 1.0, -2147483648);
    }

    @Test
    void oneBaseMaxInt() {
        assertPow(1.0, 1.0, 2147483647);
    }
}
