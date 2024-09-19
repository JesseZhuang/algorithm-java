package princeton.jsl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QueueSinglyLinkedTest {
    QueueSinglyLinked<Integer> tbt;

    @BeforeEach
    void setup() {
        tbt = new QueueSinglyLinked<>();
    }

    @Test
    void testEmptyQueue() {
        assertTrue(tbt.isEmpty());
    }

    @Test
    void testAddRemove() {
        tbt.add(0);
        tbt.add(1);
        assertEquals(2, tbt.size());
        assertEquals(Integer.valueOf(0), tbt.remove());
        assertEquals(Integer.valueOf(1), tbt.remove());
    }
}
