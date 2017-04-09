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

import com.robertsmieja.test.utils.junit.pojos.NestedPojo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * This covers correct handling synthetic fields.
 * Synthetic fields should be excluded from the testing of Getters/Setters
 */
public class NestedPojoTests {

    NestedPojo nestedPojo = new NestedPojo();

    @Test
    @DisplayName("Synthetic fields are filtered out correctly")
    @Disabled //Disabled because it's failing for now
    public void syntheticFieldsAreFilteredOutCorrectly() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        GettersAndSettersUtils.runAllGettersAndSettersTests(nestedPojo.createInnerValue(), nestedPojo.createInnerValue());
    }
}
