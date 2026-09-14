package sliding;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 904, medium, tags: array, hash table, sliding window.
 * <p>
 * You are visiting a farm that has a single row of fruit trees arranged from left to right.
 * The trees are represented by an integer array fruits where fruits[i] is the type of fruit
 * the ith tree produces.
 * <p>
 * You want to collect as much fruit as possible. However, the owner has some strict rules:
 * You only have two baskets, and each basket can only hold a single type of fruit.
 * There is no limit on the amount of fruit each basket can hold.
 * Starting from any tree of your choice, you must pick exactly one fruit from every tree
 * (including the start tree) while moving to the right. You must stop when you encounter
 * a tree with fruit that cannot fit in either basket (i.e., a third distinct type).
 * <p>
 * Given the integer array fruits, return the maximum number of fruits you can pick.
 * <p>
 * Example 1:
 * <p>
 * Input: fruits = [1,2,1]
 * Output: 3
 * Explanation: We can pick from all 3 trees.
 * <p>
 * Example 2:
 * <p>
 * Input: fruits = [0,1,2,2]
 * Output: 3
 * Explanation: We can pick from trees [1,2,2]. If we started at tree 0, we would only pick [0,1].
 * <p>
 * Example 3:
 * <p>
 * Input: fruits = [1,2,3,2,2]
 * Output: 4
 * Explanation: We can pick from trees [2,3,2,2]. If we started at tree 0, we would only pick [1,2].
 * <p>
 * Constraints:
 * <p>
 * 1 <= fruits.length <= 10^5
 * 0 <= fruits[i] < fruits.length
 */
@SuppressWarnings("unused")
public final class FruitIntoBaskets {

    private FruitIntoBaskets() {
    }

    // solution 1, sliding window + HashMap. O(n) time, O(1) space (at most 3 keys in map).
    public static int totalFruit(int[] fruits) {
        Map<Integer, Integer> count = new HashMap<>(); // fruit type -> count in window
        int left = 0, res = 0;
        for (int right = 0; right < fruits.length; right++) { // O(n), each element enters window once
            count.merge(fruits[right], 1, Integer::sum); // add right fruit to window
            while (count.size() > 2) { // shrink left until at most 2 distinct types
                int lf = fruits[left];
                count.merge(lf, -1, Integer::sum); // decrement count for left fruit
                if (count.get(lf) == 0) count.remove(lf); // remove type when count drops to 0
                left++; // O(n) total across all iterations, each element exits window once
            }
            res = Math.max(res, right - left + 1); // update max window size
        }
        return res;
    }

    // solution 2, track last two types. O(n) time, O(1) space.
    public static int totalFruit2(int[] fruits) {
        int a = -1, b = -1; // a: older basket type, b: most recent basket type
        int lastRun = 0; // length of latest consecutive run of same fruit ending at i-1
        int cur = 0, res = 0; // cur: current window length, res: max window length
        for (int i = 0; i < fruits.length; i++) { // O(n), single pass
            if (fruits[i] == a || fruits[i] == b) {
                cur++; // same type as a or b, extend window
            } else {
                cur = lastRun + 1; // third type: reset to [last consecutive run] + [new fruit]
            }
            // update lastRun: consecutive run length ending at position i
            lastRun = (i > 0 && fruits[i] == fruits[i - 1]) ? lastRun + 1 : 1;
            if (fruits[i] != b) { // new type seen, rotate baskets
                a = b; // old b becomes a
                b = fruits[i]; // current fruit becomes b (most recent)
            }
            res = Math.max(res, cur); // update max window size
        }
        return res;
    }
}
