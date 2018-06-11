package dp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import util.IntegerArrayConverter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BuySellStockTest {

    private static BuySellStock buySellStock;

    @BeforeAll
    static void setup() {
        buySellStock = new BuySellStock();
    }

    @ParameterizedTest(name = "max profit {0} = {1}")
    @CsvFileSource(resources = {"/BuySellStock.csv"}, delimiter = ' ', numLinesToSkip = 2)
        // Junit does not recognize this test if using int[] prices
    void testMaxProfit(@ConvertWith(IntegerArrayConverter.class) Integer[] prices, int profit) {
        int[] intArray = (prices.length == 0) ? new int[]{} : IntegerArrayConverter.unboxIntegerArray(prices);
        assertEquals(buySellStock.maxProfitPeakArray(intArray), profit);
        assertEquals(buySellStock.maxProfitKadane(intArray), profit);
        assertEquals(buySellStock.maxProfitMinPrice(intArray), profit);
    }
}
