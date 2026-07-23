package stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OnlineStockSpanTest {

    @Test
    void testExample1Stack() {
        OnlineStockSpan.StockSpanner sp = new OnlineStockSpan.StockSpanner();
        assertEquals(1, sp.next(100));
        assertEquals(1, sp.next(80));
        assertEquals(1, sp.next(60));
        assertEquals(2, sp.next(70));
        assertEquals(1, sp.next(60));
        assertEquals(4, sp.next(75));
        assertEquals(6, sp.next(85));
    }

    @Test
    void testExample1DP() {
        OnlineStockSpan.StockSpannerDP sp = new OnlineStockSpan.StockSpannerDP();
        assertEquals(1, sp.next(100));
        assertEquals(1, sp.next(80));
        assertEquals(1, sp.next(60));
        assertEquals(2, sp.next(70));
        assertEquals(1, sp.next(60));
        assertEquals(4, sp.next(75));
        assertEquals(6, sp.next(85));
    }

    @Test
    void testAllIncreasing() {
        OnlineStockSpan.StockSpanner sp = new OnlineStockSpan.StockSpanner();
        assertEquals(1, sp.next(1));
        assertEquals(2, sp.next(2));
        assertEquals(3, sp.next(3));
        assertEquals(4, sp.next(4));
        assertEquals(5, sp.next(5));
    }

    @Test
    void testAllDecreasing() {
        OnlineStockSpan.StockSpanner sp = new OnlineStockSpan.StockSpanner();
        assertEquals(1, sp.next(5));
        assertEquals(1, sp.next(4));
        assertEquals(1, sp.next(3));
        assertEquals(1, sp.next(2));
        assertEquals(1, sp.next(1));
    }

    @Test
    void testAllSame() {
        OnlineStockSpan.StockSpanner sp = new OnlineStockSpan.StockSpanner();
        assertEquals(1, sp.next(10));
        assertEquals(2, sp.next(10));
        assertEquals(3, sp.next(10));
        assertEquals(4, sp.next(10));
        assertEquals(5, sp.next(10));
    }

    @Test
    void testValleyAndPeak() {
        OnlineStockSpan.StockSpannerDP sp = new OnlineStockSpan.StockSpannerDP();
        assertEquals(1, sp.next(10));
        assertEquals(1, sp.next(5));
        assertEquals(1, sp.next(3));
        assertEquals(1, sp.next(1));  // valley
        assertEquals(5, sp.next(15)); // peak spans all
    }

    @Test
    void testAlternating() {
        OnlineStockSpan.StockSpanner sp = new OnlineStockSpan.StockSpanner();
        assertEquals(1, sp.next(5));
        assertEquals(1, sp.next(3));
        assertEquals(3, sp.next(5));
        assertEquals(1, sp.next(3));
        assertEquals(5, sp.next(5));
        assertEquals(1, sp.next(3));
        assertEquals(7, sp.next(5));
    }

    @Test
    void testBoundaryValues() {
        OnlineStockSpan.StockSpannerDP sp = new OnlineStockSpan.StockSpannerDP();
        assertEquals(1, sp.next(1));       // minimum price
        assertEquals(2, sp.next(100000));  // maximum price spans all
    }
}
