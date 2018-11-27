package list;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import struct.ListNode;

import static org.junit.jupiter.api.Assertions.*;

public class IntersectionTwoLinkedListsTest {
    private static IntersectionTwoLinkedLists toBeTested;

    @BeforeAll
    static void setup() {
        toBeTested = new IntersectionTwoLinkedLists();
    }

    @Test
    void testIntersection() {
        ListNode a = ListNode.createFromArray(new int[]{1, 2, 3, 4, 5});
        ListNode b = ListNode.createFromArray(new int[]{6, 7});
        ListNode a5 = a.next.next.next.next;
        assertEquals(5, a5.val);
        b.next.next = a5;
        assertSame(toBeTested.getIntersectionNode1(a, b), a5);
        assertSame(toBeTested.getIntersectionNode2(a, b), a5);
        assertSame(toBeTested.getIntersectionNodeBF(a, b), a5);
        assertSame(toBeTested.getIntersectionNodeSpace(a, b), a5);
    }

    @Test
    void testIntersectionEquals() {
        ListNode a = ListNode.createFromArray(new int[]{1, 2, 3, 4, 5});
        ListNode b = ListNode.createFromArray(new int[]{1, 2, 3, 4, 5});
        assertNotSame(a, b);
        assertEquals(a, b);
        assertSame(toBeTested.getIntersectionNodeSpace(a, b), b);
        assertNull(toBeTested.getIntersectionNode1(a, b));
    }

}
