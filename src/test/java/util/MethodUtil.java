package util;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility reflection methods on methods.
 *
 * @author Zexi Jesse Zhuang
 */
public class MethodUtil {

    /**
     * Get all declared  public methods of an object.
     *
     * @param object to get methods for.
     * @return a list of public {@link Method} of the object.
     */
    public static List<Method> getDeclaredPublicMethods(Object object) {
        List<Method> allMethods = getAllDeclaredMethods(object);
        List<Method> result = new ArrayList<>();
        for (Method method : allMethods)
            if (Modifier.isPublic(method.getModifiers())) result.add(method);
        return result;
    }

    private static List<Method> getAllDeclaredMethods(Object object) {
        return Arrays.asList(object.getClass().getDeclaredMethods());
    }
}
