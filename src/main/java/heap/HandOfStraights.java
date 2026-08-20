package heap;

import java.util.*;

/**
 * LeetCode 846, medium, tags: array, hash table, greedy, sorting.
 * <p>
 * Alice has some number of cards in her hand. She wants to rearrange the cards into groups so that each group
 * is of size groupSize, and consists of groupSize consecutive cards.
 * <p>
 * Given an integer array hand where hand[i] is the value written on the ith card and an integer groupSize,
 * return true if she can rearrange the cards, or false otherwise.
 * <p>
 * Constraints: 1 <= hand.length <= 10^4, 0 <= hand[i] <= 10^9, 1 <= groupSize <= hand.length.
 */
public final class HandOfStraights {
    private HandOfStraights() {
    }

    /**
     * Solution 1: Greedy with TreeMap. Always pick the smallest available card as the start of a group,
     * then decrement counts for the next groupSize-1 consecutive cards.
     * O(n log n) time, O(n) space.
     */
    public static boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) return false; // n not divisible by groupSize
        TreeMap<Integer, Integer> count = new TreeMap<>();
        for (int card : hand) count.merge(card, 1, Integer::sum); // O(n log n)
        while (!count.isEmpty()) {
            int first = count.firstKey(); // smallest card, O(log n)
            for (int i = first; i < first + groupSize; i++) {
                Integer cnt = count.get(i);
                if (cnt == null) return false; // gap found
                if (cnt == 1) count.remove(i);
                else count.put(i, cnt - 1);
            }
        }
        return true;
    }

    /**
     * Solution 2: Greedy with sorting + HashMap. Sort the hand, iterate and start groups from the
     * smallest unused card.
     * O(n log n) time, O(n) space.
     */
    public static boolean isNStraightHand2(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) return false;
        Arrays.sort(hand); // O(n log n)
        Map<Integer, Integer> count = new HashMap<>();
        for (int card : hand) count.merge(card, 1, Integer::sum);
        for (int card : hand) { // iterate sorted order
            if (count.getOrDefault(card, 0) == 0) continue; // already used
            for (int i = card; i < card + groupSize; i++) {
                int cnt = count.getOrDefault(i, 0);
                if (cnt == 0) return false; // gap or exhausted
                count.put(i, cnt - 1);
            }
        }
        return true;
    }
}
