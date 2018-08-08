package bit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import util.MethodUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SumTwoIntegersTest {

    private static SumTwoIntegers sumTwoIntegers;

    @BeforeAll
    static void setup() {
        sumTwoIntegers = new SumTwoIntegers();
    }

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvFileSource(resources = {"/SumTwoIntegers.csv"}, numLinesToSkip = 2)
    void testSum(int a, int b, int sum) throws InvocationTargetException, IllegalAccessException {
        List<Method> methods = MethodUtil.getDeclaredPublicMethods(sumTwoIntegers);
        for (Method method : methods) {
            // System.out.println("Testing method : " + method.getName());
            assertEquals(method.invoke(sumTwoIntegers, a, b), sum);
        }
        assertEquals(sumTwoIntegers.getSumRecursive(a, b), sum);
        assertEquals(sumTwoIntegers.getSumIterative(a, b), sum);
        assertEquals(sumTwoIntegers.getSumRadix32(a, b), sum);
    }
}
