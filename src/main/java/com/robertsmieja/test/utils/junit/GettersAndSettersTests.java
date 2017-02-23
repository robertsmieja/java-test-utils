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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static com.robertsmieja.test.utils.junit.GettersAndSettersTestUtil.runAllGettersAndSettersTests;

/**
 * A set of tests surrounding getter and setter methods.
 * Contains the following tests:
 * <ul>
 * <li> A test to make sure all getters will return the value that was passed into set, and handle null values correctly </li>
 * </ul>
 *
 * @param <T> The class under test
 *
 * @since 1.0.0
 */
public interface GettersAndSettersTests<T> extends TestProducer<T> {

    String GET_METHOD_PREFIX = "get";
    String IS_METHOD_PREFIX = "is";
    String SET_METHOD_PREFIX = "set";

    @Test
    @DisplayName("Test getters and setters")
    default void testGettersAndSetters() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        runAllGettersAndSettersTests(getTypeClass(), createValue(), createDifferentValue());
    }
}
