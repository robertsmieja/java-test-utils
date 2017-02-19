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

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static com.robertsmieja.test.utils.junit.GettersAndSettersTestUtil.ensureFieldCanHandleNullValues;

/**
 * A set of tests surrounding getter and setter methods.
 * Contains the following tests:
 *  - A test to make sure all getters will return the value that was passed into set, and handle null values correctly
 *
 * @param <T> The class under test
 */
public interface GettersAndSettersTests<T> extends TestProducer<T> {

    String GET_METHOD_PREFIX = "get";
    String IS_METHOD_PREFIX = "is";
    String SET_METHOD_PREFIX = "set";

    @Test
    @DisplayName("Test getters and setters")
    default void testGettersAndSetters() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Class aClass = getTypeClass();
        List<Field> fieldsToTest = GettersAndSettersTestUtil.getFields(aClass);

        T value = createValue();
        T differentValue = createDifferentValue();

        for (Field field : fieldsToTest) {
            ImmutablePair<Method, Method> getterAndSetter = GettersAndSettersTestUtil.getGetterAndSetterForField(aClass, field);
            Method getter = getterAndSetter.getLeft();
            Method setter = getterAndSetter.getRight();

            Object originalFieldValue = getter.invoke(value);
            Object differentFieldValue = getter.invoke(differentValue);

            setter.invoke(value, differentFieldValue);
            Object newFieldValue = getter.invoke(value);

            Assertions.assertNotEquals(originalFieldValue, newFieldValue);

            setter.invoke(value, originalFieldValue);
            newFieldValue = getter.invoke(value);
            Assertions.assertEquals(originalFieldValue, newFieldValue);

            ensureFieldCanHandleNullValues(value, getter, setter);
        }
    }

}
