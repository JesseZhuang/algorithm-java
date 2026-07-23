package stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode 901, medium, tags: stack, design, monotonic stack, data stream.
 * <p>
 * Design an algorithm that collects daily price quotes for some stock and returns the span of that stock's price
 * for the current day.
 * <p>
 * The span of the stock's price in one day is the maximum number of consecutive days (starting from that day and
 * going backward) for which the stock price was less than or equal to that day's price.
 * <p>
 * For example, if the prices of the stock in the last four days is [7,2,1,2] and the price of the stock today is 2,
 * then the span of today is 4 because starting from today, the price of the stock was less than or equal 2 for 4
 * consecutive days.
 * Also, if the prices of the stock in the last four days is [7,34,1,2] and the price of the stock today is 8, then
 * the span of today is 3 because starting from today, the price of the stock was less than or equal 8 for 3
 * consecutive days.
 * <p>
 * Implement the StockSpanner class:
 * <p>
 * StockSpanner() Initializes the object of the class.
 * int next(int price) Returns the span of the stock's price given that today's price is price.
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["StockSpanner", "next", "next", "next", "next", "next", "next", "next"]
 * [[], [100], [80], [60], [70], [60], [75], [85]]
 * Output
 * [null, 1, 1, 1, 2, 1, 4, 6]
 * <p>
 * Constraints:
 * <p>
 * 1 <= price <= 10^5
 * At most 10^4 calls will be made to next.
 */
@SuppressWarnings("unused")
public class OnlineStockSpan {

    /**
     * Monotonic stack approach. Stack stores (price, span) pairs.
     * Time O(1) amortized per call, Space O(n) for the stack.
     */
    public static class StockSpanner {
        private final Deque<int[]> stack; // each element: [price, span]

        public StockSpanner() {
            stack = new ArrayDeque<>();
        }

        public int next(int price) {
            int span = 1;
            while (!stack.isEmpty() && stack.peek()[0] <= price) { // pop while top price <= current
                span += stack.pop()[1]; // accumulate spans, O(1) amortized
            }
            stack.push(new int[]{price, span}); // push (price, accumulated_span)
            return span;
        }
    }

    /**
     * DP jump-back approach. Store prices and spans in lists; jump backwards using spans to skip.
     * Time O(n) worst case per call, Space O(n).
     */
    public static class StockSpannerDP {
        private final List<Integer> prices;
        private final List<Integer> spans;

        public StockSpannerDP() {
            prices = new ArrayList<>();
            spans = new ArrayList<>();
        }

        public int next(int price) {
            int span = 1;
            int i = prices.size() - 1;
            while (i >= 0 && prices.get(i) <= price) { // jump back using spans
                span += spans.get(i);
                i -= spans.get(i); // skip over already-computed span
            }
            prices.add(price);
            spans.add(span);
            return span;
        }
    }
}
