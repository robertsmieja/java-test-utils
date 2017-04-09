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
import com.robertsmieja.test.utils.junit.pojos.ComplexPojo;
import com.robertsmieja.test.utils.junit.pojos.ReadOnlyPojo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComplexPojoTests implements ObjectInstantiatorForTests<ComplexPojo> {

    private static ReadOnlyPojo value;
    private static ReadOnlyPojo differentValue;
    private static boolean previousCacheInstancesValue = true;

    @BeforeAll
    static public void testSetup() {
        GenericObjectFactory genericObjectFactory = (GenericObjectFactory) objectFactory;
        previousCacheInstancesValue = genericObjectFactory.isCacheInstances();
        genericObjectFactory.setCacheInstances(false);

        value = new ReadOnlyPojo(1, "foo");
        differentValue = new ReadOnlyPojo(2, "bar");
        objectFactory.registerClassAndValues(ReadOnlyPojo.class, value, differentValue);
    }

    @AfterAll
    static public void testTearDown() {
        GenericObjectFactory genericObjectFactory = (GenericObjectFactory) objectFactory;
        genericObjectFactory.setCacheInstances(previousCacheInstancesValue);
    }

    @Test
    @DisplayName("Registering values allows testing when unable to create a default value for a field")
    public void registeringValuesAllowsTestingWhenUnableToCreateADefaultValueForAField() throws ObjectFactoryException, InvocationTargetException, IllegalAccessException {
        GettersAndSettersUtils.runGettersAndSettersTestOnField(createValue(), createDifferentValue(), "fieldToIgnore");
    }

    @Test
    @DisplayName("Exception is thrown when unable to create a default value for a field")
    public void exceptionIsThrownWhenUnableToCreateADefaultValueForAField() throws ObjectFactoryException, InvocationTargetException, IllegalAccessException {
        objectFactory.clearValuesForClass(ReadOnlyPojo.class);
        try {
            ObjectFactoryException exception = assertThrows(ObjectFactoryException.class, () -> GettersAndSettersUtils.runGettersAndSettersTestOnField(createValue(), createDifferentValue(), "fieldToIgnore"));
            assertEquals("No values registered for <class com.robertsmieja.test.utils.junit.pojos.ReadOnlyPojo>", exception.getMessage());
        } finally { //if this test fails, we don't pollute others
            objectFactory.registerClassAndValues(ReadOnlyPojo.class, value, differentValue);
        }
    }
}
