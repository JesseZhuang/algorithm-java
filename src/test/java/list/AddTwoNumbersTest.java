package list;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import struct.ListNode;
import util.ListNodeConverter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddTwoNumbersTest {

    private static AddTwoNumbers addTwoNumbers;

    @BeforeAll
    static void setup() {
        addTwoNumbers = new AddTwoNumbers();
    }

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvFileSource(resources = {"/AddTwoNumbers.csv"}, delimiter = ' ', numLinesToSkip = 2)
    void testAddTwoNumbers(@ConvertWith(ListNodeConverter.class) ListNode l1,
                           @ConvertWith(ListNodeConverter.class) ListNode l2,
                           @ConvertWith(ListNodeConverter.class) ListNode sum) {
        assertEquals(addTwoNumbers.addTwoNumbers(l1, l2), sum);
    }
}
