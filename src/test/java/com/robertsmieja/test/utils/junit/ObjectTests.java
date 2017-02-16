package com.robertsmieja.test.utils.junit;

import java.lang.reflect.InvocationTargetException;

public class ObjectTests implements EqualsTests<Object>, ToStringTests<Object>, HashCodeTests<Object> {

    @Override
    public Class<Object> getTypeClass() {
        return Object.class;
    }

    @Override
    public Object createValue() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        return new Object();
    }

    @Override
    public Object createDifferentValue() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        return null;
    }
}