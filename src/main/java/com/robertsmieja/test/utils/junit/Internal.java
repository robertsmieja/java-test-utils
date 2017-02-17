package com.robertsmieja.test.utils.junit;

import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * To make sure that the behavior of both createValue() methods in the interface are independent of each other, we need
 * a "helper" method.
 */
public class Internal {
    private Internal() {
    }

    static <T> T defaultCreateValueImplementation(Class<T> tClass) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<T> constructor = ConstructorUtils.getAccessibleConstructor(tClass);
        return constructor.newInstance();
    }

    static void doNotUseDefaultMethod(Class aClass, String methodName) {
        doNotUseDefaultMethod(aClass, methodName, (Class[]) null);
    }

    static void doNotUseDefaultMethod(Class aClass, String methodName, Class... parameterTypes) {
        Method methodToCheck = MethodUtils.getAccessibleMethod(aClass, methodName, parameterTypes);
        Assertions.assertNotEquals(Object.class, methodToCheck.getDeclaringClass(), methodName + "() method not implemented");
    }
}
