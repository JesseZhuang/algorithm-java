package array;

/**
 * LeetCode 408, LintCode 637, easy, tags: array, two pointers, math, string.
 * <p>
 * Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.
 * <p>
 * A string such as "word" contains only the following valid abbreviations:
 * <p>
 * ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 * Notice that only the above abbreviations are valid abbreviations of the string "word".
 * Any other string is not a valid abbreviation of "word".
 * <p>
 * Note:
 * Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.
 * <p>
 * Example 1:
 * Given s = "internationalization", abbr = "i12iz4n":
 * <p>
 * Return true.
 * Example 2:
 * Given s = "apple", abbr = "a2e":
 * <p>
 * Return false.
 * <p>
 * Where 1 means one character is omitted, 2 means two characters are omitted, and so on.
 * Notice that only the above abbreviations are valid abbreviations of the string word.
 * Any other string is not a valid abbreviation of word.
 */
public class ValidWordAbbr {
    // solution 1, lint code 324ms, 22.25Mb. n time, 1 space.
    public boolean validWordAbbreviation(String word, String abbr) {
        int i = 0, j = 0;
        while (i < word.length() && j < abbr.length()) {
            if (word.charAt(i) == abbr.charAt(j)) {
                ++i;
                ++j;
            } else if (Character.isDigit(abbr.charAt(j)) && abbr.charAt(j) != '0') {
                int num = 0;
                while (j < abbr.length() && Character.isDigit(abbr.charAt(j))) {
                    num = num * 10 + abbr.charAt(j) - '0';
                    ++j;
                }
                i += num; // do not forget
            } else return false;
        }
        return i == word.length() && j == abbr.length();
    }
}
