package list;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 146, LintCode 134, medium, tags: hash table, linked list, design, doubly-linked list.
 * Companies: pinterest.
 * <p>
 * Design and implement a data structure for Least Recently Used (LRU) cache.
 * It should support the following operations: get and put.
 * <p>
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity,
 * it should invalidate the least recently used item before inserting a new item.
 * <p>
 * The cache is initialized with a positive capacity.
 * <p>
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 * <p>
 * Example:
 * <p>
 * LRUCache cache = new LRUCache(2); // capacity 2
 * <pre>
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // returns 1
 * cache.put(3,3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.put(4,4);    // evicts key 1
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 * </pre>
 * <p>
 * <b>Summary</b>:
 * <p>
 * <ul>
 * <li>Cannot use java standard library linked list, remove is O(N). Use hash table and doubly
 * linked list O(1) time, O(N) space. Note cannot use HashTable at leetcode, not imported maybe. 46 ms, 111 mb.
 * </ul>
 */
@SuppressWarnings("unused")
public class LRUCache {
    Map<Integer, Node> cache; // key -> node
    int cnt;
    int capacity;
    Node head, tail;

    public LRUCache(int capacity) {
        this.cnt = 0;
        this.capacity = capacity;
        cache = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    private void addToHead(Node node) {
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    private Node popTail() {
        Node res = tail.pre;
        removeNode(res);
        return res;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) return -1;
        moveToHead(node);
        return node.v;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node == null) {
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addToHead(newNode);
            ++cnt;
            if (cnt > capacity) {
                Node tail = popTail();
                cache.remove(tail.k);
                --cnt;
            }
        } else {
            node.v = value;
            moveToHead(node);
        }
    }

    static class Node {
        int k;
        int v;
        Node pre;
        Node next;

        Node(int k, int v) {
            this.k = k;
            this.v = v;
        }

        Node() {
        }
    }
}
