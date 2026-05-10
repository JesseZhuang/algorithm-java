package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class PartitionLabels {
    private PartitionLabels() {}

    public static class Solution {
        public List<Integer> partitionLabels(String s) {
            int[] last = new int[26]; // O(1) space
            for (int i = 0; i < s.length(); i++) { // O(n)
                last[s.charAt(i) - 'a'] = i;
            }
            List<Integer> result = new ArrayList<>();
            int start = 0, end = 0;
            for (int i = 0; i < s.length(); i++) { // O(n)
                end = Math.max(end, last[s.charAt(i) - 'a']);
                if (i == end) {
                    result.add(end - start + 1);
                    start = end + 1;
                }
            }
            return result;
        }
    }

    public static class Solution2 {
        public List<Integer> partitionLabels(String s) {
            int[] first = new int[26];
            int[] lastIdx = new int[26];
            Arrays.fill(first, -1);
            for (int i = 0; i < s.length(); i++) { // O(n)
                int c = s.charAt(i) - 'a';
                if (first[c] == -1) first[c] = i;
                lastIdx[c] = i;
            }
            // Build intervals for each character that appears
            int[][] intervals = new int[26][2];
            int count = 0;
            for (int c = 0; c < 26; c++) { // O(26) = O(1)
                if (first[c] != -1) {
                    intervals[count][0] = first[c];
                    intervals[count][1] = lastIdx[c];
                    count++;
                }
            }
            Arrays.sort(intervals, 0, count, (a, b) -> a[0] - b[0]); // O(26 log 26) = O(1)
            // Merge intervals
            List<Integer> result = new ArrayList<>();
            int start = intervals[0][0], end = intervals[0][1];
            for (int i = 1; i < count; i++) { // O(26) = O(1)
                if (intervals[i][0] <= end) {
                    end = Math.max(end, intervals[i][1]);
                } else {
                    result.add(end - start + 1);
                    start = intervals[i][0];
                    end = intervals[i][1];
                }
            }
            result.add(end - start + 1);
            return result;
        }
    }
}
