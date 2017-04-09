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
import org.apache.commons.collections4.map.UnmodifiableMap;
import org.apache.commons.lang3.ClassUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.robertsmieja.test.utils.junit.GettersAndSettersUtils.getFields;
import static com.robertsmieja.test.utils.junit.GettersAndSettersUtils.getSetterForField;

public class GenericObjectFactory implements ObjectFactory {
    //Create sensible defaults
    //Delegate to valueOf() to take advantage of caching when possible, except for Strings
    //It doesn't make sense to use valueOf for String
    final static Map<Class<?>, Object> primitivesToValuesMap;
    final static Map<Class<?>, Object> primitivesToDifferentValuesMap;
    protected final static int NUMBER_OF_PRIMITIVE_CLASSES_INCLUDING_ARRAYS = 9 * 2; //double the number because of arrays

    static {
        Map<Class<?>, Object> initPrimitivesToValuesMap = new HashMap<>(NUMBER_OF_PRIMITIVE_CLASSES_INCLUDING_ARRAYS);
        Map<Class<?>, Object> initPrimitivesToDifferentValuesMap = new HashMap<>(NUMBER_OF_PRIMITIVE_CLASSES_INCLUDING_ARRAYS);

        //primitives
        initPrimitivesToValuesMap.put(Boolean.class, Boolean.valueOf(false));
        initPrimitivesToValuesMap.put(Byte.class, Byte.valueOf((byte) 0x11));
        initPrimitivesToValuesMap.put(Character.class, Character.valueOf('b'));
        initPrimitivesToValuesMap.put(Double.class, Double.valueOf(-2.22F));
        initPrimitivesToValuesMap.put(Float.class, Float.valueOf(-1.1F));
        initPrimitivesToValuesMap.put(Integer.class, Integer.valueOf(-100));
        initPrimitivesToValuesMap.put(Long.class, Long.valueOf(-1000L));
        initPrimitivesToValuesMap.put(Short.class, Short.valueOf((short) -10));
        initPrimitivesToValuesMap.put(String.class, "value");

        //arrays
        initPrimitivesToValuesMap.put(Boolean[].class, new Boolean[]{Boolean.valueOf(false)});
        initPrimitivesToValuesMap.put(Byte[].class, new Byte[]{Byte.valueOf((byte) 0x11)});
        initPrimitivesToValuesMap.put(Character[].class, new Character[]{Character.valueOf('b')});
        initPrimitivesToValuesMap.put(Double[].class, new Double[]{Double.valueOf(-2.22F)});
        initPrimitivesToValuesMap.put(Float[].class, new Float[]{Float.valueOf(-1.1F)});
        initPrimitivesToValuesMap.put(Integer[].class, new Integer[]{Integer.valueOf(-100)});
        initPrimitivesToValuesMap.put(Long[].class, new Long[]{Long.valueOf(-1000L)});
        initPrimitivesToValuesMap.put(Short[].class, new Short[]{Short.valueOf((short) -10)});
        initPrimitivesToValuesMap.put(String[].class, new String[]{"value"});

        primitivesToValuesMap = UnmodifiableMap.unmodifiableMap(initPrimitivesToValuesMap);

        //primitives
        initPrimitivesToDifferentValuesMap.put(Boolean.class, Boolean.valueOf(true));
        initPrimitivesToDifferentValuesMap.put(Byte.class, Byte.valueOf((byte) 0xCC));
        initPrimitivesToDifferentValuesMap.put(Character.class, Character.valueOf('d'));
        initPrimitivesToDifferentValuesMap.put(Double.class, Double.valueOf(2.22F));
        initPrimitivesToDifferentValuesMap.put(Float.class, Float.valueOf(1.1F));
        initPrimitivesToDifferentValuesMap.put(Integer.class, Integer.valueOf(100));
        initPrimitivesToDifferentValuesMap.put(Long.class, Long.valueOf(1000L));
        initPrimitivesToDifferentValuesMap.put(Short.class, Short.valueOf((short) 10));
        initPrimitivesToDifferentValuesMap.put(String.class, "differentValue");

        //arrays
        initPrimitivesToDifferentValuesMap.put(Boolean[].class, new Boolean[]{Boolean.valueOf(true)});
        initPrimitivesToDifferentValuesMap.put(Byte[].class, new Byte[]{Byte.valueOf((byte) 0xCC)});
        initPrimitivesToDifferentValuesMap.put(Character[].class, new Character[]{Character.valueOf('d')});
        initPrimitivesToDifferentValuesMap.put(Double[].class, new Double[]{Double.valueOf(2.22F)});
        initPrimitivesToDifferentValuesMap.put(Float[].class, new Float[]{Float.valueOf(1.1F)});
        initPrimitivesToDifferentValuesMap.put(Integer[].class, new Integer[]{Integer.valueOf(100)});
        initPrimitivesToDifferentValuesMap.put(Long[].class, new Long[]{Long.valueOf(1000L)});
        initPrimitivesToDifferentValuesMap.put(Short[].class, new Short[]{Short.valueOf((short) 10)});
        initPrimitivesToDifferentValuesMap.put(String[].class, new String[]{"differentValue"});

        primitivesToDifferentValuesMap = UnmodifiableMap.unmodifiableMap(initPrimitivesToDifferentValuesMap);
    }

    //Keep track of user inputs
    final Map<Class<?>, Object> additionalClassToValuesMap = new ConcurrentHashMap<>();
    final Map<Class<?>, Object> additionalClassToDifferentValuesMap = new ConcurrentHashMap<>();
    protected final boolean cacheInstances;

    public GenericObjectFactory() {
        this(true);
    }

    public GenericObjectFactory(boolean cacheInstances) {
        this.cacheInstances = cacheInstances;
    }

    protected static Class<?> convertPrimitiveToWrapperOrReturn(Class<?> primitiveClass) {
        if (primitiveClass.isPrimitive()) {
            return ClassUtils.primitiveToWrapper(primitiveClass);
        }
        return primitiveClass;
    }

    @Override
    public <T> void registerClassAndValues(Class<T> aClass, T value, T differentValue) {
        additionalClassToValuesMap.put(aClass, value);
        additionalClassToDifferentValuesMap.put(aClass, differentValue);
    }

    @Override
    public <T> T getInstanceOfClass(Class<T> aClass) throws ObjectFactoryException {
        return getInstanceOfClassUsingValueMap(aClass, additionalClassToValuesMap);
    }

    @Override
    public <T> T getInstanceOfClassWithDifferentValues(Class<T> aClass) throws ObjectFactoryException {
        return getInstanceOfClassUsingValueMap(aClass, additionalClassToDifferentValuesMap);
    }

    //Helpers
    protected <T> boolean doesClassExistInCache(Class<T> aClass) {
        if (ClassUtils.isPrimitiveOrWrapper(aClass) || String.class.equals(aClass)) {
            return true;
        }
        return additionalClassToValuesMap.containsKey(aClass) && additionalClassToDifferentValuesMap.containsKey(aClass);
    }

    protected <T> void populateCacheWithInstancesOfClass(Class<T> aClass) throws ObjectFactoryException {
        T leftValue = createObjectForClass(aClass, additionalClassToValuesMap);
        T rightValue = createObjectForClass(aClass, additionalClassToDifferentValuesMap);
        registerClassAndValues(aClass, leftValue, rightValue);
    }

    protected <T> T getInstanceOfClassUsingValueMap(Class<T> aClass, Map<Class<?>, Object> valueMap) throws ObjectFactoryException {
        boolean isInCache = doesClassExistInCache(aClass);
        if (isInCache) {
            return getValueFromMapOrDefaultMap(aClass, valueMap);
        }
        if (cacheInstances) {
            populateCacheWithInstancesOfClass(aClass);
            return getValueFromMapOrDefaultMap(aClass, valueMap);
        }
        return createObjectForClass(aClass, valueMap);
    }

    protected <T> T createObjectForClass(Class<T> aClass, Map<Class<?>, Object> valueMap) throws ObjectFactoryException {
        T object;
        try {
            object = Internal.createObjectFromDefaultConstructor(aClass);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            throw new ObjectFactoryException("Unable to create an instance of <" + aClass + ">. Is there a non-arg constructor?", e);
        }
        List<Field> fields = getFields(aClass);

        for (Field field : fields) {
            setValueForField(field, object, valueMap);
        }
        return object;
    }

    protected <T> void setValueForField(Field field, T object, Map<Class<?>, Object> valueMap) throws ObjectFactoryException {
        Method setter = getSetterForField(field);
        T valueToSet = getValueFromMapOrDefaultMap((Class<T>) field.getType(), valueMap);
        if (valueToSet == null) {
            throw new ObjectFactoryException("No values registered for <" + field.getType() + ">");
        }

        try {
            setter.invoke(object, valueToSet);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            throw new ObjectFactoryException("Unable to call setter for <" + field + "> on <" + object.getClass() + ">", e);
        }
    }

    protected <T> T getValueFromMapOrDefaultMap(Class<T> fieldClass, Map<Class<?>, Object> valueMap) {
        Class<?> nonPrimitiveClass = convertPrimitiveToWrapperOrReturn(fieldClass);
        Map<Class<?>, Object> defaultValueMap = getCorrectDefaultValueMapFromClassMap(valueMap);
        Object value = valueMap.getOrDefault(nonPrimitiveClass, defaultValueMap.get(nonPrimitiveClass));
        T castedValue = (T) value;
        return castedValue;
    }

    protected Map<Class<?>, Object> getCorrectDefaultValueMapFromClassMap(Map<Class<?>, Object> classMap) {
        if (classMap == additionalClassToValuesMap) { //Avoid equals(), since that will be true with an empty map
            return primitivesToValuesMap;
        }
        return primitivesToDifferentValuesMap;
    }
}
