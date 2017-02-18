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

package com.robertsmieja.test.utils.junit.domain;

import com.robertsmieja.test.utils.junit.TestProducer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

public class TestProducerTests implements TestProducer<SimplePojo>{
    @Override
    public Class<SimplePojo> getTypeClass() {
        return SimplePojo.class;
    }

    @Test
    @Tag("Default values are created successfully")
    public void defaultValuesAreCreatedSuccessfully() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        SimplePojo value = createValue();
        SimplePojo differentValue = createDifferentValue();

        Assertions.assertEquals(value, new SimplePojo());
        Assertions.assertEquals(differentValue, value);
        Assertions.assertNotSame(differentValue, value);
        Assertions.assertNotSame(value, createValue());
        Assertions.assertNotSame(differentValue, createDifferentValue());
    }
}
