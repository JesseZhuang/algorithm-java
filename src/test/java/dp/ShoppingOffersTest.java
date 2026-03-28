package dp;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShoppingOffersTest {

    private final Solution1 sol1 = new Solution1();
    private final Solution2 sol2 = new Solution2();

    private void verify(List<Integer> price, List<List<Integer>> special, List<Integer> needs, int expected) {
        assertEquals(expected, sol1.shoppingOffers(price, special, needs));
        assertEquals(expected, sol2.shoppingOffers(price, special, needs));
    }

    @Test
    void example1() {
        verify(Arrays.asList(2, 5), Arrays.asList(Arrays.asList(3, 0, 5), Arrays.asList(1, 2, 10)),
                Arrays.asList(3, 2), 14);
    }

    @Test
    void example2() {
        verify(Arrays.asList(2, 3, 4), Arrays.asList(Arrays.asList(1, 1, 0, 4), Arrays.asList(2, 2, 1, 9)),
                Arrays.asList(1, 2, 1), 11);
    }

    @Test
    void noOffers() {
        verify(Arrays.asList(1, 2, 3), List.of(), Arrays.asList(3, 2, 1), 10);
    }

    @Test
    void noNeeds() {
        verify(Arrays.asList(10, 20), List.of(Arrays.asList(1, 1, 5)), Arrays.asList(0, 0), 0);
    }

    @Test
    void singleItem() {
        verify(List.of(5), List.of(Arrays.asList(2, 8)), List.of(4), 16);
    }

    @Test
    void offerWorseThanIndividual() {
        verify(Arrays.asList(1, 1), List.of(Arrays.asList(1, 1, 5)), Arrays.asList(2, 2), 4);
    }

    @Test
    void multipleSameOffer() {
        verify(Arrays.asList(4, 3), List.of(Arrays.asList(2, 1, 5)), Arrays.asList(4, 2), 10);
    }

    @Test
    void offerExactMatch() {
        verify(Arrays.asList(10, 10), List.of(Arrays.asList(1, 1, 1)), Arrays.asList(1, 1), 1);
    }

    @Test
    void largeNeedsSingleItem() {
        verify(List.of(3), List.of(Arrays.asList(2, 5)), List.of(6), 15);
    }

    @Test
    void mixedOffers() {
        verify(Arrays.asList(2, 3),
                Arrays.asList(Arrays.asList(1, 0, 1), Arrays.asList(0, 1, 2), Arrays.asList(1, 1, 3)),
                Arrays.asList(2, 2), 6);
    }

    @Test
    void needsOneEach() {
        verify(Arrays.asList(5, 5, 5), List.of(Arrays.asList(1, 1, 1, 10)), Arrays.asList(1, 1, 1), 10);
    }
}
