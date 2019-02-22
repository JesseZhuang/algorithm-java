package math;

/**
 * LeetCode 292. Easy.
 * <p>
 * You are playing the following Nim Game with your friend: There is a heap of stones on the table,
 * each time one of you take turns to remove 1 to 3 stones. The one who removes the last stone will be the winner.
 * You will take the first turn to remove the stones.
 * <p>
 * Both of you are very clever and have optimal strategies for the game. Write a function to determine
 * whether you can win the game given the number of stones in the heap.
 * <p>
 * Example:
 * <p>
 * Input: 4
 * Output: false
 * <p>
 * Explanation: If there are 4 stones in the heap, then you will never win the game;
 * No matter 1, 2, or 3 stones you remove, the last stone will always be
 * removed by your friend.
 * <p>
 * <b>Summary</b>:
 * <p>
 * <ul>
 * <li>Brain Teaser, O(1) time, O(1) space.
 * </ul>
 */
public class NimGame {
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}
