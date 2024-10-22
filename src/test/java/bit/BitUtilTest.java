package bit;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
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

    @Test
    void ilog2BitLength() {
        for (int i = 0; i < 10; i++) {
            int n = r.nextInt();
            if (n > 0)
                assertEquals(BigInteger.valueOf(n).bitLength(), BitUtil.ilog2(n) + 1);
        }
    }

    @Test
    void bitLength() {
        for (int i = 0; i < 10; i++) {
            int n = r.nextInt();
            if (n >= 0) // big integer bit length excluding sign bit
                assertEquals(BigInteger.valueOf(n).bitLength(), BitUtil.bitLength(n));
        }
        assertEquals(BigInteger.valueOf(0).bitLength(), BitUtil.bitLength(0));
        assertEquals(0xffff_fffe, -2);
        // big integer bit length Computes (ceil(log2(this < 0 ? -this : this+1))
        assertEquals(BigInteger.valueOf(0xffff_fffe).bitLength(), BitUtil.bitLength(1)); // log2(2)==1
        assertEquals(BigInteger.valueOf(0xffff_ffff).bitLength(), BitUtil.bitLength(0)); // log2(2)==1
    }

}