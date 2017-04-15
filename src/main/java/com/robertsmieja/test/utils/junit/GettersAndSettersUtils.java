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

import com.robertsmieja.test.utils.junit.annotations.IgnoreForTests;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

import static com.robertsmieja.test.utils.junit.Internal.failToFindMethodForField;

/**
 * This class provides a public API to invoke the GettersAndSetters tests.
 * <p>
 * It's main purpose is to provide a way to call the tests from JUnit 4 projects.
 */
public class GettersAndSettersUtils {
    GettersAndSettersUtils() {
    }

    /**
     * Runs all tests relating to Getters and Setters for the passed in objects
     *
     * @param value          An instance of the object under test
     * @param differentValue An instance of the object under test that contains different values in it's fields
     * @param <T>            The class under test
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     */
    public static <T> void runAllGettersAndSettersTests(T value, T differentValue) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<T> classUnderTest = (Class<T>) value.getClass();
        List<Field> fieldsToTest = GettersAndSettersUtils.getFields(classUnderTest);

        for (Field field : fieldsToTest) {
            runGettersAndSettersTestOnField(value, differentValue, field);
        }
    }

    static List<Field> getFields(Class<?> aClass) {
        List<Field> allFields = FieldUtils.getAllFieldsList(aClass);
        List<Field> excludedFields = FieldUtils.getFieldsListWithAnnotation(aClass, IgnoreForTests.class);
        return allFields.stream()
                .filter(field -> !field.isSynthetic())
                .filter(field -> !excludedFields.contains(field))
                .collect(Collectors.toList());
    }

    static <T> void runGettersAndSettersTestOnField(T value, T differentValue, Field field) throws InvocationTargetException, IllegalAccessException {
        ImmutablePair<Method, Method> getterAndSetter = GettersAndSettersUtils.getGetterAndSetterForField(field);
        Method getter = getterAndSetter.getLeft();
        Method setter = getterAndSetter.getRight();

        ensureFieldCanHandleDifferentValues(value, differentValue, getter, setter);
        ensureFieldCanHandleNullValues(value, getter, setter);
    }
    
    static ImmutablePair<Method, Method> getGetterAndSetterForField(Field field) {
        Method getter = MethodUtils.getAccessibleMethod(field.getDeclaringClass(), accessorMethodNameForField(GettersAndSettersTests.IS_METHOD_PREFIX, field));
        if (getter == null) {
            getter = MethodUtils.getAccessibleMethod(field.getDeclaringClass(), accessorMethodNameForField(GettersAndSettersTests.GET_METHOD_PREFIX, field));
        }
        if (getter == null) {
            failToFindMethodForField(field, accessorMethodNameForField(GettersAndSettersTests.GET_METHOD_PREFIX, field));
        }
        Method setter = getSetterForField(field);
        if (setter == null) {
            failToFindMethodForField(field, accessorMethodNameForField(GettersAndSettersTests.SET_METHOD_PREFIX, field));
        }
        return new ImmutablePair<>(getter, setter);
    }

    static <T> void ensureFieldCanHandleDifferentValues(T value, T differentValue, Method getter, Method setter) throws IllegalAccessException, InvocationTargetException {
        Object originalFieldValue = getter.invoke(value);
        Object differentFieldValue = getter.invoke(differentValue);

        setter.invoke(value, differentFieldValue);
        Object newFieldValue = getter.invoke(value);

        Assertions.assertNotEquals(originalFieldValue, newFieldValue);

        setter.invoke(value, originalFieldValue);
        newFieldValue = getter.invoke(value);
        Assertions.assertEquals(originalFieldValue, newFieldValue);
    }

    static <T> void ensureFieldCanHandleNullValues(T value, Method getter, Method setter) throws IllegalAccessException, InvocationTargetException {
        Object newFieldValue;
        if (!getter.getReturnType().isPrimitive()) {
            setter.invoke(value, (Object) null);
            newFieldValue = getter.invoke(value);
            Assertions.assertNull(newFieldValue);
        }
    }

    static String accessorMethodNameForField(String accessorPrefix, Field field) {
        return accessorPrefix + StringUtils.capitalize(field.getName());
    }

    static Method getSetterForField(Field field) {
        return MethodUtils.getAccessibleMethod(field.getDeclaringClass(), accessorMethodNameForField(GettersAndSettersTests.SET_METHOD_PREFIX, field), field.getType());
    }

    /**
     * Run the tests only on one specific field for the passed in objects
     *
     * @param value          An instance of the object under test
     * @param differentValue An instance of the object under test that contains different values in it's fields
     * @param fieldName      The name of hte field to test
     * @param <T>            The class under test
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static <T> void runGettersAndSettersTestOnField(T value, T differentValue, String fieldName) throws InvocationTargetException, IllegalAccessException {
        Class<?> aClass = value.getClass();
        Field field = FieldUtils.getField(aClass, fieldName, true);
        if (field == null) {
            Assertions.fail("Field <" + fieldName + "> not found on <" + aClass + ">");
        }
        runGettersAndSettersTestOnField(value, differentValue, field);
    }
}
