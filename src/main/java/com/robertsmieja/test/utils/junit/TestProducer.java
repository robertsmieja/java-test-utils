package com.robertsmieja.test.utils.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static com.robertsmieja.test.utils.junit.Internal.defaultCreateValueImplementation;

public interface TestProducer<T> {
    Class<T> getTypeClass();

    default T createValue() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        return defaultCreateValueImplementation(getTypeClass());
    }

    default T createDifferentValue() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        return defaultCreateValueImplementation(getTypeClass());
    }

    @Test
    @DisplayName("Can create values successfully")
    default void canCreateValuesSuccessfully() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        T value = createValue();
        T differentValue = createDifferentValue();

        Assertions.assertNotNull(value);
        Assertions.assertNotNull(differentValue);
    }
}
