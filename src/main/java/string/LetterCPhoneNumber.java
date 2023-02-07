package string;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * LeetCode 17, medium, tags: hash table, string, backtracking.
 * <p>
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could
 * represent. Return the answer in any order.
 * <p>
 * A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to
 * any letters.
 * <p>
 * Example 1: https://assets.leetcode.com/uploads/2022/03/15/1200px-telephone-keypad2svg.png
 * <p>
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * Example 2:
 * <p>
 * Input: digits = ""
 * Output: []
 * Example 3:
 * <p>
 * Input: digits = "2"
 * Output: ["a","b","c"]
 * <p>
 * Constraints:
 * <p>
 * 0 <= digits.length <= 4
 * digits[i] is a digit in the range ['2', '9'].
 */
public class LetterCPhoneNumber {
    // 2ms, 40.8Mb. O(3^n) time and space.
    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) return new ArrayList<>(); // expected [] not [""]
        List<StringBuilder> res = new ArrayList<>();
        res.add(new StringBuilder());
        char[][] map = {{'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}, {'j', 'k', 'l'}, {'m', 'n', 'o'},
                {'p', 'q', 'r', 's'}, {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};
        for (int i = 0; i < digits.length(); i++) {
            List<StringBuilder> temp = new ArrayList<>();
            for (StringBuilder sb : res)
                for (char c : map[digits.charAt(i) - '2'])
                    temp.add(new StringBuilder(sb).append(c)); // must copy, cannot directly append
            res = temp;
        }
        return res.stream().map(sb -> sb.toString()).collect(Collectors.toList());
    }

    // use a queue, another method is recursive
    public List<String> letterCombinationsQ(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        if (digits.isEmpty()) return ans;
        String[] mapping = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        while (ans.peek().length() != digits.length()) {
            String remove = ans.remove();
            String map = mapping[digits.charAt(remove.length()) - '0'];
            for (char c : map.toCharArray()) ans.addLast(remove + c);
        }
        return ans;
    }
}
