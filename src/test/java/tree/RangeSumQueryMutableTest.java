package tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RangeSumQueryMutableTest {

    @Test
    void testSquareRootDecomposition() {
        int[] nums = {1, 3, 2, 5, 2, 7, 11, 9};
        RangeSumQueryMutable.SquareRoot squareRoot = new RangeSumQueryMutable.SquareRoot(nums);
        assertEquals(10, squareRoot.sumRange(1, 3));
        squareRoot.update(3, 10);
        assertEquals(15, squareRoot.sumRange(1, 3));
    }

}
