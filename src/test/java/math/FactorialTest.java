package math;

import com.google.common.math.BigIntegerMath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FactorialTest {

    Factorial tbt;

    @BeforeEach
    void setUp() {
        tbt = new Factorial();
    }

    @Test
    void testFactorial() {
        int[] cases = {3, 2, 4, 5, 1, 9, 7, 10, 8, 11, 12, 6};
        for (int i : cases) assertEquals(BigIntegerMath.factorial(i).intValue(), tbt.factorialInt(i));
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            int n = random.nextInt(50);
            BigInteger res = tbt.factorialBig(n);
            assertEquals(BigIntegerMath.factorial(n), res);
            System.out.println(n + "! = " + res);
        }
    }
}