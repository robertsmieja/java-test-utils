package com.robertsmieja.test.utils.junit;

import org.apache.commons.lang3.reflect.ConstructorUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * To make sure that the behavior of both createValue() methods in the interface are independent of each other, we need
 * a "helper" method.
 */
public class Internal {
    private Internal(){}

    static<T> T defaultCreateValueImplementation(Class<T> tClass) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<T> constructor = ConstructorUtils.getAccessibleConstructor(tClass);
        return constructor.newInstance();
    }
}
