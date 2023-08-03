package string;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1507, easy, tags: string.
 * <p>
 * Given a date string in the form Day Month Year, where:
 * <p>
 * Day is in the set {"1st", "2nd", "3rd", "4th", ..., "30th", "31st"}.
 * Month is in the set {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}.
 * Year is in the range [1900, 2100].
 * Convert the date string to the format YYYY-MM-DD, where:
 * <p>
 * YYYY denotes the 4 digit year.
 * MM denotes the 2 digit month.
 * DD denotes the 2 digit day.
 * <p>
 * Example 1:
 * <p>
 * Input: date = "20th Oct 2052"
 * Output: "2052-10-20"
 * Example 2:
 * <p>
 * Input: date = "6th Jun 1933"
 * Output: "1933-06-06"
 * Example 3:
 * <p>
 * Input: date = "26th May 1960"
 * Output: "1960-05-26"
 * <p>
 * Constraints:
 * <p>
 * The given dates are guaranteed to be valid, so no error handling is necessary.
 * <p>
 * Hints:
 * <p>
 * Handle the conversions of day, month and year separately.
 * Notice that days always have a two-word ending, so if you erase the last two characters of this days
 * you'll get the number.
 */
public class ReformatDate {
    // 1ms, 40.7 Mb, month could use case switch
    public String reformatDate(String date) {
        StringBuilder res = new StringBuilder();
        String[] sa = date.split(" ");
        String dash = "-";
        Map<String, String> months = new HashMap<>();
        months.put("Jan", "01");
        months.put("Feb", "02");
        months.put("Mar", "03");
        months.put("Apr", "04");
        months.put("May", "05");
        months.put("Jun", "06");
        months.put("Jul", "07");
        months.put("Aug", "08");
        months.put("Sep", "09");
        months.put("Oct", "10");
        months.put("Nov", "11");
        months.put("Dec", "12");
        res.append(sa[2]); // year
        res.append(dash);
        res.append(months.get(sa[1])); // month
        res.append(dash);
        if (sa[0].length() < 4) res.append("0" + sa[0].substring(0, 1));
        else res.append(sa[0].substring(0, 2));
        return res.toString();
    }
}
