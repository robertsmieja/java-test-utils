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
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface GettersAndSettersTests<T> extends TestProducer<T> {

    String GET_PREFIX = "get";
    String SET_PREFIX = "set";
    String SYNTHETIC_FIELD_PREFIX = "$";

    @Test
    @DisplayName("Test getters and setters")
    default void testGettersAndSetters() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        List<Field> allFields = FieldUtils.getAllFieldsList(getTypeClass());
        List<Field> excludedFields = FieldUtils.getFieldsListWithAnnotation(getTypeClass(), IgnoreForTests.class);
        List<Field> fieldsToTest = allFields.stream()
                .filter(field -> !StringUtils.startsWith(field.getName(), SYNTHETIC_FIELD_PREFIX))
                .filter(field -> !excludedFields.contains(field))
                .collect(Collectors.toList());

        List<Triple<Field, Method, Method>> listOfFieldGetterSetter = new ArrayList<>();
        T value = createValue();
        T differentValue = createDifferentValue();

        for (Field field : fieldsToTest) {
            String capitalizedFieldName = StringUtils.capitalize(field.getName());
            Method getter = MethodUtils.getAccessibleMethod(getTypeClass(), GET_PREFIX + capitalizedFieldName);
            if (getter == null) {
                Assertions.fail("Unable to find getter <" + GET_PREFIX + capitalizedFieldName + "> for field <" + field + ">");
            }
            Method setter = MethodUtils.getAccessibleMethod(getTypeClass(), SET_PREFIX + capitalizedFieldName, field.getType());
            if (setter == null) {
                Assertions.fail("Unable to find getter <" + SET_PREFIX + capitalizedFieldName + "> for field <" + field + ">");
            }
            listOfFieldGetterSetter.add(new ImmutableTriple<>(field, getter, setter));

            Object originalFieldValue = getter.invoke(value);
            Object differentFieldValue = getter.invoke(differentValue);

            setter.invoke(value, differentFieldValue);
            Object newFieldValue = getter.invoke(value);

            Assertions.assertNotEquals(originalFieldValue, newFieldValue);

            setter.invoke(value, originalFieldValue);
            newFieldValue = getter.invoke(value);
            Assertions.assertEquals(originalFieldValue, newFieldValue);

            if (!getter.getReturnType().isPrimitive()) {
                setter.invoke(value, (Object) null);
                newFieldValue = getter.invoke(value);
                Assertions.assertNull(newFieldValue);
            }
        }
    }
}
