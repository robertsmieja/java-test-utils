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

import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * This is an internal class that should not be used.
 * <p>
 * This class solely holds static methods, in order to reduce code duplication.
 *
 * @since 0.1.0
 */
class Internal {
    Internal() {
    } //package default for code coverage

    @NotNull
    static <T> T createObjectFromDefaultConstructor(Class<T> tClass) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<T> constructor = ConstructorUtils.getAccessibleConstructor(tClass);
        return constructor.newInstance();
    }

    static void doNotUseDefaultMethod(Class aClass, String methodName, Class... parameterTypes) {
        Method methodToCheck = MethodUtils.getAccessibleMethod(aClass, methodName, parameterTypes);
        Assertions.assertNotEquals(Object.class, methodToCheck.getDeclaringClass(), methodName + "() method not implemented");
    }

    static void failToFindMethodForField(Field field, String desiredMethodName) {
        Assertions.fail("Unable to find <" + desiredMethodName + "> for field <" + field + ">");
    }

}
