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

public class ToStringUtils {
    public static <T> void runAllToStringTests(T value, T differentValue) {
        ensureDefaultToStringIsNotUsed(value.getClass());
        ensureDifferentValuesHaveDifferentToStrings(value, differentValue);
        ensureSameValuesHaveSameToStrings(value, differentValue);
    }

    static <T> void ensureDefaultToStringIsNotUsed(Class<T> classUnderTest){
        Internal.doNotUseDefaultMethod(classUnderTest, "toString");
    }

    static <T> void ensureDifferentValuesHaveDifferentToStrings(T value, T differentValue) {
        assertNotEquals(value.toString(), differentValue.toString());
    }

    static <T> void ensureSameValuesHaveSameToStrings(T value, T differentValue) {
        assertEquals(value.toString(), value.toString());
        assertEquals(differentValue.toString(), differentValue.toString());
    }
}
