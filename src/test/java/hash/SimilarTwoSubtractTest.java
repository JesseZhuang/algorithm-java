package hash;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimilarTwoSubtractTest {

    @Test void noPair() {
        assertEquals(0, SimilarTwoSubtract.twoSubtract(new int[]{1, 2}, 3));
    }

    @Test void singlePair() {
        assertEquals(1, SimilarTwoSubtract.twoSubtract(new int[]{1, 2}, 1)); // (2,1)
    }

    @Test void zeroAdds() {
        assertEquals(2, SimilarTwoSubtract.twoSubtract(new int[]{1, 2, 0}, 1)); // (2,1),(1,0)
    }

    @Test void duplicatesMultiplyCounts() {
        assertEquals(6, SimilarTwoSubtract.twoSubtract(new int[]{1, 2, 1, 2, 0}, 1));
    }

    @Test void emptyArray() {
        assertEquals(0, SimilarTwoSubtract.twoSubtract(new int[]{}, 1));
    }

    @Test void singleElementTargetZero() {
        // cnt={5:1}, v=5, look=5, res += 1*1 = 1 (self-pair when T=0).
        assertEquals(1, SimilarTwoSubtract.twoSubtract(new int[]{5}, 0));
    }

    @Test void singleElementNonZeroTarget() {
        assertEquals(0, SimilarTwoSubtract.twoSubtract(new int[]{5}, 3));
    }

    @Test void nullArray() {
        assertEquals(0, SimilarTwoSubtract.twoSubtract(null, 1));
    }

    @Test void negativeValues() {
        // cnt = {-1:1, -3:1, -2:1}. T=1: look -1+1=0(no), -3+1=-2(yes, 1*1), -2+1=-1(yes, 1*1) = 2.
        assertEquals(2, SimilarTwoSubtract.twoSubtract(new int[]{-1, -3, -2}, 1));
    }

    @Test void negativeTarget() {
        // T=-1: same array as singlePair but reversed direction. cnt={1:1,2:1}.
        // v=1: look=0(no). v=2: look=1(yes, 1*1)=1.
        assertEquals(1, SimilarTwoSubtract.twoSubtract(new int[]{1, 2}, -1));
    }

    @Test void allSameValueTargetZero() {
        // cnt={5:4}. v=5: look=5, res += 4*4 = 16.
        assertEquals(16, SimilarTwoSubtract.twoSubtract(new int[]{5, 5, 5, 5}, 0));
    }

    @Test void multipleDistinctPairs() {
        // [10, 7, 3, 0, 13, 17] with T=3. cnt has 1 each of 10,7,3,0,13,17.
        // v=10:look=13(1*1=1). v=7:look=10(1*1=1). v=3:look=6(no). v=0:look=3(1*1=1).
        // v=13:look=16(no). v=17:look=20(no). Total=3.
        assertEquals(3, SimilarTwoSubtract.twoSubtract(new int[]{10, 7, 3, 0, 13, 17}, 3));
    }

    @Test void largeTargetNoMatch() {
        assertEquals(0, SimilarTwoSubtract.twoSubtract(new int[]{1, 2, 3, 4, 5}, 100));
    }
}
