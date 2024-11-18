package hash;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

/**
 * LintCode 2894, medium, tags: map, sort.
 * See LeetCode 884, 451.
 */
@SuppressWarnings("unused")
public class OrderByFreq {
    // let n=nums.length, k=number of unique elements
    // n+klgk, n
    static class Solution {
        public Map<Integer, List<Integer>> orderByFrequency(int[] nums) {
            Map<Integer, List<Integer>> res = new TreeMap<>(Comparator.reverseOrder());
            res.putAll(
                    Arrays.stream(nums).boxed().collect(groupingBy( // num -> count
                                    i -> i, collectingAndThen(counting(), Long::intValue)))
                            .entrySet().stream().collect(groupingBy( // count -> [nums]
                                    Entry::getValue, mapping(Entry::getKey, toList()))));
            return res;
        }
    }
}
