package math;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HappyNumberTest {
    private static HappyNumber toBeTested;

    @BeforeAll
    static void setup(){
        toBeTested = new HappyNumber();
    }

    @ParameterizedTest(name = "{0} is happy : {1}")
    @CsvFileSource(resources = {"/HappyNumber.csv"}, delimiter = ' ', numLinesToSkip = 2)
    void testIsHappyNumber(int n, boolean isHappy) {
        assertEquals(toBeTested.isHappyNL(n), isHappy);
        assertEquals(toBeTested.isHappyNL2(n), isHappy);
        assertEquals(toBeTested.isHappySlowFastPt(n), isHappy);
    }
}
