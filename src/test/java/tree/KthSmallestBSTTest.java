package tree;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import struct.TreeNode;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KthSmallestBSTTest {

    static Stream<Arguments> cases() {
        // [3,1,4,null,2], k=1 -> 1
        TreeNode t1 = new TreeNode(3);
        t1.left = new TreeNode(1);
        t1.right = new TreeNode(4);
        t1.left.right = new TreeNode(2);

        // [5,3,6,2,4,null,null,1], k=3 -> 3
        TreeNode t2 = new TreeNode(5);
        t2.left = new TreeNode(3);
        t2.right = new TreeNode(6);
        t2.left.left = new TreeNode(2);
        t2.left.right = new TreeNode(4);
        t2.left.left.left = new TreeNode(1);

        // single node, k=1
        TreeNode t3 = new TreeNode(42);

        // right-skewed: 1->2->3, k=2 -> 2
        TreeNode t4 = new TreeNode(1);
        t4.right = new TreeNode(2);
        t4.right.right = new TreeNode(3);

        // k=n (largest), [2,1,3], k=3 -> 3
        TreeNode t5 = new TreeNode(2);
        t5.left = new TreeNode(1);
        t5.right = new TreeNode(3);

        return Stream.of(
                Arguments.of(t1, 1, 1),
                Arguments.of(t2, 3, 3),
                Arguments.of(t3, 1, 42),
                Arguments.of(t4, 2, 2),
                Arguments.of(t5, 3, 3)
        );
    }

    @ParameterizedTest
    @MethodSource("cases")
    void testKthSmallest(TreeNode root, int k, int expected) {
        assertEquals(expected, KthSmallestBST.kthSmallest(root, k));
    }
}
