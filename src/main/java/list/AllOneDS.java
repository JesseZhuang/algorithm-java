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
    static class Node {
        int freq;
        Node prev;
        Node next;
        Set<String> keys = new HashSet<>(); // keys with same freq

        Node(int freq) {
            this.freq = freq;
        }
    }

    // solution 1, map, doubly linked list with set, 1, n.
    static class AllOne {
        Node head;
        Node tail;
        Map<String, Node> map = new HashMap<>();

        AllOne() {
            head = new Node(0); // dummy head
            tail = new Node(0); // dummy tail
            head.next = tail;
            tail.prev = head;
        }

        public void inc(String key) {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                int freq = node.freq;
                node.keys.remove(key);

                Node next = node.next;
                if (next == tail || next.freq != freq + 1) {
                    Node nn = new Node(freq + 1); // new node
                    nn.keys.add(key);
                    nn.prev = node;
                    nn.next = next;
                    node.next = nn;
                    next.prev = nn;
                    map.put(key, nn);
                } else {
                    next.keys.add(key);
                    map.put(key, next);
                }

                if (node.keys.isEmpty()) removeNode(node);
            } else {
                Node first = head.next;
                if (first == tail || first.freq > 1) {
                    Node nn = new Node(1); // new node
                    nn.keys.add(key);
                    nn.prev = head;
                    nn.next = first;
                    head.next = nn;
                    first.prev = nn;
                    map.put(key, nn);
                } else {
                    first.keys.add(key);
                    map.put(key, first);
                }
            }
        }

        public void dec(String key) {
            if (!map.containsKey(key)) return;

            Node node = map.get(key);
            node.keys.remove(key);
            int freq = node.freq;

            if (freq == 1) map.remove(key);
            else {
                Node prevNode = node.prev;
                if (prevNode == head || prevNode.freq != freq - 1) {
                    Node newNode = new Node(freq - 1);
                    newNode.keys.add(key);
                    newNode.prev = prevNode;
                    newNode.next = node;
                    prevNode.next = newNode;
                    node.prev = newNode;
                    map.put(key, newNode);
                } else {
                    prevNode.keys.add(key);
                    map.put(key, prevNode);
                }
            }

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
            Node prev = node.prev;
            Node next = node.next;
            prev.next = next;
            next.prev = prev;
        }
    }
}
