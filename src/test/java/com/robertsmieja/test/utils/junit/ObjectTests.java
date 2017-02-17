package com.robertsmieja.test.utils.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.lang.reflect.InvocationTargetException;

public class ObjectTests implements EqualsTests<Object>, ToStringTests<Object>, HashCodeTests<Object>, GettersAndSettersTests<Object> {

    Object value = new Object();
    Object differentValue = new Object();

    @Override
    public Class<Object> getTypeClass() {
        return Object.class;
    }

    @Override
    public Object createValue() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        return value;
    }

    @Override
    public Object createDifferentValue() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        return differentValue;
    }

    //These are expected failures
    @Override
    @Test
    public void doNotUseDefaultHashCode() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        try {
            HashCodeTests.super.doNotUseDefaultHashCode();
            Assertions.fail("Expected AssertionFailedError!");
        } catch (AssertionFailedError expectedException){
            Assertions.assertEquals("hashCode() method not implemented ==> expected: not equal but was: <class java.lang.Object>", expectedException.getMessage());
        }
    }

    @Override
    @Test
    public void doNotUseDefaultToString() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        try {
            ToStringTests.super.doNotUseDefaultToString();
            Assertions.fail("Expected AssertionFailedError!");
        } catch (AssertionFailedError expectedException){
            Assertions.assertEquals("toString() method not implemented ==> expected: not equal but was: <class java.lang.Object>", expectedException.getMessage());
        }
    }

    @Override
    @Test
    public void doNotUseDefaultEquals() {
        try {
            EqualsTests.super.doNotUseDefaultEquals();
            Assertions.fail("Expected AssertionFailedError!");
        } catch (AssertionFailedError expectedException){
            Assertions.assertEquals("equals() method not implemented ==> expected: not equal but was: <class java.lang.Object>", expectedException.getMessage());
        }
    }
}