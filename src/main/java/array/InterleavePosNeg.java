package array;

import util.IntUtil;

import java.util.Arrays;

/**
 * LintCode. Easy.
 * <p>
 * Given an array with positive and negative integers. Re-range it to
 * interleaving with positive and negative integers.
 * <p>
 * Notice: You are not necessary to keep the original order of positive integers
 * or negative integers.
 * <p>
 * Example Given [-1, -2, -3, 4, 5, 6], after re-range, it will be [-1, 5, -2,
 * 4, -3, 6] or any other reasonable answer.
 * <p>
 * Challenge Do it in-place and without extra memory.
 * <p>
 * Tags: Two Pointers.
 * <p>
 * <b>Summary:</b>
 * <p>
 * <ul>
 * <li><b>two pointers, not stable, O(n) time, O(1) space, 1716 ms.</b>
 * <li>quick sort partition first, then swaps. more swaps than two pointer
 * method. O(n) time, O(1) space. 1752 ms.
 * <li>use one auxiliary array, stable, O(n) time, O(n) space, 2276 ms.
 * </ul>
 */
public class InterleavePosNeg {

    public void rearrangeQuick(int[] A) {
        if (A == null) return;
        int neg = -1, pos = A.length;
        while (true) {
            while (A[++neg] < 0)
                if (neg == A.length - 1) break;
            while (A[--pos] > 0)
                if (pos == 0) break;
            if (neg >= pos) break;
            IntUtil.swap(A, neg, pos);
        }
        pos = 1;
        if (neg <= A.length / 2) pos = 0;
        while (neg < A.length && neg > pos) {
            IntUtil.swap(A, pos, neg++);
            pos += 2;
        }
    }

    public void rearrangeTwoPointer(int[] A) {
        if (A == null) return;
        int numPos = 0;
        for (int aA : A) if (aA > 0) numPos++;
        int pos = 1, neg = 0;
        if (numPos >= A.length - numPos) {
            pos = 0;
            neg = 1;
        }
        while (pos < A.length && neg < A.length) {
            while (pos < A.length && A[pos] > 0)
                pos += 2;
            while (neg < A.length && A[neg] < 0)
                neg += 2;
            if (pos < A.length && neg < A.length) IntUtil.swap(A, pos, neg);
            pos += 2;
            neg += 2;
        }
    }

    public void rearrangeAuxArray(int[] A) {
        if (A == null) return;
        int[] aux = new int[A.length];

        int numPos = 0;
        for (int aA : A) if (aA > 0) numPos++;

        // indexing positive and negative numbers
        int first = 0, second = 0, k;
        if (numPos >= A.length - numPos) {
            for (k = 0; k < A.length; k++) {
                while (second < A.length && A[second] > 0)
                    second++;
                while (first < A.length && A[first] < 0)
                    first++;
                if (first >= A.length) aux[k] = A[second++];
                else if (second >= A.length) aux[k] = A[first++];
                else if (k % 2 == 0) aux[k] = A[first++];
                else aux[k] = A[second++];

            }
        } else {
            for (k = 0; k < A.length; k++) {
                while (first < A.length && A[first] > 0)
                    first++;
                while (second < A.length && A[second] < 0)
                    second++;
                if (first >= A.length) aux[k] = A[second++];
                else if (second >= A.length) aux[k] = A[first++];
                else if (k % 2 == 0) aux[k] = A[first++];
                else aux[k] = A[second++];

            }
        }
        for (k = 0; k < A.length; k++)
            A[k] = aux[k];
    }

    public static void main(String[] args) {
        InterleavePosNeg i = new InterleavePosNeg();
        int[][] inputs = { { 9, -3, 8, 5, -3, -4, -5, -4 },
                { -33, -19, 30, 26, 21, -9 }, { 28, 2, -22, -27, 2, 9, -33, -4, -18,
                26, 25, 34, -35, -17, 2, -2, 32, 35, -8 } };
        for (int[] A : inputs) {
            // i.rearrangeQuick(A);
            i.rearrangeTwoPointer(A);
            System.out.println(Arrays.toString(A));
        }
    }
}
