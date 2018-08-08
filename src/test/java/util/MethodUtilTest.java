package util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MethodUtilTest {

    private static MethodUtil methodUtil;
    private static final String METHOD_NAME = "getDeclaredPublicMethods";

    @BeforeAll
    static void setup() {
        methodUtil = new MethodUtil();
    }

    @Test
    void testGetPublicMethods() {
        List<Method> methods = MethodUtil.getDeclaredPublicMethods(methodUtil);
        // System.out.printf(methods.toString());
        // [public static java.util.List util.MethodUtil.getDeclaredPublicMethods(java.lang.Object)]
        assertEquals(methods.get(0).getName(), METHOD_NAME);
    }
}
