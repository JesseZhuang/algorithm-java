package list;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import struct.ListNode;
import util.ListNodeConverter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReverseLinkedListTest {
    private static ReverseLinkedList reverseLinkedList;

    @BeforeAll
    static void setup() {
        reverseLinkedList = new ReverseLinkedList();
    }

    @ParameterizedTest(name = "{0} reversed : {1}")
    @CsvFileSource(resources = {"/ReverseLinkedList.csv"}, delimiter = ' ', numLinesToSkip = 2)
    void testReverseLinkedList(@ConvertWith(ListNodeConverter.class) ListNode head,
                               @ConvertWith(ListNodeConverter.class) ListNode reversed) {
        ListNode headOfReversed;
        assertEquals(headOfReversed = reverseLinkedList.reverseListIterative(head), reversed);
        head = reverseLinkedList.reverseListIterative(headOfReversed);
        assertEquals(reverseLinkedList.reverseListIterative2(head), reversed);
        head = reverseLinkedList.reverseListIterative(headOfReversed);
        assertEquals(reverseLinkedList.reverseListRecursive1(head), reversed);
        head = reverseLinkedList.reverseListIterative(headOfReversed);
        assertEquals(reverseLinkedList.reverseListRecursive2(head), reversed);
    }
}
