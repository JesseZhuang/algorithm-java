package tree;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.com/problems/design-file-system/">LeetCode 1166</a>, medium,
 * tags: hash table, trie, string, design.
 */
public final class DesignFileSystem {

    private final Map<String, DesignFileSystem> next = new HashMap<>();
    private int val;

    public DesignFileSystem() {
        this.val = -1;
    }

    private DesignFileSystem(int val) {
        this.val = val;
    }

    /**
     * Create path and associate value. Returns false if path exists or parent doesn't exist.
     * Time O(n), Space O(n).
     */
    public boolean createPath(String path, int value) {
        String[] ps = path.split("/");
        DesignFileSystem node = this;
        for (int i = 1; i < ps.length - 1; i++) {
            if (!node.next.containsKey(ps[i])) return false;
            node = node.next.get(ps[i]);
        }
        if (node.next.containsKey(ps[ps.length - 1])) return false;
        node.next.put(ps[ps.length - 1], new DesignFileSystem(value));
        return true;
    }

    /**
     * Get value for path, or -1 if not found.
     * Time O(n), Space O(1).
     */
    public int get(String path) {
        String[] ps = path.split("/");
        DesignFileSystem node = this;
        for (int i = 1; i < ps.length; i++) {
            if (!node.next.containsKey(ps[i])) return -1;
            node = node.next.get(ps[i]);
        }
        return node.val;
    }
}
