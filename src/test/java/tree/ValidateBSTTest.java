package tree;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import struct.TreeNode;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidateBSTTest {

    static Stream<Arguments> cases() {
        // [2,1,3] -> true
        TreeNode t1 = new TreeNode(2);
        t1.left = new TreeNode(1);
        t1.right = new TreeNode(3);

        // [5,1,4,null,null,3,6] -> false
        TreeNode t2 = new TreeNode(5);
        t2.left = new TreeNode(1);
        t2.right = new TreeNode(4);
        t2.right.left = new TreeNode(3);
        t2.right.right = new TreeNode(6);

        // single node [1] -> true
        TreeNode t3 = new TreeNode(1);

        // equal values [2,2,2] -> false
        TreeNode t4 = new TreeNode(2);
        t4.left = new TreeNode(2);
        t4.right = new TreeNode(2);

        // [5,4,6,null,null,3,7] -> false (3 in right subtree < root 5)
        TreeNode t5 = new TreeNode(5);
        t5.left = new TreeNode(4);
        t5.right = new TreeNode(6);
        t5.right.left = new TreeNode(3);
        t5.right.right = new TreeNode(7);

        // [2147483647] -> true (max int, ensure no overflow with long bounds)
        TreeNode t6 = new TreeNode(Integer.MAX_VALUE);

        // left skewed 3->2->1 -> true
        TreeNode t7 = new TreeNode(3);
        t7.left = new TreeNode(2);
        t7.left.left = new TreeNode(1);

        // right skewed 1->2->3 -> true
        TreeNode t8 = new TreeNode(1);
        t8.right = new TreeNode(2);
        t8.right.right = new TreeNode(3);

        return Stream.of(
                Arguments.of(t1, true),
                Arguments.of(t2, false),
                Arguments.of(t3, true),
                Arguments.of(t4, false),
                Arguments.of(t5, false),
                Arguments.of(t6, true),
                Arguments.of(t7, true),
                Arguments.of(t8, true)
        );
    }

    @ParameterizedTest
    @MethodSource("cases")
    void testIsValidBST(TreeNode root, boolean expected) {
        assertEquals(expected, ValidateBST.isValidBST(root));
    }

    @ParameterizedTest
    @MethodSource("cases")
    void testIsValidBST2(TreeNode root, boolean expected) {
        assertEquals(expected, ValidateBST.isValidBST2(root));
    }
}
