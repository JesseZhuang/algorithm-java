package string;

/** LeetCode 2000: Reverse Prefix of Word. */
public final class ReversePrefixOfWord {
    private ReversePrefixOfWord() {}

    public static String reversePrefix(String word, char ch) {
        int end = word.indexOf(ch);
        if (end < 0) return word;

        char[] letters = word.toCharArray();
        int left = 0;
        int right = end;
        while (left < right) {
            char temporary = letters[left];
            letters[left++] = letters[right];
            letters[right--] = temporary;
        }
        return new String(letters);
    }
}
