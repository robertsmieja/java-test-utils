/*
 *    Copyright 2017 Robert Smieja
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.robertsmieja.test.utils.junit;

import com.robertsmieja.test.utils.junit.exceptions.ObjectFactoryException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.lang.reflect.InvocationTargetException;

public class ObjectTests implements EqualsTests<Object>, ToStringTests<Object>, HashCodeTests<Object>, GettersAndSettersTests<Object> {

    Object value = new Object();
    Object differentValue = new Object();

    @Override
    public Object createValue() throws ObjectFactoryException {
        return value;
    }

    @Override
    public Object createDifferentValue() throws ObjectFactoryException {
        return differentValue;
    }

    //These are expected failures
    @Override
    @Test
    public void doNotUseDefaultHashCode() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        try {
            HashCodeTests.super.doNotUseDefaultHashCode();
            Assertions.fail("Expected AssertionFailedError!");
        } catch (AssertionFailedError expectedException) {
            Assertions.assertEquals("hashCode() method not implemented ==> expected: not equal but was: <class java.lang.Object>", expectedException.getMessage());
        }
    }

    @Override
    @Test
    public void doNotUseDefaultToString() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        try {
            ToStringTests.super.doNotUseDefaultToString();
            Assertions.fail("Expected AssertionFailedError!");
        } catch (AssertionFailedError expectedException) {
            Assertions.assertEquals("toString() method not implemented ==> expected: not equal but was: <class java.lang.Object>", expectedException.getMessage());
        }
    }

    @Override
    @Test
    public void doNotUseDefaultEquals() {
        try {
            EqualsTests.super.doNotUseDefaultEquals();
            Assertions.fail("Expected AssertionFailedError!");
        } catch (AssertionFailedError expectedException) {
            Assertions.assertEquals("equals() method not implemented ==> expected: not equal but was: <class java.lang.Object>", expectedException.getMessage());
        }
    }
}