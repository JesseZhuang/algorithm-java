package util;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Uses reflection to test a class's method with provided inputs and expected
 * results. The names of the class and method are specified as String
 * parameters. The {@code className} parameter should include its package name.
 * <p>
 * If one of the test cases fail, the program will print out the failed test and
 * exit.
 */
@SuppressWarnings("unchecked")
public class Test {

    private final static String SUCCESS = "Congrats! All tests passed.";

    /**
     * Test method as if calling {@code className.methodName()} assuming only
     * one parameter for the tested method.
     *
     * @param <R>
     *            method return type
     * @param className
     *            the class to be tested
     * @param methodName
     *            the method to be tested
     * @param inputs
     *            an array containing all test cases' inputs
     * @param expected
     *            an array containing all expected results
     */
    public static <R> void assertEqual(String className, String methodName,
                                       Object[] inputs, Object[] expected) {
        validateLengths(inputs, expected);
        try {
            // use reflection to get the type of class indicated by "className"
            Class<?> c = Class.forName(className);
            Class<?> inputType = unboxing(inputs[0].getClass());
            Method m = c.getMethod(methodName, inputType);
            // static methods will ignore this instance
            Object o = c.newInstance();
            for (int i = 0; i < inputs.length; i++) {
                Object e = expected[i];
                R output = (R) m.invoke(o, inputs[i]);
                String inputi = inputs[i] == null ? "null"
                        : inputs[i].toString();
                if (inputs[i] != null && inputs[i].getClass().isArray()) {
                    int n2 = Array.getLength(inputs[i]);
                    Object boxedInputsi = Array.newInstance(
                            boxing(inputs[i].getClass().getComponentType()),
                            n2);
                    for (int j = 0; j < n2; j++) {
                        Array.set(boxedInputsi, j, Array.get(inputs[i], j));
                    }
                    inputi = Arrays.deepToString((Object[]) boxedInputsi);
                }
                if (e != null && e.getClass().isArray()) {
                    int n1 = Array.getLength(e);
                    Object boxedE = Array.newInstance(
                            boxing(e.getClass().getComponentType()), n1);
                    Object boxedOutput = Array.newInstance(
                            boxing(e.getClass().getComponentType()), n1);

                    for (int j = 0; j < n1; j++) {
                        Array.set(boxedE, j, Array.get(e, j));
                        Array.set(boxedOutput, j, Array.get(output, j));
                    }
                    if (!Arrays.deepEquals((Object[]) boxedE,
                            (Object[]) boxedOutput)) {
                        System.out.println("test failed for\n " + inputi
                                + " expected "
                                + Arrays.deepToString((Object[]) boxedE)
                                + ", output "
                                + Arrays.deepToString((Object[]) boxedOutput));
                        System.exit(-1);
                    }
                } else if (e != null && !e.equals(output)) {
                    System.out.println("test:\n " + inputi + " failed, "
                            + " expected " + e + ", output " + output);
                    System.exit(-1);
                } else if (e == null && e != output) {
                    System.out.println("test:\n " + inputi + " failed, "
                            + " expected " + e + ", output " + output);
                    System.exit(-1);
                }

            }
            System.out.println(SUCCESS);
        } catch (ClassNotFoundException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException
                | InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test method as if calling {@code className.methodName()} assuming the
     * tested method has only one parameter.
     *
     * @param <T>
     *            method input type
     * @param <R>
     *            method return type
     * @param className
     *            the class to be tested
     * @param methodName
     *            the method to be tested
     * @param tests
     *            a map containing the test inputs and expected results
     */
    public static <T, R> void assertEqual(String className, String methodName,
                                          Map<T, R> tests) {
        T[] inputs = (T[]) tests.keySet().toArray();
        R[] expected = (R[]) tests.values().toArray();
        assertEqual(className, methodName, inputs, expected);
    }

    /**
     * Test method as if calling {@code className.methodName()} assuming the
     * tested method has only one parameter.
     *
     * @param <T>
     *            method input type
     * @param <R>
     *            method return type
     * @param className
     *            the class to be tested
     * @param methodName
     *            the method to be tested
     * @param inputs
     *            a list containing all test cases' inputs
     * @param expected
     *            a list containing all expected results
     */
    public static <T, R> void assertEqual(String className, String methodName,
                                          List<T> inputs, List<R> expected) {
        T[] inputs1 = (T[]) inputs.toArray();
        R[] expected1 = (R[]) expected.toArray();
        assertEqual(className, methodName, inputs1, expected1);

    }

    /**
     * Test method as if calling {@code className.methodName()}, the method can
     * have multiple parameters.
     *
     * @param <R>
     *            method return type
     * @param className
     *            the class to be tested
     * @param methodName
     *            the method to be tested
     * @param inputs
     *            a list containing all test cases' inputs
     * @param expected
     *            a list containing all expected results
     */
    public static <R> void assertMPEqual(String className, String methodName,
                                         List<Object[]> inputs, List<R> expected) {
        Object[][] inputs1 = (Object[][]) inputs.toArray();
        R[] expected1 = (R[]) expected.toArray();
        assertMPEqual(className, methodName, inputs1, expected1);
    }

    /**
     * Test method as if calling {@code className.methodName()}, the method can
     * have multiple parameters.
     *
     * @param <R>
     *            method return type
     * @param className
     *            the class to be tested
     * @param methodName
     *            the method to be tested
     * @param inputs
     *            an array containing all test cases' inputs
     * @param expected
     *            an array containing all expected results
     */
    public static <R> void assertMPEqual(String className, String methodName,
                                         Object[][] inputs, R[] expected) {
        validateLengths(inputs, expected);
        try {
            Class<?> c = Class.forName(className);
            int n = inputs[0].length;
            Class<?>[] inputTypes = new Class<?>[n];
            for (int i = 0; i < n; i++) {
                inputTypes[i] = unboxing(inputs[0][i].getClass());
            }
            Method m = c.getMethod(methodName, inputTypes);
            Object o = c.newInstance();
            for (int i = 0; i < inputs.length; i++) {
                R e = expected[i];
                R output = (R) m.invoke(o, inputs[i]);
                String inputi = Arrays.deepToString(inputs[i]);
                if (e.getClass().isArray()) {
                    int n1 = Array.getLength(e);
                    Object boxedE = Array.newInstance(
                            boxing(e.getClass().getComponentType()), n1);
                    Object boxedOutput = Array.newInstance(
                            boxing(e.getClass().getComponentType()), n1);

                    for (int j = 0; j < n1; j++) {
                        Array.set(boxedE, j, Array.get(e, j));
                        Array.set(boxedOutput, j, Array.get(output, j));
                    }
                    if (!Arrays.deepEquals((Object[]) boxedE,
                            (Object[]) boxedOutput)) {
                        System.out.println("test failed for\n " + inputi
                                + " expected "
                                + Arrays.deepToString((Object[]) boxedE)
                                + ", output "
                                + Arrays.deepToString((Object[]) boxedOutput));
                        System.exit(-1);
                    }
                } else if (!e.equals(output)) {
                    System.out.println("test:\n " + inputi + " failed, "
                            + " expected " + e + ", output " + output);
                    System.exit(-1);
                }

            }
            System.out.println(SUCCESS);

        } catch (ClassNotFoundException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException
                | InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test method as if calling {@code className.methodName()} assuming the
     * tested method has multiple input parameters.
     *
     * @param <T>
     *            method input type
     * @param <R>
     *            method return type
     * @param className
     *            the class to be tested
     * @param methodName
     *            the method to be tested
     * @param tests
     *            a map containing the test inputs and expected results
     */
    public static <T, R> void assertMPEqual(String className, String methodName,
                                            Map<T, R> tests) {
        Object[][] inputs = (Object[][]) tests.keySet().toArray();
        R[] expected = (R[]) tests.values().toArray();
        assertMPEqual(className, methodName, inputs, expected);
    }

    private static <T, R> void validateLengths(T[] inputs, R[] expected) {
        if (inputs.length != expected.length) {
            System.out.println("inputs and expected lengths do not match.");
            System.exit(-1);
        }
    }

    private static Class<?> unboxing(Class<?> c) throws ClassNotFoundException {
        if (c == Class.forName("java.lang.Integer")) return int.class;
        if (c == Class.forName("[Ljava.lang.Integer;")) return int[].class;
        if (c == Class.forName("java.lang.Double")) return double.class;
        if (c == Class.forName("java.lang.Float")) return float.class;
        if (c == Class.forName("java.lang.Long")) return long.class;
        if (c == Class.forName("java.lang.Character")) return char.class;
        if (c == Class.forName("[Ljava.lang.Character;")) return char.class;
        if (c == Class.forName("java.lang.Short")) return short.class;
        return c;
    }

    private static Class<?> boxing(Class<?> c) throws ClassNotFoundException {
        if (c == int.class) return Class.forName("java.lang.Integer");
        if (c == int[].class) return Class.forName("[Ljava.lang.Integer;");
        if (c == double.class) return Class.forName("java.lang.Double");
        if (c == char.class) return Class.forName("java.lang.Character");
        if (c == char[].class) return Class.forName("[Ljava.lang.Character;");
        return c;
    }

    public static void main(String[] args) {
        try {
            System.out.println(Class.forName("[Ljava.lang.Integer;"));
            // System.out.println(Class.forName("int")); exception
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        int[] m = { 1, 2 };
        System.out.println(m.getClass());
        System.out.println(int.class);

        // (Integer[] )m; can't cast from int[] to Integer[]
    }
}
