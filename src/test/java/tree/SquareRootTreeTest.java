package tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SquareRootTreeTest {

    @Test
    void testSquareRootDecomposition() {
        int[] nums = {1, 3, 2, 5, 2, 7, 11, 9};
        SquareRootTree squareRoot = new SquareRootTree(nums);
        assertEquals(10, squareRoot.sumRange(1, 3));
        squareRoot.update(3, 10);
        assertEquals(15, squareRoot.sumRange(1, 3));
    }

}
