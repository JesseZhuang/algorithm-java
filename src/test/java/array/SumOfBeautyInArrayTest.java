package array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SumOfBeautyInArrayTest {

    @Test
    void lengthThreeCountsOnlyTheInteriorElement() {
        assertEquals(2, SumOfBeautyInArray.sumOfBeauties(new int[]{1, 2, 3}));
        assertEquals(0, SumOfBeautyInArray.sumOfBeauties(new int[]{3, 2, 1}));
    }

    @Test
    void increasingNeighborsEarnOneWhenTheGlobalConditionFails() {
        assertEquals(1, SumOfBeautyInArray.sumOfBeauties(new int[]{2, 4, 6, 4}));
        assertEquals(1, SumOfBeautyInArray.sumOfBeauties(new int[]{4, 2, 3, 5}));
    }

    @Test
    void globalBeautyTakesPrecedenceOverLocalBeauty() {
        assertEquals(4, SumOfBeautyInArray.sumOfBeauties(new int[]{1, 3, 4, 5}));
    }

    @Test
    void globalConditionChecksEveryElementOnBothSides() {
        assertEquals(1, SumOfBeautyInArray.sumOfBeauties(new int[]{9, 1, 4, 7, 2}));
        assertEquals(1, SumOfBeautyInArray.sumOfBeauties(new int[]{5, 1, 3, 6}));
        assertEquals(1, SumOfBeautyInArray.sumOfBeauties(new int[]{1, 3, 5, 2}));
    }

    @Test
    void equalValuesDoNotSatisfyStrictComparisons() {
        assertEquals(0, SumOfBeautyInArray.sumOfBeauties(new int[]{1, 2, 2, 3}));
        assertEquals(0, SumOfBeautyInArray.sumOfBeauties(new int[]{2, 2, 2}));
        assertEquals(1, SumOfBeautyInArray.sumOfBeauties(new int[]{3, 1, 3, 4}));
        assertEquals(1, SumOfBeautyInArray.sumOfBeauties(new int[]{1, 3, 4, 3}));
    }
}
