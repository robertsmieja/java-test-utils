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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * This class provides a public API to invoke the GettersAndSetters tests.
 * <p>
 * It's main purpose is to provide a way to call the tests from JUnit 4 projects.
 */
public class HashCodeUtils {
    /**
     * Runs all tests relating to HashCode for the passed in objects
     *
     * @param value          An instance of the object under test
     * @param differentValue An instance of the object under test that contains different values in it's fields
     * @param <T>            The class under test
     */
    public static <T> void runAllHashCodeTests(T value, T differentValue) {
        ensureDefaultHashCodeIsNotUsed(value.getClass());
        ensureSameValuesReturnSameHashCode(value, differentValue);
        ensureDifferentValuesReturnDifferentHashCodes(value, differentValue);
    }

    static <T> void ensureDefaultHashCodeIsNotUsed(Class<T> classUnderTest) {
        Internal.doNotUseDefaultMethod(classUnderTest, "hashCode");
    }

    static <T> void ensureSameValuesReturnSameHashCode(T value, T differentValue) {
        assertEquals(value.hashCode(), value.hashCode());
        assertEquals(differentValue.hashCode(), differentValue.hashCode());
    }

    static <T> void ensureDifferentValuesReturnDifferentHashCodes(T value, T differentValue) {
        assertNotEquals(value.hashCode(), differentValue.hashCode());
    }

}
