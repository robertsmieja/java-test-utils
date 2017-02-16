package com.robertsmieja.test.utils.junit;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public interface HashCodeTests<T> extends TestProducer<T> {

    @Test
    default void isNotUsingDefaultHashCodeImplementation() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        T value = createValue();
        Object valueAsObject = (Object)value;

        assertNotEquals(valueAsObject.hashCode(), value.hashCode());

        T differentValue = createDifferentValue();
        Object differentValueAsObject = (Object)differentValue;

        assertNotEquals(differentValueAsObject.hashCode(), differentValue.hashCode());
    }

    @Test
    default void differentValuesHaveDifferentHashCodes() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        T value = createValue();
        T differentValue = createDifferentValue();

        assertNotEquals(createDifferentValue().hashCode(), createValue());
    }
}
