package list;

import org.junit.jupiter.api.Test;
import struct.ListNode;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListCycleIITest {

    /**
     * Builds a linked list from values and connects the tail to the node at index pos.
     * Returns array: [head, expectedCycleStart]. If pos == -1, no cycle and expectedCycleStart is null.
     */
    private ListNode[] buildCycleList(int[] values, int pos) {
        if (values == null || values.length == 0) return new ListNode[]{null, null};
        ListNode[] nodes = new ListNode[values.length];
        for (int i = 0; i < values.length; i++) nodes[i] = new ListNode(values[i]);
        for (int i = 0; i < values.length - 1; i++) nodes[i].next = nodes[i + 1];
        ListNode cycleStart = null;
        if (pos >= 0) {
            nodes[values.length - 1].next = nodes[pos];
            cycleStart = nodes[pos];
        }
        return new ListNode[]{nodes[0], cycleStart};
    }

    @Test
    void testExample1() {
        // [3,2,0,-4] pos=1 -> node at index 1
        ListNode[] list = buildCycleList(new int[]{3, 2, 0, -4}, 1);
        assertSame(list[1], LinkedListCycleII.detectCycle(list[0]));
        assertSame(list[1], LinkedListCycleII.detectCycleHashSet(list[0]));
    }

    @Test
    void testExample2() {
        // [1,2] pos=0 -> node at index 0
        ListNode[] list = buildCycleList(new int[]{1, 2}, 0);
        assertSame(list[1], LinkedListCycleII.detectCycle(list[0]));
        assertSame(list[1], LinkedListCycleII.detectCycleHashSet(list[0]));
    }

    @Test
    void testNoCycle() {
        // [1] pos=-1 -> null
        ListNode[] list = buildCycleList(new int[]{1}, -1);
        assertNull(LinkedListCycleII.detectCycle(list[0]));
        assertNull(LinkedListCycleII.detectCycleHashSet(list[0]));
    }

    @Test
    void testNullHead() {
        assertNull(LinkedListCycleII.detectCycle(null));
        assertNull(LinkedListCycleII.detectCycleHashSet(null));
    }

    @Test
    void testSelfCycle() {
        // [1] pos=0 -> self-cycle
        ListNode[] list = buildCycleList(new int[]{1}, 0);
        assertSame(list[1], LinkedListCycleII.detectCycle(list[0]));
        assertSame(list[1], LinkedListCycleII.detectCycleHashSet(list[0]));
    }

    @Test
    void testLongerListCycleAtPos3() {
        // [1,2,3,4,5] pos=3
        ListNode[] list = buildCycleList(new int[]{1, 2, 3, 4, 5}, 3);
        assertSame(list[1], LinkedListCycleII.detectCycle(list[0]));
        assertSame(list[1], LinkedListCycleII.detectCycleHashSet(list[0]));
    }

    @Test
    void testTailCycle() {
        // [1,2,3] pos=2 -> tail points to itself
        ListNode[] list = buildCycleList(new int[]{1, 2, 3}, 2);
        assertSame(list[1], LinkedListCycleII.detectCycle(list[0]));
        assertSame(list[1], LinkedListCycleII.detectCycleHashSet(list[0]));
    }
}
