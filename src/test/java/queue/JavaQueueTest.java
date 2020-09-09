package queue;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;
import util.CollectionUtil;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JavaQueueTest {
    @Test
    void testJavaQueueImplementations() {
        // ArrayDeque and LinkedList implements Deque and Queue interface
        ArrayDeque<Integer> q1 = new ArrayDeque();
        LinkedList<Integer> q2 = new LinkedList<>();
        List<Deque<Integer>> deques = ImmutableList.of(q1, q2);
        List<Queue<Integer>> queues = ImmutableList.of(q1, q2);
        List<String> implementations = ImmutableList.of("ArrayDeque", "LinkedList");
        for (int i = 0; i < 2; i++) {
            Deque<Integer> deque = deques.get(i);
            deque.add(1);
            deque.add(2);
            System.out.println(implementations.get(i) + ", deque: " + deque);
            Iterable<Integer> iterable = deque;
            assertEquals(ImmutableList.of(1, 2), CollectionUtil.toList(iterable));
            deque.remove();
            deque.remove();
            Queue<Integer> queue = queues.get(i);
            queue.add(1);
            queue.add(2);
            System.out.println(implementations.get(i) + ", queue: " + deque);
            iterable = queue;
            assertEquals(ImmutableList.of(1, 2), CollectionUtil.toList(iterable));
        }
    }

    @Test
    void testJavaPriorityQueue() {
        PriorityQueue pq = new PriorityQueue();
        pq.add(5);
        pq.add(3);
        pq.add(4);
        pq.add(1);
        System.out.println(pq);
        assertEquals(ImmutableList.of(1, 3, 4, 5), CollectionUtil.toList(pq));
    }
}
