package string;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 68, hard, tags: string, array, simulation.
 * <p>
 * Given an array of strings words and a width maxWidth, format the text such that each line has exactly maxWidth
 * characters and is fully (left and right) justified.
 * <p>
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra
 * spaces ' ' when necessary so that each line has exactly maxWidth characters.
 * <p>
 * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line does
 * not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on
 * the right.
 * <p>
 * For the last line of text, it should be left-justified, and no extra space is inserted between words.
 * <p>
 * Note:
 * <p>
 * A word is defined as a character sequence consisting of non-space characters only.
 * Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
 * The input array words contains at least one word.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
 * Output:
 * [
 * "This    is    an",
 * "example  of text",
 * "justification.  "
 * ]
 * Example 2:
 * <p>
 * Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
 * Output:
 * [
 * "What   must   be",
 * "acknowledgment  ",
 * "shall be        "
 * ]
 * Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last line must
 * be left-justified instead of fully-justified.
 * Note that the second line is also left-justified because it contains only one word.
 * Example 3:
 * <p>
 * Input: words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.",
 * "Art","is","everything","else","we","do"], maxWidth = 20
 * Output:
 * [
 * "Science  is  what we",
 * "understand      well",
 * "enough to explain to",
 * "a  computer.  Art is",
 * "everything  else  we",
 * "do                  "
 * ]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= words.length <= 300, n
 * 1 <= words[i].length <= 20
 * words[i] consists of only English letters and symbols.
 * 1 <= maxWidth <= 100, w
 * words[i].length <= maxWidth
 */
@SuppressWarnings("unused")
public class TextJustification {
    // @sherlock321(abc): python, O(nw) time and space.
    static class Solution {
        public List<String> fullJustify(String[] words, int maxWidth) {
            List<String> res = new ArrayList<>();
            List<StringBuilder> cur = new ArrayList<>();
            int nLetters = 0;
            for (String word : words) {
                StringBuilder w = new StringBuilder(word);
                if (nLetters + w.length() + cur.size() > maxWidth) { // cannot fit in cur line
                    for (int i = 0; i < maxWidth - nLetters; i++) {
                        if (cur.size() == 1) cur.getFirst().append(" ");
                        else cur.get(i % (cur.size() - 1)).append(" ");
                    }
                    res.add(String.join("", cur));
                    cur = new ArrayList<>();
                    nLetters = 0;
                }
                cur.add(w);
                nLetters += w.length();
            }
            //%[flags:-,left justified][width]conversion
            res.add(String.format("%-" + maxWidth + "s", String.join(" ", cur)));
            return res;
        }
    }
}
