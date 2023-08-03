package list;

import org.junit.jupiter.api.Test;
import struct.ListNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SortListTest {
    @Test
    public void testSort() {
        ListNode head = ListNode.createFromArray(new int[]{4, 2, 1, 3});
        assertEquals(ListNode.createFromArray(new int[]{1, 2, 3, 4}), SortList.sortList(head));
    }

}