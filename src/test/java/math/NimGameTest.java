package math;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NimGameTest {
    private static NimGame tbt;

    @BeforeAll
    static void setup(){
        tbt = new NimGame();
    }

    @ParameterizedTest(name = "{0} stones, play 1 can win: {1}")
    @CsvFileSource(resources = "/NimGame.csv", numLinesToSkip = 2, delimiter = ' ')
    void testCanWin(int n, boolean canWin) {
        assertEquals(canWin, tbt.canWinNim(n));
    }
}
