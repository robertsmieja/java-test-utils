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

import org.junit.jupiter.api.Assertions;

/**
 * This class provides a public API to invoke the Equals tests.
 * <p>
 * It's main purpose is to provide a way to call the tests from JUnit 4 projects.
 */
public class EqualsUtils {

    //Protected instead of private due to code coverage
    protected EqualsUtils() {}

    /**
     * Runs all tests relating to Equals for the passed in objects
     *
     * @param value          An instance of the object under test
     * @param differentValue An instance of the object under test that contains different values in it's fields
     * @param <T>            The class under test
     */
    public static <T> void runAllEqualsTests(T value, T differentValue) {
        ensureDefaultEqualsIsNotUsed(value.getClass());
        ensureSameValuesAreEquals(value, differentValue);
        ensureDifferentValuesAreNotEquals(value, differentValue);
    }

    static <T> void ensureDefaultEqualsIsNotUsed(Class<T> classUnderTest) {
        Internal.doNotUseDefaultMethod(classUnderTest, "equals", Object.class);
    }

    static <T> void ensureSameValuesAreEquals(T value, T differentValue) {
        Assertions.assertEquals(value, value);
        Assertions.assertEquals(differentValue, differentValue);
    }

    static <T> void ensureDifferentValuesAreNotEquals(T value, T differentValue) {
        Assertions.assertNotEquals(value, differentValue);
        Assertions.assertNotEquals(differentValue, value);
    }
}
