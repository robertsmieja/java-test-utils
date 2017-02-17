package com.robertsmieja.test.utils.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

public interface EqualsTests<T> extends TestProducer<T> {

    @Test
    @DisplayName("Same value should be equal")
    default void sameValueShouldBeEqual() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Assertions.assertEquals(createValue(), createValue());
        Assertions.assertEquals(createDifferentValue(), createDifferentValue());
    }

    @Test
    @DisplayName("Different values should not be equal")
    default void differentValuesShouldNotBeEquals() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Assertions.assertNotEquals(createValue(), createDifferentValue());
        Assertions.assertNotEquals(createDifferentValue(), createValue());
        Assertions.assertNotEquals(null, createValue());
        Assertions.assertNotEquals(null, createDifferentValue());
    }

    @Test
    @DisplayName("Do not use default equals()")
    default void doNotUseDefaultEquals() {
        Internal.doNotUseDefaultMethod(getTypeClass(), "equals", Object.class);
    }
}
