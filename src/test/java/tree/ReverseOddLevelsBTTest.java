package tree;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import struct.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReverseOddLevelsBTTest {
    private static ReverseOddLevelsBT.Solution tbt;

    @BeforeAll
    static void setup() {
        tbt = new ReverseOddLevelsBT.Solution();
    }

    @ParameterizedTest(name = "reverseOddLevels({0}) = {1}")
    @CsvSource(delimiter = ';', value = {
            "2,3,5,8,13,21,34;2,5,3,8,13,21,34",
            "7,13,11;7,11,13",
            "0,1,2,0,0,0,0,1,1,1,1,2,2,2,2;0,2,1,0,0,0,0,2,2,2,2,1,1,1,1",
    })
    void testReverseOddLevels(String input, String expected) {
        TreeNode root = TreeNode.readFromLevelOrderString(input);
        TreeNode result = tbt.reverseOddLevels(root);
        TreeNode expectedTree = TreeNode.readFromLevelOrderString(expected);
        assertEquals(expectedTree, result);
    }
}
