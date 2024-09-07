package array;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;

/**
 * LeetCode 157, LintCode 3622, easy, tags: array, interactive, simulation.
 * <p>
 * Given a file and assume that you can only read the file using a given method read4, implement a method to
 * read n characters.
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
 * https://camo.githubusercontent.com/98c4ae5f23b92129c3c03b5a2f964ec934708394058e9da99e3721ebdc410ebc/68747470733a2f2f666173746c792e6a7364656c6976722e6e65742f67682f646f6f63732f6c656574636f6465406d61696e2f736f6c7574696f6e2f303130302d303139392f303135372e526561642532304e25323043686172616374657273253230476976656e25323052656164342f696d616765732f3135375f6578616d706c652e706e67
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
 * The read function will only be called once for each test case.
 * You may assume the destination buffer array, buf, is guaranteed to have enough space for storing n characters.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: file = "abc", n = 4
 * Output: 3
 * Explanation: After calling your read method, buf should contain "abc". We read a total of 3 characters from
 * the file, so return 3.
 * Note that "abc" is the file's content, not buf. buf is the destination buffer that you will have to write
 * the results to.
 * Example 2:
 * <p>
 * Input: file = "abcde", n = 5
 * Output: 5
 * Explanation: After calling your read method, buf should contain "abcde". We read a total of 5 characters
 * from the file, so return 5.
 * Example 3:
 * <p>
 * Input: file = "abcdABCD1234", n = 12
 * Output: 12
 * Explanation: After calling your read method, buf should contain "abcdABCD1234". We read a total of 12 characters
 * from the file, so return 12.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= file.length <= 500
 * file consist of English letters and digits.
 * 1 <= n <= 1000
 */
public class ReadNRead4 {
    public static int sp; // index for next position to read

    public static void main(String[] args) {
        System.out.println("read: " + new ReadNRead4().read(new char[20], 20));
        System.out.println("read: " + new ReadNRead4().read(new char[20], 10)); // will read 12 since 4*3>10 4*2<10
    }

    public static int read4(char[] buf4) {
        String str = "GeeksForGeeks";
        try (Reader reader = new StringReader(str.substring(sp))) {
            Arrays.fill(buf4, '\u0000');
            int n = reader.read(buf4);
            if (n == -1) return 0;
            sp += n;
            return n;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // LintCode 231ms, 20.05Mb. O(n) time O(4) space.
    // clarify what to do when n<num characters in file, can set buf with more than n characters?
    public int read(char[] buf, int n) {
        char[] buf4 = new char[4];
        int v = 4, cnt = 0;
        while (v >= 4) {
            v = read4(buf4);
            for (int i = 0; i < v; i++)
                buf[cnt++] = buf4[i];
            if (cnt >= n) return n;
        }
        return cnt;
    }
}
