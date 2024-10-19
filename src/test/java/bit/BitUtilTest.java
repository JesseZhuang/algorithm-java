package bit;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BitUtilTest {

    Random r = new Random();

    @Test
    void testNegate() {
        for (int i = 0; i < 10; i++) {
            int n = r.nextInt();
            assertEquals(-(n + 1), BitUtil.negate(n));
        }
    }
}