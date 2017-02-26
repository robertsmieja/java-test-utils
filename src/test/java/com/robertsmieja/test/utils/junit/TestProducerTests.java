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

import com.robertsmieja.test.utils.junit.domain.SimplePojo;
import com.robertsmieja.test.utils.junit.exceptions.ObjectFactoryException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestProducerTests implements TestProducer<SimplePojo> {

    @Override
    public Class<SimplePojo> getClassOfGenericTypeArgument() {
        return SimplePojo.class;
    }

    @Test
    @DisplayName("Default values are created successfully")
    public void defaultValuesAreCreatedSuccessfully() throws ObjectFactoryException {
        SimplePojo value = createValue();
        SimplePojo differentValue = createDifferentValue();
        SimplePojo valueFromFactory = objectFactory.getInstanceOfClass(SimplePojo.class);
        SimplePojo differentValueFromFactory = objectFactory.getInstanceOfClassWithDifferentValues(SimplePojo.class);

        assertEquals(value, valueFromFactory);
        assertEquals(differentValue, differentValueFromFactory);
        assertSame(value, valueFromFactory);
        assertSame(differentValue, differentValueFromFactory);
        assertNotEquals(value, differentValue);
        assertNotEquals(differentValue, value);
    }
}
