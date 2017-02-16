package com.robertsmieja.test.utils.junit;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public interface ToStringTests<T> extends TestProducer<T> {

    @Test
    @DisplayName("Different values should have different toString() results")
    default void differentValuesShouldHaveDifferentToStringResults() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        assertNotEquals(createValue().toString(), createDifferentValue().toString());
    }

    @Test
    @DisplayName("Do not use default toString()")
    default void doNotUseDefaultToString() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Internal.doNotUseDefaultMethod(getTypeClass(), "toString");
    }
}
