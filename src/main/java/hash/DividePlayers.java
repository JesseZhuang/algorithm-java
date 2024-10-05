package hash;

import java.util.HashMap;

/**
 * LeetCode 2491, medium, tags: array, hash table, two pointers, sorting.
 * <p>
 * You are given a positive integer array skill of even length n where skill[i] denotes the skill of the ith player.
 * Divide the players into n / 2 teams of size 2 such that the total skill of each team is equal.
 * <p>
 * The chemistry of a team is equal to the product of the skills of the players on that team.
 * <p>
 * Return the sum of the chemistry of all the teams, or return -1 if there is no way to divide the players into
 * teams such that the total skill of each team is equal.
 * <p>
 * Example 1:
 * <p>
 * Input: skill = [3,2,5,1,3,4]
 * Output: 22
 * Explanation:
 * Divide the players into the following teams: (1, 5), (2, 4), (3, 3), where each team has a total skill of 6.
 * The sum of the chemistry of all the teams is: 1 * 5 + 2 * 4 + 3 * 3 = 5 + 8 + 9 = 22.
 * Example 2:
 * <p>
 * Input: skill = [3,4]
 * Output: 12
 * Explanation:
 * The two players form a team with a total skill of 7.
 * The chemistry of the team is 3 * 4 = 12.
 * Example 3:
 * <p>
 * Input: skill = [1,1,2,3]
 * Output: -1
 * Explanation:
 * There is no way to divide the players into teams such that the total skill of each team is equal.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= skill.length <= 10^5, n
 * skill.length is even.
 * 1 <= skill[i] <= 1000, k
 * <p>
 * Hint 1
 * Try sorting the skill array.
 * Hint 2
 * It is always optimal to pair the weakest available player with the strongest available player.
 */
@SuppressWarnings("unused")
public class DividePlayers {
    // frequency map(array or hashmap), n, k.
    // sorting nlgn, lgn(sort).
    static class Solution {
        public long dividePlayers(int[] skill) {
            int n = skill.length, skillSum = 0;
            HashMap<Integer, Integer> skillCnt = new HashMap<>();
//            int[] skillCnt = new int[1001];
            for (int s : skill) {
                skillSum += s;
                skillCnt.put(s, skillCnt.getOrDefault(s, 0) + 1);
            }
            if (skillSum % (n / 2) != 0) return -1;
            int teamSkill = skillSum / (n / 2);
            long res = 0;
            for (int s : skill) {
                int other = teamSkill - s;
                if (skillCnt.get(other) == 0) return -1;
                res += (long) s * (long) other;
                skillCnt.merge(other, -1, Integer::sum); // set to -1 if null
            }
            return res / 2; // each pair counted twice
        }
    }
}
