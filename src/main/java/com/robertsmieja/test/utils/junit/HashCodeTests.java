package com.robertsmieja.test.utils.junit;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public interface HashCodeTests<T> extends TestProducer<T> {

    @Test
    @DisplayName("Do not use default hashCode()")
    default void doNotUseDefaultHashCode() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Internal.doNotUseDefaultMethod(getTypeClass(), "hashCode");
    }

    @Test
    @DisplayName("Different values have different hashCode() results")
    default void differentValuesHaveDifferentHashCodeResults() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Assertions.assertNotEquals(createDifferentValue().hashCode(), createValue().hashCode());
    }
}
