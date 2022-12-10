package string;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * With templated string containing variables and map for look up. Do the string interpolation.
 * Estimations: String length 1000 chars, contains 100 variables, map size is 1000.
 * For example, input: "hello %user%", output: "hello, Harry Potter"
 * <p>
 * Probably hard, considering leet code 833 is medium.
 */
public class StringInterpolation {

    static String interpolate(String input, Map<String, String> variableLookup) {
        StringBuilder result = new StringBuilder();
        int start = 0, varLength = 0;
        boolean processing = false;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            result.append(c);
            if (processing) {
                if (validate(c)) varLength++;
                else {
                    processing = false;
                    varLength = 0;
                }
            }
            if (c == '%') {
                if (!processing) start = result.length() - 1;
                else {
                    int end = start + varLength;
                    String replace = variableLookup.get(result.substring(start + 1, end));
                    if (replace == null) throw new IllegalStateException("variable not found in map");
                    result.replace(start, end + 1, replace);
                    varLength = 0;
                }
                processing = !processing;
            }
        }
        return result.toString();
    }

    static boolean validate(char c) {
        return Character.isAlphabetic(c) || Character.isDigit(c) || c == '%';
    }

    public static void main(String[] args) {
        Map<String, String> variableLookup = ImmutableMap.of("user", "Harry Potter",
                "fruit", "apple");
        System.out.println(interpolate("hello %user%, do you 100% like %fruit%", variableLookup));
    }
}
