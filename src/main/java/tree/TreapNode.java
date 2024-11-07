package tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

/**
 * A combination of heap and binary tree. May have applications in security (no history info depending on insertion
 * order) and efficient subtree sharing, fastest algorithm for set operations.
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

    public static void main(String[] args) {
        TreapNode root = new TreapNode(2, 2);
        System.out.println(root.levelOrderString());
        root = insert(root, new TreapNode(5, 6));
        root = insert(root, new TreapNode(2, 9));
        System.out.println(root.levelOrderString());
    }

    /**
     * left split contains keys <= key. O(lgn) time.
     *
     * @param key to split with.
     * @return split result with a left treap and right treap.
     */
    public static SplitRes split(TreapNode t, int key) {
        SplitRes res = new SplitRes(null, null);
        if (t == null) return res;
        if (t.key <= key) {
            SplitRes rRes = split(t.r, key);
            t.r = rRes.left;
            res = new SplitRes(t, rRes.right);
        } else {
            SplitRes lRes = split(t.l, key);
            t.l = lRes.right;
            res = new SplitRes(lRes.left, t);
        }
        return res;
    }

    /**
     * Assumption all keys in t1 are smaller than keys in t2.
     *
     * @param t1 subtree 1.
     * @param t2 subtree 2.
     * @return the merged tree.
     */
    public static TreapNode merge(TreapNode t1, TreapNode t2) {
        if (t1 == null || t2 == null) return t1 == null ? t2 : t1;
        if (t1.priority > t2.priority) {
            t1.r = merge(t1.r, t2);
            return t1;
        } else {
            t2.l = merge(t1, t2.l);
            return t2;
        }
    }

    /**
     * insert a single treap node tn into t. O(lgn) time.
     *
     * @param t  tree to insert into
     * @param tn node to be inserted, so tn.l and tn.r should be null
     * @return the new tree with tn inserted.
     */
    public static TreapNode insert(TreapNode t, TreapNode tn) {
        if (t == null) return tn;
        if (t.priority < tn.priority) {
            SplitRes res = split(t, tn.key);
            tn.l = res.left;
            tn.r = res.right;
            return tn;
        } else if (t.key <= tn.key)
            t.r = insert(t.r, tn);
        else t.l = insert(t.l, tn);
        return t;
    }

    /**
     * O(lgn) time.
     *
     * @param t   tree to delete the key.
     * @param key key to delete.
     * @return the tree with the key deleted.
     */
    public static TreapNode erase(TreapNode t, int key) {
        if (t == null) return null;
        if (t.key == key) return merge(t.l, t.r);
        if (t.key < key) t.r = erase(t.r, key);
        else t.l = erase(t.l, key);
        return t;
    }

    /**
     * Assume no duplicate key. O(Mlg(N/M)) time. Possible to achieve same complexity if duplicate elements
     * should be removed during union.
     *
     * @param t1 tree 1.
     * @param t2 tree 2.
     * @return the union tree.
     */
    public static TreapNode union(TreapNode t1, TreapNode t2) {
        if (t1 == null || t2 == null) return t1 == null ? t2 : t1;
        if (t1.priority < t2.priority) return union(t2, t1);
        SplitRes res = split(t2, t1.key);
        t1.l = union(t1.l, res.left);
        t1.r = union(t1.r, res.right);
        return t1;
    }

    public static int cnt(TreapNode t) {
        if (t == null) return 0;
        else return t.cnt;
    }

    private static void updateCnt(TreapNode t) {
        if (t != null) t.cnt = 1 + cnt(t.l) + cnt(t.r);
    }

    public void heapify(TreapNode t) {
        if (t == null) return;
        TreapNode max = t;
        if (t.l != null && t.l.priority > max.priority) max = t.l;
        if (t.r != null && t.r.priority > max.priority) max = t.r;
        if (max != t) {
            int tmp = t.priority;
            t.priority = max.priority;
            max.priority = tmp;
            heapify(max);
        }
    }

    /**
     * Given a sorted list of keys, it is possible to construct a treap faster than by inserting the keys one at
     * a time which takes O(NlogN) time. Since the keys are sorted, a balanced binary search tree can be easily
     * constructed in linear time. The heap values Y are initialized randomly and then can be heapified independent
     * of the keys to build the heap in O(N) time.
     * <p>
     * The approach always provides a perfectly balanced tree, which is generally good for practical purposes,
     * but at the cost of not preserving the priorities that were initially assigned to each node.
     * Thus, this approach is not feasible to solve the following problem:
     * <p>
     * codeforces 155 - Cartesian Tree. Given a sequence of pairs (x_i, y_i), construct a cartesian tree on them. All
     * x_i and all y_i are unique.
     * <p>
     * Note that in this problem priorities are not random, hence just inserting vertices one by one could
     * provide a quadratic solution.
     *
     * @param a     int array.
     * @param start starting index, inclusive.
     * @param n     size of the array.
     * @return the treap node root for the built tree.
     */
    public TreapNode build(int[] a, int start, int n) {
        if (n == 0) return null;
        int mid = n / 2;
        TreapNode t = new TreapNode(a[mid]);
        t.l = build(a, start, mid);
        t.r = build(a, start + mid + 1, n - mid - 1);
        heapify(t);
        updateCnt(t);
        return t;
    }

    /**
     * @return level order string.
     */
    public String levelOrderString() {
        Deque<TreapNode> q = new ArrayDeque<>();
        q.add(this);
        StringBuilder res = new StringBuilder();
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            res.append(level).append(':').append('[');
            while (size-- > 0) {
                TreapNode cur = q.remove();
                res.append('(').append(cur.key).append(',').append(cur.priority).append(')');
                if (cur.l != null) q.add(cur.l);
                if (cur.r != null) q.add(cur.r);
                if (size != 0) res.append(',');
            }
            res.append(']');
            level++;
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
