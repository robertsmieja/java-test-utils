package com.robertsmieja.test.utils.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

public interface EqualsTests<T> extends TestProducer<T> {

    @Test
    default void testEquals() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Assertions.assertEquals(createValue(), createValue());
        Assertions.assertEquals(createDifferentValue(), createDifferentValue());
    }

    @Test
    default void testNotEquals(Object firstObject, Object secondObject) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Assertions.assertNotEquals(createValue(), createDifferentValue());
        Assertions.assertNotEquals(createDifferentValue(), createValue());
        Assertions.assertNotEquals(null, createValue());
        Assertions.assertNotEquals(null, createDifferentValue());
    }
}
