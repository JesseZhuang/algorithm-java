package tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Random;

class ImplicitTreap<E> {
    private Node<E> root;

    public static void main(String[] args) {
        ImplicitTreap<Integer> treap = new ImplicitTreap<>();
        treap.insert(0, 10); // Insert 10 at position 0
        treap.insert(1, 20); // Insert 20 at position 1
        treap.insert(1, 15); // Insert 15 at position 1, 20 pushed to position 2
        treap.inorder(); // Expected output: 10 15 20

        System.out.println("Value at position 1: " + treap.get(1)); // Expected output: 10
        System.out.println("Value at position 2: " + treap.get(2)); // Expected output: 15

        treap.delete(1); // Delete value at position 1
        treap.inorder(); // Expected output: 10 20
    }

    private void updateSize(Node<E> n) {
        if (n != null) n.cnt = 1 + size(n.left) + size(n.right);
    }

    // Get the size of a node (null-safe)
    private int size(Node<E> node) {
        return node == null ? 0 : node.cnt;
    }

    // Split operation: splits the treap into two treaps at a given position
    private SplitTreap<E> split(Node<E> node, int key) {
        if (node == null) return new SplitTreap<>(null, null);
        int implicitKey = size(node.left) + 1;
        if (key < implicitKey) {
            SplitTreap<E> lsp = split(node.left, key);
            node.left = lsp.rst;
            updateSize(node);
            return new SplitTreap<>(lsp.lst, node);
        } else {
            SplitTreap<E> rsp = split(node.right, key - implicitKey);
            node.right = rsp.lst;
            updateSize(node);
            return new SplitTreap<>(node, rsp.rst);
        }
    }

    // Merge operation: merges two treaps into one. Assumption, keys in left < right
    private Node<E> merge(Node<E> left, Node<E> right) {
        if (left == null || right == null) return right == null ? left : right;
        if (left.priority > right.priority) {
            left.right = merge(left.right, right);
            updateSize(left);
            return left;
        } else {
            right.left = merge(left, right.left);
            updateSize(right);
            return right;
        }
    }

    // Insert a new value at a given position, shift existing elements to the right by 1
    public void insert(int position, E value) {
        Node<E> newNode = new Node<>(value);
        SplitTreap<E> sp = split(root, position);
        root = merge(merge(sp.lst, newNode), sp.rst);
    }

    // Delete the node at a given position, 0-based index
    public void delete(int position) {
        SplitTreap<E> split1 = split(root, position);
        SplitTreap<E> split2 = split(split1.rst, 1);
        root = merge(split1.lst, split2.rst);
    }

    // Get the value at a specific position, 1-based index
    public E get(int position) {
        return get(root, position);
    }

    private E get(Node<E> node, int position) {
        if (node == null) throw new IndexOutOfBoundsException();

        int implicitKey = size(node.left) + 1; // nodes in left subtree + root, 1 based
        if (position < implicitKey)
            return get(node.left, position);
        else if (position > implicitKey)
            return get(node.right, position - implicitKey);
        else return node.val;
    }

    // Utility method for in-order traversal (for debugging)
    public void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(Node<E> node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.val + " ");
            inorder(node.right);
        }
    }

    @ToString
    @RequiredArgsConstructor
    static class Node<E> {
        int priority = new Random().nextInt(), cnt = 1;
        Node<E> left = null, right = null;
        @NonNull
        private E val;
    }

    @Data
    @AllArgsConstructor
    static class SplitTreap<E> {
        Node<E> lst, rst;
    }
}
