package binary_search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 1268, medium, tags: array, string, binary search, sorting, trie.
 * <p>
 * You are given an array of strings products and a string searchWord. Design a system that suggests at most three
 * product names from products after each character of searchWord is typed. Suggested products should have common prefix
 * with searchWord. If there are more than three products with a common prefix return the three lexicographically
 * minimum products.
 * <p>
 * Return a list of lists of the suggested products after each character of searchWord is typed.
 * <p>
 * Example 1:
 * Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
 * Output: [["mobile","moneypot","monitor"],["mobile","moneypot","monitor"],["mouse","mousepad"],
 * ["mouse","mousepad"],["mouse","mousepad"]]
 * <p>
 * Example 2:
 * Input: products = ["havana"], searchWord = "havana"
 * Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
 * <p>
 * Example 3:
 * Input: products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
 * Output: [["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
 * <p>
 * Constraints:
 * 1 <= products.length <= 1000
 * 1 <= products[i].length <= 3000
 * 1 <= sum(products[i].length) <= 2 * 10^4
 * All the strings of products are unique.
 * 1 <= searchWord.length <= 1000
 * All input consists of lowercase English letters.
 */
public final class SearchSuggestionsSystem {

    private SearchSuggestionsSystem() {
    }

    /**
     * Sort + Binary Search. O(n log n + m*L*log n) time, O(sort) space.
     * n = products.length, m = searchWord.length, L = average product length for comparison.
     */
    public static List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        List<List<String>> result = new ArrayList<>();
        String prefix = "";
        int lo = 0, hi = products.length;
        for (int i = 0; i < searchWord.length(); i++) {
            prefix = prefix + searchWord.charAt(i);
            // narrow lower bound
            lo = lowerBound(products, lo, hi, prefix);
            List<String> suggestions = new ArrayList<>();
            for (int j = lo; j < Math.min(lo + 3, hi); j++) {
                if (products[j].startsWith(prefix)) {
                    suggestions.add(products[j]);
                } else {
                    break;
                }
            }
            result.add(suggestions);
        }
        return result;
    }

    /**
     * Find the leftmost index in [lo, hi) where products[index] >= prefix.
     */
    private static int lowerBound(String[] products, int lo, int hi, String prefix) {
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (products[mid].compareTo(prefix) < 0) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
}
