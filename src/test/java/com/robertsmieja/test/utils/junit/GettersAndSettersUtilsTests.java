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
import org.opentest4j.AssertionFailedError;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class GettersAndSettersUtilsTests {
    @Test
    @DisplayName("Test constructor")
    public void testConstructor() {
        GettersAndSettersUtils objectUnderTest = new GettersAndSettersUtils();
        assertNotNull(objectUnderTest);
    }

    @Test
    @DisplayName("Trying to test a non-existent field returns the correct exception")
    public void tryingToTestANonExistentFieldReturnsTheCorrectException() throws InvocationTargetException, IllegalAccessException {
        AssertionFailedError error = assertThrows(AssertionFailedError.class, () -> GettersAndSettersUtils.runGettersAndSettersTestOnField(new Object(), new Object(), "foo"));
        assertEquals("Field <foo> not found on <class java.lang.Object>", error.getMessage());
    }
}
