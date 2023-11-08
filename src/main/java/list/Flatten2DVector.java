package list;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * LeetCode 251, LintCode 601, medium, tags: design, iterator, array, two pointers.
 * <p>
 * Design an iterator to flatten a 2D vector. It should support the next and hasNext operations.
 * <p>
 * Implement the Vector2D class:
 * <p>
 * Vector2D(int[][] vec) initializes the object with the 2D vector vec.
 * next() returns the next element from the 2D vector and moves the pointer one step forward.
 * You may assume that all the calls to next are valid.
 * hasNext() returns true if there are still some elements in the vector, and false otherwise.
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["Vector2D", "next", "next", "next", "hasNext", "hasNext", "next", "hasNext"]
 * [[[[1, 2], [3], [4]]], [], [], [], [], [], [], []]
 * Output
 * [null, 1, 2, 3, true, true, 4, false]
 * <p>
 * Explanation
 * Vector2D vector2D = new Vector2D([[1, 2], [3], [4]]);
 * vector2D.next();    // return 1
 * vector2D.next();    // return 2
 * vector2D.next();    // return 3
 * vector2D.hasNext(); // return True
 * vector2D.hasNext(); // return True
 * vector2D.next();    // return 4
 * vector2D.hasNext(); // return False
 * <p>
 * Constraints:
 * <p>
 * 0 <= vec.length <= 200
 * 0 <= vec[i].length <= 500
 * -500 <= vec[i][j] <= 500
 * At most 105 calls will be made to next and hasNext.
 * <p>
 * Follow up: As an added challenge, try to code it using only iterators in C++ or iterators in Java.
 */
public class Flatten2DVector {
    // solution 1, lint code no need null check, 4466ms, 52.15Mb. O(n, worst case n empty list) time and O(1) space.
    // another thought is to leverage the iterator of the input list, highly unlikely what the interviewer wants
    class Vec2DList implements Iterator<Integer> {
        private int l, i; // which list, index on current list
        private List<List<Integer>> list;

        public Vec2DList(List<List<Integer>> vec2d) {
            list = vec2d;
        }

        @Override
        public boolean hasNext() {
            if (list == null) return false;
            while (l < list.size() && list.get(l).size() == 0) { // edge case skip empty list
                l++;
                i = 0;
            }
            if (l > list.size() || i >= list.get(l).size()) return false;
            return true;
        }

        @Override
        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            int res = list.get(l).get(i);
            if (i + 1 >= list.get(l).size()) {
                i = 0;
                l++;
            } else i++;
            return res;
        }
    }

    // solution 2, lint code 5828ms, 45.13 Mb. O(n, worst case n empty list) time and O(1) space.
    class Vec2DArray implements Iterator<Integer> {
        int r, c;
        int[][] vec;

        Vec2DArray(int[][] vec2d) {
            vec = vec2d;
        }

        @Override
        public boolean hasNext() {
            moveToNextRow();
            return r < vec.length;
        }

        @Override
        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            return vec[r][c++];
        }

        void moveToNextRow() {
            while (r < vec.length && c >= vec[r].length) {
                r++;
                c = 0;
            }
        }
    }
}
