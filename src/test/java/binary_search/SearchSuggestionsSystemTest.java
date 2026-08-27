package binary_search;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchSuggestionsSystemTest {

    @Test
    void testExample1() {
        String[] products = {"mobile", "mouse", "moneypot", "monitor", "mousepad"};
        List<List<String>> expected = List.of(
                List.of("mobile", "moneypot", "monitor"),
                List.of("mobile", "moneypot", "monitor"),
                List.of("mouse", "mousepad"),
                List.of("mouse", "mousepad"),
                List.of("mouse", "mousepad")
        );
        assertEquals(expected, SearchSuggestionsSystem.suggestedProducts(products, "mouse"));
    }

    @Test
    void testExample2() {
        String[] products = {"havana"};
        List<List<String>> expected = List.of(
                List.of("havana"),
                List.of("havana"),
                List.of("havana"),
                List.of("havana"),
                List.of("havana"),
                List.of("havana")
        );
        assertEquals(expected, SearchSuggestionsSystem.suggestedProducts(products, "havana"));
    }

    @Test
    void testExample3() {
        String[] products = {"bags", "baggage", "banner", "box", "cloths"};
        List<List<String>> expected = List.of(
                List.of("baggage", "bags", "banner"),
                List.of("baggage", "bags", "banner"),
                List.of("baggage", "bags"),
                List.of("bags")
        );
        assertEquals(expected, SearchSuggestionsSystem.suggestedProducts(products, "bags"));
    }

    @Test
    void testNoMatch() {
        String[] products = {"apple", "apricot", "banana"};
        List<List<String>> expected = List.of(
                List.of(),
                List.of()
        );
        assertEquals(expected, SearchSuggestionsSystem.suggestedProducts(products, "zz"));
    }

    @Test
    void testSingleChar() {
        String[] products = {"a", "ab", "abc", "abcd"};
        List<List<String>> expected = List.of(
                List.of("a", "ab", "abc")
        );
        assertEquals(expected, SearchSuggestionsSystem.suggestedProducts(products, "a"));
    }
}
