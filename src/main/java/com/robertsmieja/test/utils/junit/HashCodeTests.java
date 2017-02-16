package com.robertsmieja.test.utils.junit;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public interface HashCodeTests<T> extends TestProducer<T> {

    @Test
    default void isNotUsingDefaultHashCodeImplementation() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<T> tClass = getTypeClass();

        Method hashCodeMethod = MethodUtils.getAccessibleMethod(tClass, "hashCode");
        Assertions.assertNotNull(hashCodeMethod, "hashCode() method not implemented");
    }

    @Test
    default void differentValuesHaveDifferentHashCodes() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Assertions.assertNotEquals(createDifferentValue().hashCode(), createValue().hashCode());
    }
}
