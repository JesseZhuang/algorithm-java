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

    @Test
    public void testSortExample2() {
        ListNode head = ListNode.createFromArray(new int[]{-1, 5, 3, 4, 0});
        assertEquals(ListNode.createFromArray(new int[]{-1, 0, 3, 4, 5}), SortList.sortList(head));
    }

    @Test
    public void testSortEmpty() {
        assertEquals(null, SortList.sortList(null));
    }

    @Test
    public void testSortDuplicates() {
        ListNode head = ListNode.createFromArray(new int[]{3, 1, 2, 3, 1});
        assertEquals(ListNode.createFromArray(new int[]{1, 1, 2, 3, 3}), SortList.sortList(head));
    }

    @Test
    public void testSortRecursive() {
        ListNode head = ListNode.createFromArray(new int[]{4, 2, 1, 3});
        assertEquals(ListNode.createFromArray(new int[]{1, 2, 3, 4}), SortList.sortListRecursive(head));
    }

    @Test
    public void testSortRecursiveExample2() {
        ListNode head = ListNode.createFromArray(new int[]{-1, 5, 3, 4, 0});
        assertEquals(ListNode.createFromArray(new int[]{-1, 0, 3, 4, 5}), SortList.sortListRecursive(head));
    }

    @Test
    public void testSortRecursiveReverse() {
        ListNode head = ListNode.createFromArray(new int[]{5, 4, 3, 2, 1});
        assertEquals(ListNode.createFromArray(new int[]{1, 2, 3, 4, 5}), SortList.sortListRecursive(head));
    }

}