package tree;

import java.util.Random;

/**
 * A combination of heap and binary tree. May have applications in security (no history info depending on insertion
 * order) and efficient sub tree sharing, fastest algorithm for set operations.
 * <p>
 * More specifically, treap is a data structure that stores pairs
 * (X, Y) in a binary tree in such a way that it is a binary search tree by X and a binary heap by Y.
 * If some node of the tree contains values
 * (X_0, Y_0), all nodes in the left subtree have X <= X_0
 * all nodes in the right subtree have X_0 <= X
 * and all nodes in both left and right subtrees have Y <= Y_0.
 * <p>
 * A treap is also often referred to as a "cartesian tree", as it is easy to embed it in a Cartesian plane.
 * <p>
 * one set of X values could correspond to a lot of different trees, some of them degenerate (for example,
 * in the form of a linked list), and therefore extremely slow (the main operations would have linear complexity).
 * <p>
 * At the same time, priorities (Y, when they're unique) allow to uniquely specify the tree that will be constructed
 * (of course, it does not depend on the order in which values are added). Obviously, if you choose the
 * priorities randomly, you will get non-degenerate trees on average, which will ensure O(lgN) complexity for the
 * main operations. Hence, another name of this data structure - randomized binary search tree.
 * <p>
 * References:
 * 1, https://cp-algorithms.com/data_structures/treap.html
 * 2, wikipedia
 * 3, https://stackoverflow.com/questions/16009361/when-to-use-a-treap
 */
@SuppressWarnings("unused")
public class Treap {
    static class Item {
        Random rand = new Random();
        int key, priority;
        Item l, r; // left and right subtrees

        public Item(int key, int priority) {
            this.key = key;
            this.priority = priority;
        }

        public Item(int key) {
            this.key = key;
            this.priority = rand.nextInt();
        }
    }
}
