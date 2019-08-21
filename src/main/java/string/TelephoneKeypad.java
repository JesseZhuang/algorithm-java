package string;

import java.util.*;

/**
 * Give all possible words with sequence of keypad inputs.
 * e.g., 23: [ad, ae, af, bd, be, bf, cd, ce, cf]
 *
 * Follow up: valid dictionary ["advertiser"]. Hint: use a trie or build prefix array/set.
 * Only give output that is a prefix of the words in the valid dictionary.
 */
public class TelephoneKeypad {

    static List<String> allPossibleWords(String digits, Map<Character,List<Character>> map) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) return result;
        for(int i = 0; i < digits.length(); i++)
            if (digits.charAt(i) > '9' || digits.charAt(i) < '2') return result;
        result.add("");
        for(int i = 0; i < digits.length(); i++) {
            char digit = digits.charAt(i);
            List<Character> possibleChars = map.get(digit);
            List<String> temp = new ArrayList<>();
            for(String string: result)
                for(Character pc: possibleChars)
                    temp.add(string + pc);
            result = temp;
        }
        return result;
    }

    public static void main(String[] args) {
        Map<Character, List<Character>> map = new HashMap<>();
        map.put('2', Arrays.asList('a', 'b', 'c'));
        map.put('3', Arrays.asList('d', 'e', 'f'));
        map.put('4', Arrays.asList('g', 'h', 'i'));
        map.put('5', Arrays.asList('j', 'k', 'l'));
        map.put('6', Arrays.asList('m', 'n', 'o'));
        map.put('7', Arrays.asList('p', 'q', 'r', 's'));
        map.put('8', Arrays.asList('t', 'u', 'v'));
        map.put('9', Arrays.asList('w', 'x', 'y', 'z'));
        for (Map.Entry e : map.entrySet()) {
            System.out.println("key: " + e.getKey() + "; value: " + e.getValue());
        }
        List<String> possibleWords = allPossibleWords("23", map);
        System.out.println("23: " + possibleWords);
        possibleWords = allPossibleWords("231", map);
        System.out.println("231: " + possibleWords);
    }

}
