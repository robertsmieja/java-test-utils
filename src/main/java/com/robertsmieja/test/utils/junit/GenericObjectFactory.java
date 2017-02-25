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
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.robertsmieja.test.utils.junit.GettersAndSettersUtils.getFields;
import static com.robertsmieja.test.utils.junit.GettersAndSettersUtils.getSetterForField;

public class GenericObjectFactory {

    GenericObjectFactory() {
    }

    //Create sensible defaults
    //Delegate to valueOf() to take advantage of caching when possible, except for Strings
    //It doesn't make sense to use valueOf for String
    final static Map<Class<?>, Pair<?, ?>> classToValuesMap = new ConcurrentHashMap<>();

    static {
        //primitives
        classToValuesMap.put(Boolean.class, new ImmutablePair<>(Boolean.valueOf(false), Boolean.valueOf(true)));
        classToValuesMap.put(Byte.class, new ImmutablePair<>(Byte.valueOf((byte) 0x11), Byte.valueOf((byte) 0xCC)));
        classToValuesMap.put(Character.class, new ImmutablePair<>(Character.valueOf('b'), Character.valueOf('d')));
        classToValuesMap.put(Double.class, new ImmutablePair<>(Double.valueOf(-2.22F), Double.valueOf(2.22F)));
        classToValuesMap.put(Float.class, new ImmutablePair<>(Float.valueOf(-1.1F), Float.valueOf(1.1F)));
        classToValuesMap.put(Integer.class, new ImmutablePair<>(Integer.valueOf(-100), Integer.valueOf(100)));
        classToValuesMap.put(Long.class, new ImmutablePair<>(Long.valueOf(-1000L), Long.valueOf(1000L)));
        classToValuesMap.put(Short.class, new ImmutablePair<>(Short.valueOf((short) -10), Short.valueOf((short) 10)));
        classToValuesMap.put(String.class, new ImmutablePair<>("value", "differentValue"));

        //arrays
        classToValuesMap.put(Boolean[].class, new ImmutablePair<>(new Boolean[]{Boolean.valueOf(false)}, new Boolean[]{Boolean.valueOf(true)}));
        classToValuesMap.put(Byte[].class, new ImmutablePair<>(new Byte[]{Byte.valueOf((byte) 0x11)}, new Byte[]{Byte.valueOf((byte) 0xCC)}));
        classToValuesMap.put(Character[].class, new ImmutablePair<>(new Character[]{Character.valueOf('b')}, new Character[]{Character.valueOf('d')}));
        classToValuesMap.put(Double[].class, new ImmutablePair<>(new Double[]{Double.valueOf(-2.22F)}, new Double[]{Double.valueOf(2.22F)}));
        classToValuesMap.put(Float[].class, new ImmutablePair<>(new Float[]{Float.valueOf(-1.1F)}, new Float[]{Float.valueOf(1.1F)}));
        classToValuesMap.put(Integer[].class, new ImmutablePair<>(new Integer[]{Integer.valueOf(-100)}, new Integer[]{Integer.valueOf(100)}));
        classToValuesMap.put(Long[].class, new ImmutablePair<>(new Long[]{Long.valueOf(-1000L)}, new Long[]{Long.valueOf(1000L)}));
        classToValuesMap.put(Short[].class, new ImmutablePair<>(new Short[]{Short.valueOf((short) -10)}, new Short[]{Short.valueOf((short) 10)}));
        classToValuesMap.put(String[].class, new ImmutablePair<>(new String[]{"value"}, new String[]{"differentValue"}));
    }

    //Keep track of user inputs
    final static Map<Class<?>, Pair<?, ?>> additionalClassToValuesMap = new ConcurrentHashMap<>();

    public static <T> void registerClassAndValues(Class<T> aClass, T value, T differentValue) {
        additionalClassToValuesMap.put(aClass, new ImmutablePair<>(value, differentValue));
    }

    public static <T> T createObjectForClass(Class<T> aClass) throws ObjectFactoryException {
        T object;
        try {
            object = Internal.defaultCreateValueImplementation(aClass);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            throw new ObjectFactoryException("Unable to create an instance of <" + aClass + ">, is there a non-arg constructor?", e);
        }
        List<Field> fields = getFields(aClass);

        for (Field field : fields) {
            Method setter = getSetterForField(field);
            Pair valuesForType = getPairForClass(convertPrimitivesToWrapperType(field.getType()));
            if (valuesForType == null) {
                throw new ObjectFactoryException("No values registered for <" + field.getType() + ">");
            }

            Object valueToSet = valuesForType.getLeft();
            try {
                setter.invoke(object, valueToSet);
            } catch (InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
                throw new ObjectFactoryException("Unable to call setter for <" + field + "> on <" + aClass + ">", e);
            }
        }

        return object;
    }

    public static <T> T createDifferentObjectForClass(Class<T> aClass) throws ObjectFactoryException {
        return null;
    }

    public static <T> T getValueForClass(Class<T> aClass) {
        T value = (T) getPairForClass(aClass).getLeft();
        return value;
    }

    public static <T> T getDifferentValueForClass(Class<T> aClass) {
        T value = (T) getPairForClass(aClass).getRight();
        return value;
    }

    private static <T> Pair getPairForClass(Class<T> aClass) {
        //TODO Default value will always be evaluated before checking additionalClassToValuesMap, will this cause a performance issue?
        return additionalClassToValuesMap.getOrDefault(aClass, classToValuesMap.get(aClass));
    }

    private static Class convertPrimitivesToWrapperType(Class primitiveClass){
        if (primitiveClass.isPrimitive()){
            return ClassUtils.primitiveToWrapper(primitiveClass);
        }
        return primitiveClass;
    }
}
