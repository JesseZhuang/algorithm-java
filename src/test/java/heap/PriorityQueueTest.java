package heap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class PriorityQueueTest {

    PriorityQueue<Integer> tbt;

    @BeforeEach
    void setup() {
        tbt = new PriorityQueue<>();
        Arrays.stream(new int[]{1, 2, 1}).forEach(i -> tbt.add(i));
    }


    @Test
    void testRemoveElement() {
        tbt.remove(1); // O(n) time, removes the first equals() in the heap array
        assertEquals(1, tbt.peek());
    }


    /**
     * add() is a wrapper of offer(), no difference. both returns boolean.
     * <p>
     * in the Queue interface, add() can throw IllegalStateException, which offer() cannot.
     */
    @Test
    void pollVsRemove() {
        assertEquals(1, tbt.poll()); // returns min element
        assertEquals(1, tbt.remove()); // just a wrapper if not empty, from AbstractQueue
        assertEquals(2, tbt.poll());
        assertEquals(null, tbt.poll()); // returns null if empty
        assertThrowsExactly(NoSuchElementException.class, () -> tbt.remove()); // throws exception if empty
    }
}
