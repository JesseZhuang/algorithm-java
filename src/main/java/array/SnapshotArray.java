package array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * LeetCode 1146, medium, tags: array, hash table, binary search, design.
 * Implement a SnapshotArray that supports the following interface:
 * <p>
 * SnapshotArray(int length) initializes an array-like data structure with the given length. Initially, each
 * element equals 0.
 * void set(index, val) sets the element at the given index to be equal to val.
 * int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
 * int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id
 * <p>
 * Example 1:
 * <p>
 * Input: ["SnapshotArray","set","snap","set","get"]
 * [[3],[0,5],[],[0,6],[0,0]]
 * Output: [null,null,0,null,5]
 * Explanation:
 * SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
 * snapshotArr.set(0,5);  // Set array[0] = 5
 * snapshotArr.snap();  // Take a snapshot, return snap_id = 0
 * snapshotArr.set(0,6);
 * snapshotArr.get(0,0);  // Get the value of array[0] with snap_id = 0, return 5
 * <p>
 * Constraints:
 * <p>
 * 1 <= length <= 5 * 104
 * 0 <= index < length
 * 0 <= val <= 109
 * 0 <= snap_id < (the total number of times we call snap())
 * At most 5 * 104 calls will be made to set, snap, and get.
 * <p>
 * Hint: Use a list of lists, adding both the element and the snap_id to each index.
 */
public class SnapshotArray { // 117 ms, 75.9 Mb.
    // treemap[] snapId, val version, 54ms, 76.4 Mb. O(log(set)) for set()
    // Note that we can achieve O(1) complexity for the get operation, but it won't be memory-efficient
    // if we populate all indices and then make a lot of snaps (without modifying much).
    List<int[]>[] data;
    int snapId;

    public SnapshotArray(int length) { // O(n)
        data = new List[length];
        for (int i = 0; i < length; i++) {
            data[i] = new ArrayList<>();
            data[i].add(new int[]{0, 0}); // [snap_id, val]
        }
        snapId = 0;
    }

    public void set(int index, int val) {// can only set val in the current snapshot, O(1)
        if (data[index].get(data[index].size() - 1)[0] == snapId)
            data[index].get(data[index].size() - 1)[1] = val;
        else data[index].add(new int[]{snapId, val}); // lazy
    }

    public int snap() { // O(1)
        return snapId++;
    }

    public int get(int index, int snap_id) { // O(log(set))
        int id = Collections.binarySearch(data[index], new int[]{snap_id, 0}, Comparator.comparingInt(a -> a[0]));
        if (id < 0) id = ~id - 1; // possible that multiple snapshots taken without setting new value
        return data[index].get(id)[1];
    }
}
