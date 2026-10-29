package queue;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode 1700, easy, tags: array, queue, simulation, counting.
 * <p>
 * Students and sandwiches are each encoded as 0 (circular) or 1 (square).
 * A student who does not want the top sandwich moves to the end of the queue.
 * Return the number of students left when no remaining student wants the top sandwich.
 * <p>
 * Constraints:
 * 1 <= students.length, sandwiches.length <= 100
 * students.length == sandwiches.length
 * students[i], sandwiches[i] are 0 or 1
 */
@SuppressWarnings("unused")
public final class NumberOfStudentsUnableToEatLunch {
    private NumberOfStudentsUnableToEatLunch() {
    }

    /**
     * Counts student preferences instead of rotating the queue. O(n) time and O(1) extra space.
     */
    public static int countStudents(int[] students, int[] sandwiches) {
        int[] remaining = new int[2];
        for (int student : students) { // O(n)
            remaining[student]++;
        }

        for (int sandwich : sandwiches) { // O(n)
            if (remaining[sandwich] == 0) {
                return remaining[1 - sandwich];
            }
            remaining[sandwich]--;
        }
        return 0;
    }

    /**
     * Simulates student rotations directly. O(n^2) time and O(n) space in the worst case.
     */
    public static int countStudentsQueue(int[] students, int[] sandwiches) {
        Queue<Integer> studentsInLine = new ArrayDeque<>();
        for (int student : students) { // O(n)
            studentsInLine.offer(student);
        }

        int sandwichIndex = 0;
        int rotationsWithoutEating = 0;
        while (!studentsInLine.isEmpty() && rotationsWithoutEating < studentsInLine.size()) {
            if (studentsInLine.peek() == sandwiches[sandwichIndex]) {
                studentsInLine.poll();
                sandwichIndex++;
                rotationsWithoutEating = 0;
            } else {
                studentsInLine.offer(studentsInLine.poll());
                rotationsWithoutEating++;
            }
        }
        return studentsInLine.size();
    }
}
