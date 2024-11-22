package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Companies: LTK.
 */
@SuppressWarnings("unused")
public class ElevatorPath {
    // nlgn, n+sort.
    static class Solution {
        public List<Integer> closestFloors(List<Integer> floors, int start) {
            Collections.sort(floors);
            int idx = ~Collections.binarySearch(floors, start), l = 0, r = floors.size() - 1;
            List<Integer> lower = floors.subList(0, idx), higher = floors.subList(idx, r + 1);
            Collections.reverse(lower);
            List<Integer> res = new ArrayList<>();
            if (idx - l <= r - idx) {
                res.addAll(lower);
                res.addAll(higher);
            } else {
                res.addAll(higher);
                res.addAll(lower);
            }
            return res;
        }
    }
}
