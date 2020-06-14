package struct;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

class ListNodeTest {

    @Test
    void testEquals() {
        ListNode node1 = ListNode.createFromArray(new int[]{1, 2});
        ListNode node2 = ListNode.createFromArray(new int[]{1, 2});
        System.out.println(System.identityHashCode(node1) + " " + System.identityHashCode(node2));
        assertNotSame(node1, node2);
        assertEquals(node1, node2);
    }

}
