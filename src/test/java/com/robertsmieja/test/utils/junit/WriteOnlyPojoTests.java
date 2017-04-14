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

import com.robertsmieja.test.utils.junit.pojos.WriteOnlyPojo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WriteOnlyPojoTests {

    @Test
    @DisplayName("Exception expected when unable to find Getter for Field")
    public void exceptionExpectedWhenUnableToFindGetterForField() throws InvocationTargetException, IllegalAccessException {
        WriteOnlyPojo value = new WriteOnlyPojo();
        AssertionFailedError error = assertThrows(AssertionFailedError.class, () -> GettersAndSettersUtils.runGettersAndSettersTestOnField(value, value, "data"));
        assertEquals("Unable to find <getData> for field <public java.lang.String com.robertsmieja.test.utils.junit.pojos.WriteOnlyPojo.data>", error.getMessage());
    }
}
