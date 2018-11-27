package string;

/**
 * LeetCode 58. Easy.
 * <p>
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ',
 * return the length of last word in the string. If the last word does not exist, return 0.
 * <p>
 * Note: A word is defined as a character sequence consists of non-space
 * characters only. For example, Given s = "Hello World", return 5.
 * <p>
 * <b>Summary:</b>
 * <p>
 * <ul>
 * <li>locate first letter, run backwards, O(n) time, O(1) space, if use char
 * array, might be O(n) space.
 * <li>use trim, lastIndexOf space char, O(m+n) time, m number of spaces to
 * remove.
 * <li>algorithm 4 update count in each i decrement, can be avoided. But should
 * judge whether char is space or not instead of letter.
 * </ul>
 */
public class LengthLastWord {

    public int lengthOfLastWord5(String s) {
        int count = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != 32) {// ascii table 32 is space
                count = i--;
                while (i >= 0 && !Character.isWhitespace(s.charAt(i))) i--;
                return count - i;
            }
        }
        return count;
    }

    public int lengthOfLastWord(String s) {
        int count = 0;
        char[] chars = s.toCharArray();
        int i = chars.length - 1;
        while (i >= 0) {
            if (isLetter(chars[i])) {
                count = i;
                while (i >= 1 && isLetter(chars[i - 1])) i--;
                return count - i + 1;
            } else i--;
        }
        return count;
    }

    private boolean isLetter(char c) {
        return (c >= 65 && c <= 90) || (c >= 97 && c <= 122);
    }

    /**
     * java does not have one function to judge whether it is a upper or lower
     * case letter. isLetter() returns true for title_case etc.
     */
    public int lengthOfLastWord2(String s) {
        int count = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (Character.isUpperCase(c) || Character.isLowerCase(c)) {
                count = i;
                while (Character.isUpperCase(c) || Character.isLowerCase(c)) {
                    i--;
                    if (i < 0) break;
                    c = s.charAt(i);
                }
                return count - i;
            }
        }
        return count;
    }

    public int lengthOfLastWord3(String s) {
        String ss = s.trim();
        return ss.length() - 1 - ss.lastIndexOf(' ');
    }

    public int lengthOfLastWord4(String s) {
        int len = s.length(), lastLength = 0;
        while (len > 0 && s.charAt(len - 1) == ' ') len--;

        while (len > 0 && s.charAt(len - 1) != ' ') {
            lastLength++;
            len--;
        }
        return lastLength;
    }

}
