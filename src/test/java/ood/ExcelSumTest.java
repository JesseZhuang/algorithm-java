package ood;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExcelSumTest {
    ExcelSum.Excel tbt;

    @Test
    void testExample() {
        tbt = new ExcelSum.Excel(3, 'C');
        assertEquals(0, tbt.get(1, 'A'));
        tbt.set(1, 'A', 2);
        assertEquals(2, tbt.get(1, 'A'));

        assertEquals(0, tbt.get(3, 'C'));
        tbt.sum(3, 'C', new String[]{"A1", "A1:B2"});
        assertEquals(1, tbt.sheet[2][0].dependee.size()); // C3->A2
        assertEquals(4, tbt.get(3, 'C'));

        assertEquals(0, tbt.get(2, 'B'));
        tbt.set(2, 'B', 2);
        assertEquals(2, tbt.get(2, 'B'));
        assertEquals(6, tbt.get(3, 'C'));

        assertEquals(0, tbt.get(2, 'C'));
        tbt.sum(2, 'C', new String[]{"A1:A2", "C3"}); // C3->A1 and C2->A1 C2->C3, kind of parallel edges
        assertEquals(8, tbt.get(2, 'C'));
        assertEquals(2, tbt.sheet[1][0].dependee.size()); // (C3,C2)->A1
        assertEquals(1, tbt.sheet[3][2].dependee.size()); // C2->C3
        assertEquals(2, tbt.sheet[2][0].dependee.size()); // (C3,C2)->A2

        tbt.sum(3, 'C', new String[]{"A1"});
        assertEquals(1, tbt.sheet[2][0].dependee.size()); // cell[2]['A'] dependee -= 1
        assertEquals(0, tbt.sheet[1][1].dependee.size()); // B1 dependee decrease to 0
        assertEquals(2, tbt.sheet[1][0].dependee.size()); // cell[1]['A'] reset then add back
        assertEquals(2, tbt.get(3, 'C'));
        assertEquals(4, tbt.get(2, 'C'));
        assertEquals(1, tbt.sheet[3][2].dependee.size()); // C2->C3 should not change


        tbt.set(1, 'A', 4);
        assertEquals(4, tbt.get(1, 'A'));
        assertEquals(4, tbt.get(3, 'C'));
        assertEquals(8, tbt.get(2, 'C'));
    }
}