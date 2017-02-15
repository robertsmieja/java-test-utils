package com.robertsmieja.test.utils.junit;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public interface ToStringTests<T> extends TestProducer<T> {

    @Test
    default void differentValuesShouldHaveDifferentToStrings() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        assertNotEquals(createValue().toString(), createDifferentValue().toString());
    }

    @Test
    default void doNotUseDefaultToString() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        T value = createValue();
        Object valueAsObject = (Object)value;

        assertNotEquals(valueAsObject.toString(), value.toString());

        T differentValue = createDifferentValue();
        Object differentValueAsObject = (Object)differentValue;

        assertNotEquals(differentValueAsObject.toString(), differentValue.toString());
    }
}
