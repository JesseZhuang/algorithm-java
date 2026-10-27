package hash;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** LeetCode 2215. Find the distinct values present in only one of two arrays. */
public final class FindTheDifferenceOfTwoArrays {

    private FindTheDifferenceOfTwoArrays() {
    }

    /** O(n + m) time and O(n + m) space. */
    public static List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> first = new HashSet<>();
        Set<Integer> second = new HashSet<>();
        for (int num : nums1) {
            first.add(num);
        }
        for (int num : nums2) {
            second.add(num);
        }

        List<Integer> onlyFirst = new ArrayList<>();
        for (int num : first) {
            if (!second.contains(num)) {
                onlyFirst.add(num);
            }
        }
        List<Integer> onlySecond = new ArrayList<>();
        for (int num : second) {
            if (!first.contains(num)) {
                onlySecond.add(num);
            }
        }
        return List.of(onlyFirst, onlySecond);
    }
}
