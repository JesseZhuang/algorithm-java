package math;

import edu.princeton.cs.algs4.Queue;

import java.util.Arrays;

/**
 * The four adjacent digits in the 1000-digit number that have the greatest product are 9 × 9 × 8 × 9 = 5832.
 * <pre>
 * 73167176531330624919225119674426574742355349194934
 * 96983520312774506326239578318016984801869478851843
 * 85861560789112949495459501737958331952853208805511
 * 12540698747158523863050715693290963295227443043557
 * 66896648950445244523161731856403098711121722383113
 * 62229893423380308135336276614282806444486645238749
 * 30358907296290491560440772390713810515859307960866
 * 70172427121883998797908792274921901699720888093776
 * 65727333001053367881220235421809751254540594752243
 * 52584907711670556013604839586446706324415722155397
 * 53697817977846174064955149290862569321978468622482
 * 83972241375657056057490261407972968652414535100474
 * 82166370484403199890008895243450658541227588666881
 * 16427171479924442928230863465674813919123162824586
 * 17866458359124566529476545682848912883142607690042
 * 24219022671055626321111109370544217506941658960408
 * 07198403850962455444362981230987879927244284909188
 * 84580156166097919133875499200524063689912560717606
 * 05886116467109405077541002256983155200055935729725
 * 71636269561882670428252483600823257530420752963450
 * </pre>
 * <p>
 * Find the thirteen adjacent digits in the 1000-digit number that have the greatest product.
 * What is the value of this product?
 * <p>
 * <b>Summary</b>:
 * <p>
 * <ul>
 * <li>recursive or iterative, O(log5_n) time, O(1) space.
 * </ul>
 */
public class ProjectEuler8 {
    public static int[] findMaxProduct4Naive(String number) {
        int[] result = new int[2];
        if (number.length() < 4) {
            Arrays.fill(result, -1);
            return result;
        }

        int max = Integer.MIN_VALUE;
        int index = 0;
        for (int i = 0; i < number.length(); i++) {
            /**
             * 7 3 1 6
             * p = 126
             * p = 126 / 7 * 6 = 18 * 6 = 108
             * p = n1 * n2 * n3 * n4
             * p = (n1 * n2 * n3 * n4) / n1 * n5
             */
            // '0' -> 36 '1' -> 37
            while (number.charAt(i) == '0') i++;
            int product = 1;
            for (int j = 0; j < 4; j++) {
                int num = number.charAt(i + j) - '0';
                if (num != 0) product *= num;
                else {
                    i = i + j;
                    product = 0;
                    break;
                }
            }
            if (product > max) {
                max = product;
                index = i;
            }
        }
        result[0] = max; // max product
        result[1] = index; // starting index
        return result;
    }

    public static int[] findMaxProduct(String number, int numConsecutiveDigits) {
        Queue<Integer> queue = new Queue<>();
        Queue<Integer> maxQueue = new Queue<>();
        int counter = 0, current, product = 1, maxProduct = -1, startingIndex = -1;
        for (int i = 0; i < number.length(); i++) {
            current = number.charAt(i) - '0';
            if (current == 0 && i != number.length() - 1) {
                counter = 0;
                product = 1;
                while (!queue.isEmpty()) queue.dequeue();
                continue;
            }
            queue.enqueue(current);
            counter++;
            product *= current; // save some multiplication operations
            if (counter == numConsecutiveDigits) {
                if (product > maxProduct) {
                    maxProduct = product;
                    startingIndex = i + 1 - numConsecutiveDigits;
                    maxQueue = copyQueue(queue);
                }
                if (i != number.length() - 1) {
                    int firstInQueue = queue.dequeue();
                    if (firstInQueue != 0) product /= firstInQueue;
                    counter--;
                }
            }
        }
        int[] result = new int[2];
        result[0] = maxProduct;
        result[1] = startingIndex;
        System.out.println("queue " + maxQueue);
        return result;
    }

    public static long[] findMaxProductLong(String number, int numConsecutiveDigits) {
        Queue<Integer> queue = new Queue<>();
        Queue<Integer> maxQueue = new Queue<>();
        int counter = 0, current, startingIndex = -1;
        long product = 1, maxProduct = -1;
        for (int i = 0; i < number.length(); i++) {
            current = number.charAt(i) - '0';
            if (current == 0 && i != number.length() - 1) {
                counter = 0;
                product = 1;
                while (!queue.isEmpty()) queue.dequeue();
                continue;
            }
            queue.enqueue(current);
            counter++;
            product *= current; // save some multiplication operations
            if (counter == numConsecutiveDigits) {
                if (product > maxProduct) {
                    maxProduct = product;
                    startingIndex = i + 1 - numConsecutiveDigits;
                    maxQueue = copyQueue(queue);
                }
                if (i != number.length() - 1) {
                    int firstInQueue = queue.dequeue();
                    if (firstInQueue != 0) product /= firstInQueue;
                    counter--;
                }
            }
        }
        long[] result = new long[2];
        result[0] = maxProduct;
        result[1] = startingIndex;
        System.out.println("queue " + maxQueue);
        return result;
    }

    private static Queue<Integer> copyQueue(Queue<Integer> queue) {
        Queue<Integer> result = new Queue<>();
        for (Integer num : queue) result.enqueue(num);
        return result;
    }

    public static void main(String[] args) {
        final String number = "73167176531330624919225119674426574742355349194934"
                + "96983520312774506326239578318016984801869478851843"
                + "85861560789112949495459501737958331952853208805511"
                + "12540698747158523863050715693290963295227443043557"
                + "66896648950445244523161731856403098711121722383113"
                + "62229893423380308135336276614282806444486645238749"
                + "30358907296290491560440772390713810515859307960866"
                + "70172427121883998797908792274921901699720888093776"
                + "65727333001053367881220235421809751254540594752243"
                + "52584907711670556013604839586446706324415722155397"
                + "53697817977846174064955149290862569321978468622482"
                + "83972241375657056057490261407972968652414535100474"
                + "82166370484403199890008895243450658541227588666881"
                + "16427171479924442928230863465674813919123162824586"
                + "17866458359124566529476545682848912883142607690042"
                + "24219022671055626321111109370544217506941658960408"
                + "07198403850962455444362981230987879927244284909188"
                + "84580156166097919133875499200524063689912560717606"
                + "05886116467109405077541002256983155200055935729725"
                + "71636269561882670428252483600823257530420752963450";
        int[] result = findMaxProduct4Naive(number);
        // [5832, 615] 9989 for 4 consecutive
        System.out.println(Arrays.toString(result) + " " + number.substring(result[1], result[1] + 4));
        System.out.println(Arrays.toString(findMaxProduct("763", 2)));
        System.out.println(Arrays.toString(findMaxProduct("763", 4)));
        System.out.println(Arrays.toString(findMaxProduct(number, 4)));
        // max is 40824 for 5 consecutive
        System.out.println(Arrays.toString(findMaxProduct(number, 5)));
        // max is 23514624000 for 13 > INT_MAX overflow
        System.out.println(Arrays.toString(findMaxProductLong(number, 13)));
    }
}
