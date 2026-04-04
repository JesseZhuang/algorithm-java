package ood;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.BiFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Mirrors {@code InCodeLearning-Python3/test/test_excel_sum_formula.py} for both {@link ExcelSum.Excel} and
 * {@link ExcelSum.ExcelLazy}.
 */
class ExcelSumTest {

    @SuppressWarnings("Convert2MethodRef")
    static Stream<Arguments> spreadsheetFactories() {
        return Stream.of(
                Arguments.of(
                        "eager",
                        (BiFunction<Integer, Character, ExcelSum.Spreadsheet>) ExcelSum.Excel::new),
                Arguments.of(
                        "lazy",
                        (BiFunction<Integer, Character, ExcelSum.Spreadsheet>) ExcelSum.ExcelLazy::new));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("spreadsheetFactories")
    void example(String ignored, BiFunction<Integer, Character, ExcelSum.Spreadsheet> factory) {
        ExcelSum.Spreadsheet sheet = factory.apply(3, 'C');
        sheet.set(1, 'A', 2);
        assertEquals(4, sheet.sum(3, 'C', new String[]{"A1", "A1:B2"}));
        sheet.set(2, 'B', 2);
        assertEquals(6, sheet.get(3, 'C'));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("spreadsheetFactories")
    void overwriteFormulaWithRawValue(String ignored, BiFunction<Integer, Character, ExcelSum.Spreadsheet> factory) {
        ExcelSum.Spreadsheet sheet = factory.apply(2, 'C');
        sheet.set(1, 'A', 5);
        sheet.set(1, 'B', 3);
        assertEquals(8, sheet.sum(2, 'C', new String[]{"A1:B1"}));
        assertEquals(8, sheet.get(2, 'C'));
        sheet.set(2, 'C', 100);
        sheet.set(1, 'A', 20);
        assertEquals(100, sheet.get(2, 'C'));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("spreadsheetFactories")
    void replaceFormulaAndUpdateChain(String ignored, BiFunction<Integer, Character, ExcelSum.Spreadsheet> factory) {
        ExcelSum.Spreadsheet sheet = factory.apply(2, 'D');
        sheet.set(1, 'A', 2);
        sheet.set(1, 'B', 3);
        assertEquals(7, sheet.sum(1, 'C', new String[]{"A1", "A1:B1"}));
        assertEquals(10, sheet.sum(2, 'A', new String[]{"C1", "B1"}));
        assertEquals(10, sheet.get(2, 'A'));
        assertEquals(3, sheet.sum(1, 'C', new String[]{"B1"}));
        assertEquals(6, sheet.get(2, 'A'));
        sheet.set(1, 'A', 10);
        assertEquals(3, sheet.get(1, 'C'));
        assertEquals(6, sheet.get(2, 'A'));
        sheet.set(1, 'B', 5);
        assertEquals(5, sheet.get(1, 'C'));
        assertEquals(10, sheet.get(2, 'A'));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("spreadsheetFactories")
    void overlappingRangesCountMultipleTimes(String ignored, BiFunction<Integer, Character, ExcelSum.Spreadsheet> factory) {
        ExcelSum.Spreadsheet sheet = factory.apply(3, 'D');
        sheet.set(1, 'A', 1);
        sheet.set(1, 'B', 2);
        sheet.set(2, 'A', 3);
        sheet.set(2, 'B', 4);
        assertEquals(16, sheet.sum(3, 'D', new String[]{"A1:B2", "B1:B2"}));
        sheet.set(2, 'B', 5);
        assertEquals(18, sheet.get(3, 'D'));
    }
}
