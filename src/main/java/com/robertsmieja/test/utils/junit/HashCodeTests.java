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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static com.robertsmieja.test.utils.junit.HashCodeUtils.ensureDefaultHashCodeIsNotUsed;
import static com.robertsmieja.test.utils.junit.HashCodeUtils.ensureDifferentValuesReturnDifferentHashCodes;
import static com.robertsmieja.test.utils.junit.HashCodeUtils.ensureSameValuesReturnSameHashCode;

/**
 * A set of tests for the hashCode() method.
 * Contains the following tests:
 * <ul>
 * <li> A test that checks if the default hashCode() implementation is used, and fails if it is </li>
 * <li> A test to make sure instances of the same values return the same hashCodes </li>
 * <li> A test to make sure different values return different hashCodes </li>
 * </ul>
 *
 * @param <T> The class under test
 * @since 0.1.0
 */
public interface HashCodeTests<T> extends ObjectInstantiatorForTests<T> {

    @Test
    @DisplayName("Do not use default hashCode()")
    default void doNotUseDefaultHashCode() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        ensureDefaultHashCodeIsNotUsed(getClassOfGenericTypeArgument());
    }

    @Test
    @DisplayName("Same values have different hashCode() results")
    default void sameValuesHaveDifferentHashCodeResults() throws ObjectFactoryException {
        ensureSameValuesReturnSameHashCode(createValue(), createDifferentValue());
    }

    @Test
    @DisplayName("Different values have different hashCode() results")
    default void differentValuesHaveDifferentHashCodeResults() throws ObjectFactoryException {
        ensureDifferentValuesReturnDifferentHashCodes(createValue(), createDifferentValue());
    }
}
