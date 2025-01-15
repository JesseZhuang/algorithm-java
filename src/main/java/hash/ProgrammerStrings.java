package hash;

import java.io.*;
import java.util.HashMap;

/**
 * hackerrank, medium, companies: salesforce.
 */
@SuppressWarnings("unused")
public class ProgrammerStrings {
    public static int programmerStrings(String s) {
        HashMap<Character, Integer> PG = new HashMap<>();
        String PGS = "programmer";
        for (int i = 0; i < PGS.length(); i++) PG.merge(PGS.charAt(i), 1, Integer::sum);
        int e1 = 0, s2 = 0; // end of leftmost programmer, start of rightmost programmer
        HashMap<Character, Integer> m2 = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (!PG.containsKey(s.charAt(i))) continue;
            m2.merge(s.charAt(i), 1, Integer::sum);
            if (enough(PG, m2)) {
                e1 = i;
                break;
            }
        }
        m2 = new HashMap<>();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (!PG.containsKey(s.charAt(i))) continue;
            m2.merge(s.charAt(i), 1, Integer::sum);
            if (enough(PG, m2)) {
                s2 = i;
                break;
            }
        }
        return s2 - e1 - 1;
    }

    // m1 is programmer map, check whether m2 has enough letters to compose programmer string
    static boolean enough(HashMap<Character, Integer> m1, HashMap<Character, Integer> m2) {
        for (Character c : m1.keySet()) {
            if (!m2.containsKey(c)) return false;
            if (m2.get(c) < m1.get(c)) return false;
        }
        return true;
    }

    static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            String s = bufferedReader.readLine();

            int result = programmerStrings(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();

            bufferedReader.close();
            bufferedWriter.close();
        }
    }
}
