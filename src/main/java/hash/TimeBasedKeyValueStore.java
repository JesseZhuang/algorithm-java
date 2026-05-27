package hash;

import java.util.*;

/**
 * LeetCode 981, medium, tags: hash table, string, binary search, design.
 * <p>
 * Design a time-based key-value data structure that can store multiple values for the same key at different
 * time stamps and retrieve the key's value at a certain timestamp.
 * <p>
 * Implement the TimeMap class:
 * <p>
 * TimeMap() Initializes the object of the data structure.
 * void set(String key, String value, int timestamp) Stores the key key with the value value at the given
 * time timestamp.
 * String get(String key, int timestamp) Returns a value such that set was called previously, with
 * timestamp_prev <= timestamp. If there are multiple such values, it returns the value associated with
 * the largest timestamp_prev. If there are no values, it returns "".
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["TimeMap", "set", "get", "get", "set", "get", "get"]
 * [[], ["foo", "bar", 1], ["foo", 1], ["foo", 3], ["foo", "bar2", 4], ["foo", 4], ["foo", 5]]
 * Output
 * [null, null, "bar", "bar", null, "bar2", "bar2"]
 * <p>
 * Constraints:
 * <p>
 * 1 <= key.length, value.length <= 100
 * key and value consist of lowercase English letters and digits.
 * 1 <= timestamp <= 10^7
 * All the timestamps timestamp of set are strictly increasing.
 * At most 2 * 10^5 calls will be made to set and get.
 */
public final class TimeBasedKeyValueStore {
    private TimeBasedKeyValueStore() {}

    /**
     * Solution 1: HashMap + TreeMap. set: O(1) amortized (O(log n) for TreeMap put), get: O(log n).
     * Space: O(n) where n is total number of set calls.
     */
    static class TimeMap1 {
        private final Map<String, TreeMap<Integer, String>> map;

        public TimeMap1() {
            map = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            map.computeIfAbsent(key, k -> new TreeMap<>()).put(timestamp, value);
        }

        public String get(String key, int timestamp) {
            TreeMap<Integer, String> treeMap = map.get(key);
            if (treeMap == null) return "";
            Map.Entry<Integer, String> entry = treeMap.floorEntry(timestamp);
            return entry == null ? "" : entry.getValue();
        }
    }

    /**
     * Solution 2: HashMap + ArrayList with binary search. set: O(1), get: O(log n).
     * Space: O(n). Takes advantage of the constraint that timestamps are strictly increasing
     * for the same key, so the list is already sorted.
     */
    static class TimeMap2 {
        private final Map<String, List<int[]>> timestamps; // key -> list of [timestamp]
        private final Map<String, List<String>> values;    // key -> list of values

        public TimeMap2() {
            timestamps = new HashMap<>();
            values = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            timestamps.computeIfAbsent(key, k -> new ArrayList<>()).add(new int[]{timestamp});
            values.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
        }

        public String get(String key, int timestamp) {
            List<int[]> ts = timestamps.get(key);
            if (ts == null || ts.isEmpty()) return "";
            // binary search for largest timestamp_prev <= timestamp
            int lo = 0, hi = ts.size() - 1, res = -1;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                if (ts.get(mid)[0] <= timestamp) {
                    res = mid;
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
            return res == -1 ? "" : values.get(key).get(res);
        }
    }
}
