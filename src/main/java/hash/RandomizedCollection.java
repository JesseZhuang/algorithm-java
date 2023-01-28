package hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * LeetCode 381, hard, tags: array, hash table, math, design, randomized.
 * RandomizedCollection is a data structure that contains a collection of numbers, possibly duplicates
 * (i.e., a multiset). It should support inserting and removing specific elements and also reporting a random element.
 * <p>
 * Implement the RandomizedCollection class:
 * <p>
 * RandomizedCollection() Initializes the empty RandomizedCollection object.
 * bool insert(int val) Inserts an item val into the multiset, even if the item is already present. Returns true if the
 * item is not present, false otherwise.
 * bool remove(int val) Removes an item val from the multiset if present. Returns true if the item is present, false
 * otherwise. Note that if val has multiple occurrences in the multiset, we only remove one of them.
 * int getRandom() Returns a random element from the current multiset of elements. The probability of each element
 * being returned is linearly related to the number of the same values the multiset contains.
 * You must implement the functions of the class such that each function works on average O(1) time complexity.
 * <p>
 * Note: The test cases are generated such that getRandom will only be called if there is at least one item in
 * the RandomizedCollection.
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["RandomizedCollection", "insert", "insert", "insert", "getRandom", "remove", "getRandom"]
 * [[], [1], [1], [2], [], [1], []]
 * Output
 * [null, true, false, true, 2, true, 1]
 * <pre>
 * Explanation
 * RandomizedCollection randomizedCollection = new RandomizedCollection();
 * randomizedCollection.insert(1);   // return true since the collection does not contain 1.
 * // Inserts 1 into the collection.
 * randomizedCollection.insert(1);   // return false since the collection contains 1.
 * // Inserts another 1 into the collection. Collection now contains [1,1].
 * randomizedCollection.insert(2);   // return true since the collection does not contain 2.
 * // Inserts 2 into the collection. Collection now contains [1,1,2].
 * randomizedCollection.getRandom(); // getRandom should:
 * // - return 1 with probability 2/3, or
 * // - return 2 with probability 1/3.
 * randomizedCollection.remove(1);   // return true since the collection contains 1.
 * // Removes 1 from the collection. Collection now contains [1,2].
 * randomizedCollection.getRandom(); // getRandom should return 1 or 2, both equally likely.
 * </pre>
 * <p>
 * Constraints:
 * <p>
 * -231 <= val <= 231 - 1
 * At most 2 * 105 calls in total will be made to insert, remove, and getRandom.
 * There will be at least one element in the data structure when getRandom is called.
 * <p>
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
public class RandomizedCollection {
    // 92 ms 108.4 Mb for keeping a count for the keys version. remove is O(N) though.
    // 36 ms 84.2 Mb. LinkedHashSet version.
    Map<Integer, Set<Integer>> indexes;
    List<Integer> data; // amortize O(1)
    Random r;

    public RandomizedCollection() {
        indexes = new HashMap<>();
        data = new ArrayList<>();
        r = new Random();
    }

    public boolean insert(int val) { // hashset cannot pick one to remove in O(1), list remove is O(N)
        if (!indexes.containsKey(val)) indexes.put(val, new LinkedHashSet<>());
        indexes.get(val).add(data.size());
        data.add(val);
        return indexes.get(val).size() == 1;
    }

    public boolean remove(int val) {
        if (!indexes.containsKey(val)) return false;
        int removeId = indexes.get(val).iterator().next(); // iterator key set cached and maintained, O(1)
        indexes.get(val).remove(removeId);
        int lastId = data.size() - 1;
        int last = data.get(lastId);
        data.set(removeId, last);
        indexes.get(last).add(removeId); // must be before remove: removeId possible == lastId
        indexes.get(last).remove(lastId);
        data.remove(lastId);
        if (indexes.get(val).isEmpty()) indexes.remove(val);
        return true;
    }

    public int getRandom() {
        return data.get(r.nextInt(data.size()));
    }

    public static void main(String[] args) {
        RandomizedCollection rc = new RandomizedCollection();
        System.out.println(rc.insert(0));
        System.out.println(rc.remove(0));
        System.out.println(rc.insert(-1));
        System.out.println(rc.remove(0));
        System.out.println(rc.getRandom());
    }
}

// 37 ms, 90.8Mb.
class RandomizedCollection2 {
    private Map<Integer, List<Integer>> map; // val, {ids}
    private List<int[]> nums; // [val, index]
    private ThreadLocalRandom rnd;

    public RandomizedCollection2() {
        map = new HashMap<>();
        nums = new ArrayList<>();
        rnd = ThreadLocalRandom.current();
    }

    public boolean insert(int val) {
        if (!map.containsKey(val)) map.put(val, new ArrayList<>());
        map.get(val).add(nums.size());
        nums.add(new int[]{val, map.get(val).size() - 1});
        return map.get(val).size() == 1;
    }

    public boolean remove(int val) {
        boolean res = map.containsKey(val);
        if (res) {
            List<Integer> ids = map.get(val);
            int vLastId = ids.get(ids.size() - 1);
            int[] swapNum = nums.get(nums.size() - 1);
            int swapVal = swapNum[0], swapIndex = swapNum[1];
            map.get(swapVal).set(swapIndex, vLastId);
            nums.set(vLastId, swapNum);
            if (ids.size() == 1) map.remove(val);
            else ids.remove(ids.size() - 1);
            nums.remove(nums.size() - 1);
        }
        return res;
    }

    public int getRandom() {
        return nums.get(rnd.nextInt(nums.size()))[0];
    }
}