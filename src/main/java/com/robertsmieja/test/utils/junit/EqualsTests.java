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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.robertsmieja.test.utils.junit.EqualsUtils.*;

/**
 * A set of tests for the equals() method.
 * Contains the following tests:
 * <ul>
 * <li> A test that checks if the default equals() implementation is used, and fails if it is </li>
 * <li> A test to make sure instances of the same values are equal to each other </li>
 * <li> A test to make sure different values are not equal to each other </li>
 * </ul>
 *
 * @param <T> The class under test
 * @since 0.1.0
 */
public interface EqualsTests<T> extends ObjectInstantiatorForTests<T> {
    @Test
    @DisplayName("Do not use default equals()")
    default void doNotUseDefaultEquals() {
        ensureDefaultEqualsIsNotUsed(getClassUnderTest());
    }

    @Test
    @DisplayName("Same value should be equal")
    default void sameValueShouldBeEqual() throws ObjectFactoryException {
        ensureSameValuesAreEquals(createValue(), createDifferentValue());
    }

    @Test
    @DisplayName("Different values should not be equal")
    default void differentValuesShouldNotBeEquals() throws ObjectFactoryException {
        ensureDifferentValuesAreNotEquals(createValue(), createDifferentValue());
    }
}
