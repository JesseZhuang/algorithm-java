package tree;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import struct.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaxDepthBTTest {
    private static MaxDepthBT toBeTested;

    @BeforeAll
    static void setup() {
        toBeTested = new MaxDepthBT();
    }

    @ParameterizedTest(name = "depth of tree {0} = {1}")
    @CsvSource(value = "0,1,2,3,#,#,#,4,#,#,#;4", delimiter = ';')
    void testMaxDepth(String tree, int depth) {
        assertEquals(toBeTested.maxDepth(TreeNode.readFromLevelOrderString(tree)), depth);
        assertEquals(toBeTested.maxDepth2(TreeNode.readFromLevelOrderString(tree)), depth);
    }
}
