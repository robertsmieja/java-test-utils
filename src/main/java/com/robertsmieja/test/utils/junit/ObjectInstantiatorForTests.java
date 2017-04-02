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
import com.robertsmieja.test.utils.junit.interfaces.ObjectFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * A base interface that defines the following methods:
 * <ul>
 * <li> getClassOfGenericTypeArgument() which returns the class under test's Class </li>
 * <li> createValue() which returns an instance of the class under test </li>
 * <li> createDifferentValue() which returns an instance of the class under test, with different values in each field </li>
 * </ul>
 * By default it will attempt to use a no-arg constructor to create for the createValue() and createDifferentValue() methods
 *
 * @param <T> The class under test
 *
 * @since 0.1.0
 */
public interface ObjectInstantiatorForTests<T> {
    ObjectFactory objectFactory = new GenericObjectFactory();

    default Class<T> getClassOfGenericTypeArgument() {
        //TODO Clean this up to be more readable? is that possible?
        //TODO make sure this works with complicated interface/class hierarchies
        Class<? extends ObjectInstantiatorForTests> ourCurrentClass = this.getClass();
        Type[] ourCurrentInterfaces = ourCurrentClass.getGenericInterfaces();
        ParameterizedType currentInterface = (ParameterizedType) ourCurrentInterfaces[0];
        Type genericTypeArgument = currentInterface.getActualTypeArguments()[0];
        //noinspection unchecked
        @SuppressWarnings("unchecked")
        Class<T> classType = (Class<T>) genericTypeArgument;
        return classType;
    }

    default T createValue() throws ObjectFactoryException {
        return objectFactory.getInstanceOfClass(getClassOfGenericTypeArgument());
    }

    default T createDifferentValue() throws ObjectFactoryException {
        return objectFactory.getInstanceOfClassWithDifferentValues(getClassOfGenericTypeArgument());
    }

    @Test
    @DisplayName("Can create values successfully")
    default void canCreateValuesSuccessfully() throws ObjectFactoryException {
        T value = createValue();
        T differentValue = createDifferentValue();

        Assertions.assertNotNull(value);
        Assertions.assertNotNull(differentValue);
    }
}
