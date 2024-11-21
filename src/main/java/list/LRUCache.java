package list;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 146. Medium. Tags: hash table, linked list, design, doubly-linked list.
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
 * linked list O(1) time, O(N) space. Note cannot use HashTable at leetcode, not imported maybe. 38ms, 109.8Mb.
 * </ul>
 */
@SuppressWarnings("unused")
public class LRUCache {
    private Map<Integer, Node> cache = new HashMap<>();
    private int count;
    private int capacity;
    private Node head, tail;

    public LRUCache(int capacity) {
        this.count = 0;
        this.capacity = capacity;
        head = new Node();
        head.pre = null;
        tail = new Node();
        tail.post = null;
        head.post = tail;
        tail.pre = head;
    }

    /**
     * Always add the new node right after head;
     */
    private void addNode(Node node) {
        node.pre = head;
        node.post = head.post;

        head.post.pre = node;
        head.post = node;
    }

    /**
     * Remove an existing node from the linked list.
     */
    private void removeNode(Node node) {
        Node pre = node.pre;
        Node post = node.post;

        pre.post = post;
        post.pre = pre;
    }

    /**
     * Move certain node in between to the head.
     */
    private void moveToHead(Node node) {
        this.removeNode(node);
        this.addNode(node);
    }

    // pop the current tail.
    private Node popTail() {
        Node res = tail.pre;
        this.removeNode(res);
        return res;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) return -1;
        // move the accessed node to the head;
        this.moveToHead(node);
        return node.value;
    }


    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node == null) {
            Node newNode = new Node();
            newNode.key = key;
            newNode.value = value;
            this.cache.put(key, newNode);
            this.addNode(newNode);
            ++count;
            if (count > capacity) {
                Node tail = this.popTail();
                this.cache.remove(tail.key);
                --count;
            }
        } else {
            // update the value.
            node.value = value;
            this.moveToHead(node);
        }
    }

    class Node {
        int key;
        int value;
        Node pre;
        Node post;
    }
}
