package tree;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import struct.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaxPathSumBTTest {
    static MaxPathSumBT tbt;

    @BeforeAll
    static void setup() {
        tbt = new MaxPathSumBT();
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/MaxPathSumBT.csv"}, delimiter = ' ', numLinesToSkip = 2)
    void testMaxPathSum(String tree, int sum) {
        TreeNode root = TreeNode.readFromLevelOrderString(tree);
        assertEquals(sum, tbt.maxPathSum(root));
    }
}
