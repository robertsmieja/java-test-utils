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

import com.robertsmieja.test.utils.junit.domain.ReadOnlyObject;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * This test class covers instances where there are no setters for fields
 */
public class ReadOnlyObjectTests implements GettersAndSettersTests<ReadOnlyObject> {
    @Override
    public ReadOnlyObject createValue() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        return new ReadOnlyObject(1, "coolData");
    }

    @Override
    public ReadOnlyObject createDifferentValue() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        return new ReadOnlyObject(2, "lessCoolData");
    }

    @Override
    @Test
    public void testGettersAndSetters() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        try {
            GettersAndSettersTests.super.testGettersAndSetters();
            fail("Expected an AssertionFailedError");
        } catch (AssertionFailedError assertionFailedError) {
            assertEquals("Unable to find <setId> for field <private long com.robertsmieja.test.utils.junit.domain.ReadOnlyObject.id>", assertionFailedError.getMessage());
        }
    }
}
