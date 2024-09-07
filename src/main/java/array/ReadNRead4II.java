package array;

import static array.ReadNRead4.read4;

/**
 * LeetCode 158, hard, tags: array, interactive, simulation.
 * <p>
 * Given a file and assume that you can only read the file using a given method read4, implement a method read to
 * read n characters. Your method read may be called multiple times.
 * <p>
 * Method read4:
 * <p>
 * The API read4 reads four consecutive characters from file, then writes those characters into the buffer array buf4.
 * <p>
 * The return value is the number of actual characters read.
 * <p>
 * Note that read4() has its own file pointer, much like FILE *fp in C.
 * <p>
 * Definition of read4:
 * <p>
 * Parameter:  char[] buf4
 * Returns:    int
 * <p>
 * buf4[] is a destination, not a source. The results from read4 will be copied to buf4[].
 * Below is a high-level example of how read4 works:
 * <p>
 * <p>
 * <p>
 * File file("abcde"); // File is "abcde", initially file pointer (fp) points to 'a'
 * char[] buf4 = new char[4]; // Create buffer with enough space to store characters
 * read4(buf4); // read4 returns 4. Now buf4 = "abcd", fp points to 'e'
 * read4(buf4); // read4 returns 1. Now buf4 = "e", fp points to end of file
 * read4(buf4); // read4 returns 0. Now buf4 = "", fp points to end of file
 * <p>
 * <p>
 * Method read:
 * <p>
 * By using the read4 method, implement the method read that reads n characters from file and store it in the
 * buffer array buf. Consider that you cannot manipulate file directly.
 * <p>
 * The return value is the number of actual characters read.
 * <p>
 * Definition of read:
 * <p>
 * Parameters:	char[] buf, int n
 * Returns:	int
 * <p>
 * buf[] is a destination, not a source. You will need to write the results to buf[].
 * Note:
 * <p>
 * Consider that you cannot manipulate the file directly. The file is only accessible for read4 but not for read.
 * The read function may be called multiple times.
 * Please remember to RESET your class variables declared in Solution, as static/class variables are persisted
 * across multiple test cases. Please see here for more details.
 * You may assume the destination buffer array, buf, is guaranteed to have enough space for storing n characters.
 * It is guaranteed that in a given test case the same buffer buf is called by read.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: file = "abc", queries = [1,2,1]
 * Output: [1,2,0]
 * Explanation: The test case represents the following scenario:
 * File file("abc");
 * Solution sol;
 * sol.read(buf, 1); // After calling your read method, buf should contain "a". We read a total of 1 character
 * from the file, so return 1.
 * sol.read(buf, 2); // Now buf should contain "bc". We read a total of 2 characters from the file, so return 2.
 * sol.read(buf, 1); // We have reached the end of file, no more characters can be read. So return 0.
 * Assume buf is allocated and guaranteed to have enough space for storing all characters from the file.
 * Example 2:
 * <p>
 * Input: file = "abc", queries = [4,1]
 * Output: [3,0]
 * Explanation: The test case represents the following scenario:
 * File file("abc");
 * Solution sol;
 * sol.read(buf, 4); // After calling your read method, buf should contain "abc". We read a total of 3 characters
 * from the file, so return 3.
 * sol.read(buf, 1); // We have reached the end of file, no more characters can be read. So return 0.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= file.length <= 500
 * file consist of English letters and digits.
 * 1 <= queries.length <= 10
 * 1 <= queries[i] <= 500
 */
public class ReadNRead4II {

    private final char[] buf4 = new char[4];
    private boolean done;
    private int i;
    private int size;

    public static void main(String[] args) {
        ReadNRead4II tbt = new ReadNRead4II();
        System.out.println(tbt.read(new char[30], 1));
        System.out.println(tbt.read(new char[30], 2));
        System.out.println(tbt.read(new char[30], 5));
        System.out.println(tbt.read(new char[30], 8));
        System.out.println(tbt.read(new char[30], 5));
        System.out.println(tbt.read(new char[30], 6));
    }

    // 752ms, 17.58Mb.
    public int read(char[] buf, int n) {
        int j = 0;
        while (j < n) {
            if (i == size) { // buf4 completed or initial state
                if (done) return j;
                size = read4(buf4);
                i = 0;
                if (size < 4) done = true;
            }
            while (j < n && i < size) {
                buf[j++] = buf4[i++];
            }
        }
        return j;
    }
}
