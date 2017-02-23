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
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

import static com.robertsmieja.test.utils.junit.Internal.accessorMethodNameForField;
import static com.robertsmieja.test.utils.junit.Internal.findMethodForFieldOrFail;

class GettersAndSettersTestUtil {
    GettersAndSettersTestUtil() {
    }

    static <T> void runAllGettersAndSettersTests(Class<T> aClass, T value, T differentValue) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        List<Field> fieldsToTest = GettersAndSettersTestUtil.getFields(aClass);

        for (Field field : fieldsToTest) {
            ImmutablePair<Method, Method> getterAndSetter = GettersAndSettersTestUtil.getGetterAndSetterForField(aClass, field);
            Method getter = getterAndSetter.getLeft();
            Method setter = getterAndSetter.getRight();

            ensureFieldCanHandleDifferentValues(value, differentValue, getter, setter);
            ensureFieldCanHandleNullValues(value, getter, setter);
        }
    }

    @NotNull
    static ImmutablePair<Method, Method> getGetterAndSetterForField(@NotNull Class aClass, @NotNull Field field) {
        Method getter = MethodUtils.getAccessibleMethod(aClass, accessorMethodNameForField(GettersAndSettersTests.IS_METHOD_PREFIX, field));
        if (getter == null) {
            getter = findMethodForFieldOrFail(aClass, GettersAndSettersTests.GET_METHOD_PREFIX, field);
        }
        Method setter = Internal.findMethodForFieldOrFail(aClass, GettersAndSettersTests.SET_METHOD_PREFIX, field, field.getType());

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

    static <T> void ensureFieldCanHandleNullValues(@NotNull T value, @NotNull Method getter, @NotNull Method setter) throws IllegalAccessException, InvocationTargetException {
        Object newFieldValue;
        if (!getter.getReturnType().isPrimitive()) {
            setter.invoke(value, (Object) null);
            newFieldValue = getter.invoke(value);
            Assertions.assertNull(newFieldValue);
        }
    }

    static List<Field> getFields(@NotNull Class aClass) {
        List<Field> allFields = FieldUtils.getAllFieldsList(aClass);
        List<Field> excludedFields = FieldUtils.getFieldsListWithAnnotation(aClass, IgnoreForTests.class);
        return allFields.stream()
                .filter(field -> !field.isSynthetic())
                .filter(field -> !excludedFields.contains(field))
                .collect(Collectors.toList());
    }
}
