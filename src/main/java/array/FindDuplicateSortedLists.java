package array;

import princeton.jsl.BinarySearch;

import java.util.ArrayList;
import java.util.List;

/**
 * Facebook. Easy. See LeetCode 350 intersect two arrays.
 * <p>
 * Find duplicated elements in two sorted lists. For example,
 * <pre>
 * A = [2, 3, 5, 11]
 * B = [3, 4, 11, 13]
 *
 * Output = [3, 11]
 * </pre>
 */
public class FindDuplicateSortedLists {

    // optimization on top of solution 1 below
    public List<Integer> commonElementsBinarySearch(int[] array1, int[] array2) {
        List<Integer> commonElements = new ArrayList<>();
        if (array1 == null || array2 == null) {
            return commonElements;
        }
        if (array1.length > array2.length) {
            int[] temp = array1;
            array1 = array2;
            array2 = temp;
        }
        int length1 = array1.length;
        int length2 = array2.length;
        for (int i = 0, j = 0; i < length1 && j < length2; ) {
            if (array1[i] == array2[j]) {
                commonElements.add(array1[i]);
                i++;
                j++;
            } else if (array1[i] < array2[j]) {
                i++;
            } else {
                int index2 = BinarySearch.binarySearchIndexToInsert(array2, array1[i]);
                j = index2;
            }
        }
        return commonElements;
    }

    // solution 1, m+n time, 1 space.
    public List<Integer> commonElementsLinearSearch(int[] array1, int[] array2) {
        List<Integer> commonElements = new ArrayList<Integer>();
        if (array1 == null || array2 == null) {
            return commonElements;
        }
        int length1 = array1.length;
        int length2 = array2.length;
        for (int i = 0, j = 0; i < length1 && j < length2; ) {
            if (array1[i] == array2[j]) {
                commonElements.add(array1[i]);
                i++;
                j++;
            } else if (array1[i] < array2[j]) {
                i++;
            } else {
                j++;
            }
        }
        return commonElements;
    }

}
