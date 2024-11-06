package tree;

import java.util.ArrayDeque;
import java.util.Deque;
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
public class TreapNode {
    int key, priority, cnt;
    TreapNode l, r; // left and right subtrees

    public TreapNode(int key, int priority) {
        this.key = key;
        this.priority = priority;
    }

    public TreapNode(int key) {
        this.key = key;
        this.priority = new Random().nextInt();
    }

    /**
     * left split contains keys <= key.
     *
     * @param key to split with.
     * @return split result with a left treap and right treap.
     */
    SplitRes split(int key) {
        SplitRes res;
        if (this.key <= key) {
            SplitRes rRes = r.split(key);
            r = rRes.left;
            res = new SplitRes(this, rRes.right);
        } else {
            SplitRes lRes = l.split(key);
            l = lRes.right;
            res = new SplitRes(lRes.left, this);
        }
        return res;
    }

    public TreapNode insert(TreapNode tn) {
        if (this.priority < tn.priority) {
            SplitRes res = this.split(tn.key);
            tn.l = res.left;
            tn.r = res.right;
            return tn;
        } else if (key <= tn.key)
            this.r = this.r.insert(tn);
        else this.l = this.l.insert(tn);
        return this;
    }

    /**
     * @return level order string.
     */
    public String toString() {
        Deque<TreapNode> q = new ArrayDeque<>();
        q.add(this);
        StringBuilder res = new StringBuilder();
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            res.append(level).append(':');
            while (size-- > 0) {
                TreapNode cur = q.remove();
                res.append('(').append(cur.key).append(',').append(cur.priority).append("),");
                if (cur.l != null) q.add(cur.l);
                if (cur.r != null) q.add(cur.r);
            }
            res.append(System.lineSeparator());
        }
        return res.toString();
    }

    public static class SplitRes {
        TreapNode left, right; // left and right of the split treap

        public SplitRes(TreapNode left, TreapNode right) {
            this.left = left;
            this.right = right;
        }
    }
}
