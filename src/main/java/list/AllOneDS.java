package list;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * LeetCode 432, hard, tags: hash table, linked list, design, doubly linked list.
 * Design a data structure to store the strings' count with the ability to return the strings with minimum and
 * maximum counts.
 * <p>
 * Implement the AllOne class:
 * <p>
 * AllOne() Initializes the object of the data structure.
 * inc(String key) Increments the count of the string key by 1. If key does not exist in the data structure,
 * insert it with count 1.
 * dec(String key) Decrements the count of the string key by 1. If the count of key is 0 after the decrement,
 * remove it from the data structure. It is guaranteed that key exists in the data structure before the decrement.
 * getMaxKey() Returns one of the keys with the maximal count. If no element exists, return an empty string "".
 * getMinKey() Returns one of the keys with the minimum count. If no element exists, return an empty string "".
 * Note that each function must run in O(1) average time complexity.
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
 * [[], ["hello"], ["hello"], [], [], ["leet"], [], []]
 * Output
 * [null, null, null, "hello", "hello", null, "hello", "leet"]
 * <p>
 * Explanation
 * AllOne allOne = new AllOne();
 * allOne.inc("hello");
 * allOne.inc("hello");
 * allOne.getMaxKey(); // return "hello"
 * allOne.getMinKey(); // return "hello"
 * allOne.inc("leet");
 * allOne.getMaxKey(); // return "hello"
 * allOne.getMinKey(); // return "leet"
 * <p>
 * Constraints:
 * <p>
 * 1 <= key.length <= 10, n
 * key consists of lowercase English letters.
 * It is guaranteed that for each call to dec, key is existing in the data structure.
 * At most 5 * 10^4 calls will be made to inc, dec, getMaxKey, and getMinKey.
 */
@SuppressWarnings("unused")
public class AllOneDS {
    // 76ms, 63.8mb.
    static class Node {
        int cnt;
        Node prev;
        Node next;
        Set<String> keys = new HashSet<>(); // keys with same freq

        Node(int cnt) {
            this.cnt = cnt;
        }

        Node(int cnt, Node prev, Node next) {
            this.cnt = cnt;
            this.prev = prev;
            this.next = next;
            prev.next = this;
            next.prev = this;
        }
    }

    // solution 1, map, doubly linked list with set, 1, n.
    static class AllOne {
        Node head;
        Node tail;
        Map<String, Node> keyNode; // key -> node

        AllOne() {
            head = new Node(-1); // dummy head
            tail = new Node(-1); // dummy tail
            head.next = tail;
            tail.prev = head;
            keyNode = new HashMap<>();
        }

        public void inc(String key) {
            if (keyNode.containsKey(key)) {
                Node node = keyNode.get(key);
                node.keys.remove(key);
                updateNode(node.cnt + 1, key, node, true); // move key to the next node (new or existing)
                if (node.keys.isEmpty()) removeNode(node);
            } else updateNode(1, key, head, true); // add new node after head

        }

        public void dec(String key) {
            if (!keyNode.containsKey(key)) return;
            Node node = keyNode.get(key);
            node.keys.remove(key);
            int cnt = node.cnt;
            if (cnt == 1) keyNode.remove(key); // no need to move key
            else updateNode(cnt - 1, key, node, false); // move key to the previous node (new or existing)
            if (node.keys.isEmpty()) removeNode(node);
        }

        public String getMaxKey() {
            if (tail.prev == head) return "";
            return tail.prev.keys.iterator().next();
        }

        public String getMinKey() {
            if (head.next == tail) return "";
            return head.next.keys.iterator().next();
        }

        private void removeNode(Node node) {
            Node prev = node.prev, next = node.next;
            prev.next = next;
            next.prev = prev;
        }

        /**
         * Add a new node or move key to the correct node.
         *
         * @param cnt  the target cnt.
         * @param key  the string key
         * @param node the node where the key was.
         * @param inc  whether to inc or dec cnt
         */
        void updateNode(int cnt, String key, Node node, boolean inc) {
            Node next = node.next, prev = node.prev, res;
            // if inc, add key to next if cnt match, otherwise insert new node between node, next
            if (inc) res = next.cnt == cnt ? next : new Node(cnt, node, next);
            else res = prev.cnt == cnt ? prev : new Node(cnt, prev, node);
            // dec, add key to prev if cnt match, otherwise insert new node between prev, node
            keyNode.put(key, res);
            res.keys.add(key);
        }
    }
}
