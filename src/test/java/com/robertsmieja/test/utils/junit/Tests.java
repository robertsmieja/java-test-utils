package com.robertsmieja.test.utils.junit;

import java.lang.reflect.InvocationTargetException;

public class Tests implements EqualsTests<SimplePojo>, HashCodeTests<SimplePojo>, ToStringTests<SimplePojo>, GettersAndSettersTests<SimplePojo> {
    @Override
    public Class<SimplePojo> getTypeClass() {
        return SimplePojo.class;
    }

    @Override
    public SimplePojo createValue() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        SimplePojo pojo = new SimplePojo();
        pojo.setIntegerValue(99);
        pojo.setLongValue(22L);
        pojo.setStringValue("CoolString");
        return pojo;
    }

    @Override
    public SimplePojo createDifferentValue() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        SimplePojo pojo = new SimplePojo();
        pojo.setIntegerValue(22);
        pojo.setLongValue(99L);
        pojo.setStringValue("LessCoolString");
        return pojo;
    }
}