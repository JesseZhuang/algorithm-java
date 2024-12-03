package hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 811, medium.
 * let n = cpdomains.length, m = number of domain fragments in cpdomains[i]
 */
@SuppressWarnings("unused")
public class SubdomainVisitCnt {
    // mn, mn. 16 ms, 45.3 mb.
    static class Solution {
        public List<String> subdomainVisits(String[] cpdomains) {
            Map<String, Integer> cnt = new HashMap<>(); // domain -> count
            for (String cd : cpdomains) {
                int i = cd.indexOf(' ');
                int n = Integer.parseInt(cd.substring(0, i));
                String s = cd.substring(i + 1);
                cnt.merge(s, n, Integer::sum);
                for (i = 0; i < s.length(); ++i) {
                    if (s.charAt(i) != '.') continue;
                    String d = s.substring(i + 1);
                    cnt.merge(d, n, Integer::sum);
                }
            }
            List<String> res = new ArrayList<>();
            for (String d : cnt.keySet()) res.add(cnt.get(d) + " " + d);
            return res;
        }
    }
}
