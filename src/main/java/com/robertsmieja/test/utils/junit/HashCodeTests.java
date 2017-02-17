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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

public interface HashCodeTests<T> extends TestProducer<T> {

    @Test
    @DisplayName("Do not use default hashCode()")
    default void doNotUseDefaultHashCode() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Internal.doNotUseDefaultMethod(getTypeClass(), "hashCode");
    }

    @Test
    @DisplayName("Different values have different hashCode() results")
    default void differentValuesHaveDifferentHashCodeResults() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Assertions.assertNotEquals(createDifferentValue().hashCode(), createValue().hashCode());
    }
}
