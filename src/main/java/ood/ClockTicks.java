package ood;

import java.util.ArrayList;
import java.util.List;

/**
 * lesson: do not hurry, make sure logic correctness and robustness first. cosmetic things can be improved later.
 */
public class ClockTicks {

    static List<TimeOfDay> wholeDay = TimeOfDay.wholeDay();

    // Hands are "lined up" when they are all within a full tick of each other.
    static void printLinedUpTimes1() {
        for (TimeOfDay time : wholeDay) {
            double second = Math.ceil(time.second / 60.0 * 60.0);
            double minute = Math.ceil((time.minute + second / 60.0) / 60.0 * 60.0); // for 0:0:1, 1/60, -> 1
            double hour = Math.ceil((time.hour + time.minute / 60.0 + time.second / 3600.0) / 24.0 * 60.0);
            if (hour == minute && minute == second) {
                System.out.println(hour + ":" + minute + ":" + second);
                System.out.println("real time below:");
                System.out.println(time.hour + ":" + time.minute + ":" + time.second);
            }
        }
    }

    static void printLinedUpTimes2() {
        for (TimeOfDay time : wholeDay) {
            double second = time.second; //auto upgrade int to double data type
            double minute = time.minute + second / 60.0;
            double hour = time.hour + time.minute / 60.0 + time.second / 3600.0;
            double max = Math.max(second, minute);
            max = Math.max(max, hour);
            double min = Math.min(second, minute);
            min = Math.min(hour, min);
            if (max - min <= 1) System.out.println(time + ", diff: " + (max - min));
        }
    }


    public static void main(String[] args) {
        // printLinedUpTimes1();
        printLinedUpTimes2();
    }
}

class TimeOfDay {
    // hh:mm:ss
    int hour; // 0 - 23
    int minute; // 0 - 59, angle range
    int second; // 0 - 59, angle range [0, 1) * 60

    public TimeOfDay(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    @Override
    public String toString() {
        return "TimeOfDay{" +
                "hour=" + hour +
                ", minute=" + minute +
                ", second=" + second +
                '}';
    }

    public static List<TimeOfDay> wholeDay() {
        List<TimeOfDay> list = new ArrayList<>();
        for (int hour = 0; hour < 24; hour++) {
            for (int minute = 0; minute < 60; minute++) {
                for (int second = 0; second < 60; second++) {
                    list.add(new TimeOfDay(hour, minute, second));
                }
            }
        }
        return list;
    }
}
