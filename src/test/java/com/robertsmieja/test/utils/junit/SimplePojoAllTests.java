package com.robertsmieja.test.utils.junit;

import java.lang.reflect.InvocationTargetException;

public class SimplePojoAllTests implements AllBasicTests<SimplePojo> {
    @Override
    public Class<SimplePojo> getTypeClass() {
        return SimplePojo.class;
    }

    @Override
    public SimplePojo createValue() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        return SimplePojo.createValue();
    }

    @Override
    public SimplePojo createDifferentValue() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        return SimplePojo.createDifferentValue();
    }
}