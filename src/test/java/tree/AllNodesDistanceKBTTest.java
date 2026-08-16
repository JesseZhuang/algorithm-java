package tree;

import org.junit.jupiter.api.Test;
import struct.TreeNode;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AllNodesDistanceKBTTest {

    // Helper: find node by value in tree
    private TreeNode findNode(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) return root;
        TreeNode left = findNode(root.left, val);
        return left != null ? left : findNode(root.right, val);
    }

    @Test
    void testExample1() {
        // root = [3,5,1,6,2,0,8,null,null,7,4], target=5, k=2 -> [7,4,1]
        TreeNode root = TreeNode.readFromLevelOrderString("3,5,1,6,2,0,8,#,#,7,4");
        TreeNode target = findNode(root, 5);
        List<Integer> result = AllNodesDistanceKBT.distanceK(root, target, 2);
        Collections.sort(result);
        assertEquals(List.of(1, 4, 7), result);

        List<Integer> result2 = AllNodesDistanceKBT.distanceK2(root, target, 2);
        Collections.sort(result2);
        assertEquals(List.of(1, 4, 7), result2);
    }

    @Test
    void testK0() {
        // k=0 should return the target itself
        TreeNode root = TreeNode.readFromLevelOrderString("3,5,1,6,2,0,8");
        TreeNode target = findNode(root, 5);
        assertEquals(List.of(5), AllNodesDistanceKBT.distanceK(root, target, 0));
        assertEquals(List.of(5), AllNodesDistanceKBT.distanceK2(root, target, 0));
    }

    @Test
    void testSingleNode() {
        TreeNode root = new TreeNode(1);
        assertEquals(List.of(1), AllNodesDistanceKBT.distanceK(root, root, 0));
        assertEquals(List.of(), AllNodesDistanceKBT.distanceK(root, root, 1));
        assertEquals(List.of(1), AllNodesDistanceKBT.distanceK2(root, root, 0));
        assertEquals(List.of(), AllNodesDistanceKBT.distanceK2(root, root, 1));
    }

    @Test
    void testTargetIsRoot() {
        // target is root, k=1 should return direct children
        TreeNode root = TreeNode.readFromLevelOrderString("1,2,3");
        List<Integer> result = AllNodesDistanceKBT.distanceK(root, root, 1);
        Collections.sort(result);
        assertEquals(List.of(2, 3), result);

        List<Integer> result2 = AllNodesDistanceKBT.distanceK2(root, root, 1);
        Collections.sort(result2);
        assertEquals(List.of(2, 3), result2);
    }

    @Test
    void testTargetIsLeaf() {
        // target is leaf node 4, k=2 should go up through 2 then to 5 and 3
        TreeNode root = TreeNode.readFromLevelOrderString("1,2,3,4,5");
        TreeNode target = findNode(root, 4);
        List<Integer> result = AllNodesDistanceKBT.distanceK(root, target, 2);
        Collections.sort(result);
        assertEquals(List.of(1, 5), result);

        List<Integer> result2 = AllNodesDistanceKBT.distanceK2(root, target, 2);
        Collections.sort(result2);
        assertEquals(List.of(1, 5), result2);
    }

    @Test
    void testKExceedsTreeHeight() {
        TreeNode root = TreeNode.readFromLevelOrderString("1,2,3");
        TreeNode target = findNode(root, 1);
        assertEquals(List.of(), AllNodesDistanceKBT.distanceK(root, target, 5));
        assertEquals(List.of(), AllNodesDistanceKBT.distanceK2(root, target, 5));
    }
}
