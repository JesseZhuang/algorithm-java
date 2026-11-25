package tree;

import java.util.ArrayDeque;
import java.util.Queue;

/** LeetCode 117. Populating Next Right Pointers in Each Node II. */
public final class PopulatingNextRightPointersII {

    private PopulatingNextRightPointersII() {
    }

    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    public static Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        // Each node is queued once. O(n) time and O(width) auxiliary space.
        while (!queue.isEmpty()) {
            for (int remaining = queue.size(); remaining > 0; remaining--) {
                Node node = queue.remove();
                node.next = remaining > 1 ? queue.peek() : null;
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return root;
    }

    public static Node connectConstantSpace(Node root) {
        if (root == null) {
            return null;
        }

        root.next = null;
        Node dummy = new Node(0);
        Node levelStart = root;
        // Follow each level's next links once and reuse the dummy head. O(n) time and O(1) auxiliary space.
        while (levelStart != null) {
            dummy.next = null;
            Node tail = dummy;
            for (Node node = levelStart; node != null; node = node.next) {
                if (node.left != null) {
                    tail.next = node.left;
                    tail = node.left;
                }
                if (node.right != null) {
                    tail.next = node.right;
                    tail = node.right;
                }
            }
            tail.next = null;
            levelStart = dummy.next;
        }
        return root;
    }
}
