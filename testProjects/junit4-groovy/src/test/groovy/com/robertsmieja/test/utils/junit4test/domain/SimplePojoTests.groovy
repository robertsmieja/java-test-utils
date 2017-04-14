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
package com.robertsmieja.test.utils.junit4test.domain;

import com.robertsmieja.test.utils.junit.GettersAndSettersUtils;
import com.robertsmieja.test.utils.junit4test.domain.factory.SimplePojoFactory;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class SimplePojoTests {

    SimplePojo value;
    SimplePojo differentValue;

    @Before
    public void setup(){
        value = SimplePojoFactory.createValue();
        differentValue = SimplePojoFactory.createDifferentValue();
    }

    @Test
    public void testGettersAndSetters() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        GettersAndSettersUtils.runAllGettersAndSettersTests(value, differentValue);
    }

}