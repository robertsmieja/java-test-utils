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

package com.robertsmieja.test.utils.junit.interfaces;

import com.robertsmieja.test.utils.junit.exceptions.ObjectFactoryException;

public interface ObjectFactory {
    /**
     * Registers a set of values to use when constructing objects, and instances of aClass are needed
     *
     * @param aClass         The class to register values for
     * @param value          An instance of aClass
     * @param differentValue An instance of aClass with different values for each field
     * @param <T>
     */
    <T> void registerClassAndValues(Class<T> aClass, T value, T differentValue);

    /**
     * Removes any stored instances of aClass
     *
     * @param aClass
     * @param <T>
     */
    <T> void clearValuesForClass(Class<T> aClass);

    /**
     * Attempts to construct an instance of aClass, with each field populated with stored or default values from {@link #getInstanceOfClassWithDifferentValues(Class)}
     *
     * @param aClass
     * @param <T>
     * @return An instance of aClass
     * @throws ObjectFactoryException
     */
    <T> T getInstanceOfClass(Class<T> aClass) throws ObjectFactoryException;

    /**
     * Attempts to construct an instance of aClass, with each field populated with different stored or default values from {@link #getInstanceOfClass(Class)}
     *
     * @param aClass
     * @param <T>
     * @return An instance of aClass
     * @throws ObjectFactoryException
     */
    <T> T getInstanceOfClassWithDifferentValues(Class<T> aClass) throws ObjectFactoryException;
}
