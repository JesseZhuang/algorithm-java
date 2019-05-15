package list;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import struct.ListNode;
import junit.converter.ListNodeConverter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Merge2SortedListsTest {
    private static Merge2SortedLists toBeTested;

    @BeforeAll
    static void setup() {
        toBeTested = new Merge2SortedLists();
    }

    @ParameterizedTest(name = "{0}, {1} merged : {2}")
    @CsvFileSource(resources = {"/Merge2SortedLists.csv"}, delimiter = ' ', numLinesToSkip = 2)
    void testReverseLinkedListRec(@ConvertWith(ListNodeConverter.class) ListNode l1,
                                  @ConvertWith(ListNodeConverter.class) ListNode l2,
                                  @ConvertWith(ListNodeConverter.class) ListNode merged) {
        assertEquals(merged, toBeTested.mergeTwoListsRec(l1, l2));
    }

    @ParameterizedTest(name = "{0}, {1} merged : {2}")
    @CsvFileSource(resources = {"/Merge2SortedLists.csv"}, delimiter = ' ', numLinesToSkip = 2)
    void testReverseLinkedListIter1(@ConvertWith(ListNodeConverter.class) ListNode l1,
                                  @ConvertWith(ListNodeConverter.class) ListNode l2,
                                  @ConvertWith(ListNodeConverter.class) ListNode merged) {
        assertEquals(merged, toBeTested.mergeTwoListsIter(l1, l2));
    }

    @ParameterizedTest(name = "{0}, {1} merged : {2}")
    @CsvFileSource(resources = {"/Merge2SortedLists.csv"}, delimiter = ' ', numLinesToSkip = 2)
    void testReverseLinkedListIter2(@ConvertWith(ListNodeConverter.class) ListNode l1,
                                  @ConvertWith(ListNodeConverter.class) ListNode l2,
                                  @ConvertWith(ListNodeConverter.class) ListNode merged) {
        assertEquals(merged, toBeTested.mergeTwoListsIter2(l1, l2));
    }
}
