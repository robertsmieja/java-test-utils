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

import com.robertsmieja.test.utils.junit.factory.ConstructorThatThrowsObject;
import com.robertsmieja.test.utils.junit.factory.NoDefaultConstructorObject;
import com.robertsmieja.test.utils.junit.factory.PrivateConstructorObject;
import com.robertsmieja.test.utils.junit.pojos.ComplexPojo;
import com.robertsmieja.test.utils.junit.pojos.ReadOnlyPojo;
import com.robertsmieja.test.utils.junit.pojos.SimplePojo;
import com.robertsmieja.test.utils.junit.exceptions.ObjectFactoryException;
import org.junit.jupiter.api.*;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

public class GenericObjectFactoryTests {

    GenericObjectFactory objectUnderTest;

    @BeforeEach
    public void setup() {
        objectUnderTest = new GenericObjectFactory();
    }

    @Test
    @DisplayName("Test constructor")
    public void testConstructor() {
        assertNotNull(objectUnderTest);
    }

    @Test
    @DisplayName("Instantiate Object successfully")
    public void instantiateObjectSuccessfully() throws ObjectFactoryException {
        testInstantiation(Object.class);
    }

    @Test
    @DisplayName("Object is cached successfully")
    public void objectIsCachedSuccessfully() throws ObjectFactoryException {
        Object object = objectUnderTest.getInstanceOfClass(Object.class);
        Object differentObject = objectUnderTest.getInstanceOfClassWithDifferentValues(Object.class);

        assertNotNull(object);
        assertNotNull(differentObject);
        assertNotEquals(object, differentObject);

        Object sameObject = objectUnderTest.getInstanceOfClass(Object.class);
        Object sameObjectWithDifferentValues = objectUnderTest.getInstanceOfClassWithDifferentValues(Object.class);

        assertNotNull(sameObject);
        assertNotNull(sameObjectWithDifferentValues);
        assertSame(object, sameObject);
        assertSame(differentObject, sameObjectWithDifferentValues);
    }

    @Test
    @DisplayName("Object is not cached when caching is turned off")
    public void objectIsNotCachedWhenCachingIsTurnedOff() throws ObjectFactoryException {
       objectUnderTest = new GenericObjectFactory(false);

        Object object = objectUnderTest.getInstanceOfClass(Object.class);
        Object differentObject = objectUnderTest.getInstanceOfClassWithDifferentValues(Object.class);

        assertNotNull(object);
        assertNotNull(differentObject);
        assertNotEquals(object, differentObject);

        Object sameObject = objectUnderTest.getInstanceOfClass(Object.class);
        Object sameObjectWithDifferentValues = objectUnderTest.getInstanceOfClassWithDifferentValues(Object.class);

        assertNotNull(sameObject);
        assertNotNull(sameObjectWithDifferentValues);
        assertNotSame(object, sameObject);
        assertNotSame(differentObject, sameObjectWithDifferentValues);
    }

    @Test
    @DisplayName("Can create primitive types successfully")
    public void canCreatePrimitiveTypesSuccessfully() throws ObjectFactoryException {
        String stringValue = objectUnderTest.getInstanceOfClass(String.class);
        int intValue = objectUnderTest.getInstanceOfClass(int.class);

        assertNotNull(stringValue);
        assertNotNull(intValue);
        assertEquals("value", stringValue);
        assertEquals(-100, intValue);
    }

    @Test
    @DisplayName("Instantiate SimplePojo successfully")
    public void instantiateSimplePojoSuccessfully() throws ObjectFactoryException {
        testInstantiation(SimplePojo.class);
    }

    @Test
    @DisplayName("Instantiate ComplexPojo successfully")
    public void instantiateComplexPojoSuccessfully() throws ObjectFactoryException {
        testInstantiation(ComplexPojo.class);
    }

    @Test
    @DisplayName("Unable to instantiate a Class that doesn't have a no-arg constructor")
    public void unableToInstantiateAClassThatDoesntHaveANoArgConstructor(){
        ObjectFactoryException exception = Assertions.assertThrows(ObjectFactoryException.class, () -> testInstantiation(NoDefaultConstructorObject.class));
        assertEquals("Unable to find a public no-arg constructor for <class com.robertsmieja.test.utils.junit.factory.NoDefaultConstructorObject>", exception.getMessage());
    }

    @Test
    @DisplayName("Unable to instantiate a Class that has a private constructor")
    public void unableToInstantiateAClassThatHasAPrivateConstructor(){
        ObjectFactoryException exception = Assertions.assertThrows(ObjectFactoryException.class, () -> testInstantiation(PrivateConstructorObject.class));
        assertEquals("Unable to find a public no-arg constructor for <class com.robertsmieja.test.utils.junit.factory.PrivateConstructorObject>", exception.getMessage());
    }

    @Test
    @DisplayName("Correctly handles an Exception during Constructor.newInstance()")
    public void correctlyHandlesAnExceptionDuringConstructorNewInstance(){
        ObjectFactoryException exception = Assertions.assertThrows(ObjectFactoryException.class, () -> testInstantiation(ConstructorThatThrowsObject.class));
        assertEquals("Exception encountered while trying to create an instance of <class com.robertsmieja.test.utils.junit.factory.ConstructorThatThrowsObject>", exception.getMessage());

        Throwable cause = exception.getCause();
        assertEquals(InvocationTargetException.class, cause.getClass());

        Throwable actualCause = cause.getCause();
        assertEquals("Don't call this constructor!", actualCause.getMessage());
        assertSame(ConstructorThatThrowsObject.constructorException, actualCause);
    }

    private <T> void testInstantiation(Class<T> tClass) throws ObjectFactoryException {
        T object = objectUnderTest.getInstanceOfClass(tClass);
        T objectWithDifferentValues = objectUnderTest.getInstanceOfClassWithDifferentValues(tClass);

        assertNotNull(object);
        assertNotNull(objectWithDifferentValues);
        assertNotEquals(object, objectWithDifferentValues);
    }
}
