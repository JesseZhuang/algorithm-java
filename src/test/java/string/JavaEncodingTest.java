package string;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.nio.charset.Charset;

public class JavaEncodingTest {
    @Test
    void testJavaEncoding() throws FileNotFoundException {
        System.out.println("file.encoding=" + System.getProperty("file.encoding")); // mac UTF-8
        System.out.println("Charset.defaultCharset=" + Charset.defaultCharset()); // mac UTF-8
//        System.out.println("InputStreamReader.getEncoding=" + new InputStreamReader(
//                new FileInputStream("/graph/EWD.cycle.txt")).getEncoding());
    }
}
