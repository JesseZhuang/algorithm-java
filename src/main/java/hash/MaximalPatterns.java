package hash;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a set of user sessions that contains sequential set of page visits. Find the maximal frequent patterns for a given set
 * of sessions and support threshold (0.5).
 * <p>
 * <p>
 * S1: A-B-C
 * S2: A-B-D
 * S3: A-B-C-E
 * S4: A-B-F-C-E
 * <p>
 * Support(A-B) = 4/4 = 1.0 >= 0.5 (Frequent)
 * Support (A-B-C) = 2/4 = 0.5 >= 0.5 (Frequent)
 * <p>
 * Frequent Patterns: {A, B, C, A-B, B-C, A-B-C, E, C-E}
 * Maximal Patterns: {A-B-C, C-E}
 */

public class MaximalPatterns {

    String[] maximalPatters(String[] sessions, double threshold){
        List<String> freq = frequentPatterns(sessions, threshold);
        Set<String> freqSet = new TreeSet<>((a,b) ->
                a.length() != b.length()? b.length() - a.length():b.compareTo(a));
        freqSet.addAll(freq);
        Set<String> children = new HashSet<>();
        Iterator<String> iterator = freqSet.iterator();
        while (iterator.hasNext()) {
            String session = iterator.next();
            if (children.contains(session)) iterator.remove();
            else if (session.length() > 1) children.addAll(patternsOfSession(session, session.length() - 1));
        }
        return freqSet.toArray(new String[0]);
    }

    List<String> frequentPatterns(String[] sessions, double threshold) {
        int n = sessions.length;
        List<String> allPatterns = new ArrayList<>();
        Arrays.stream(sessions).forEach(s -> allPatterns.addAll(patternsOfSession(s, s.length())));
        if(threshold <= 1.0 / n) return allPatterns;
        Map<String, Double> frequencies = new HashMap<>();
        allPatterns.stream().forEach(pattern -> frequencies.compute(
                pattern, (k, v) -> v == null ? 1.0 / n : v + 1.0 / n));
        List<String> result =
                frequencies.entrySet().stream().filter(e -> e.getValue() >= threshold).map(e -> e.getKey())
                        .collect(Collectors.toList());
        return result;
    }

    List<String> patternsOfSession(String session, int upperLength) {
        int n = session.length();
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= upperLength; i++) {// patter length
            for (int j = 0; j < n - i + 1; j++) {// starting position
                StringBuilder pattern = new StringBuilder();
                for (int k = 0; k < i; k++) {// get enough characters to have length i
                    pattern.append(session.charAt(j + k));
                }
                result.add(pattern.toString());
            }
        }
        return result;
    }
}
