package com.robertsmieja.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ReflectionUtils {
    private ReflectionUtils(){}

    public static<T> T createInstance(Class<T> clazz) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        return createInstance(clazz, null);
    }

    public static<T> T createInstance(Class<T> clazz, Object... arguments) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Constructor<T> constructor = clazz.getConstructor(getClassesFromArray(arguments));
        return constructor.newInstance(arguments);
    }

    public static List<Field> getAllFields(Class clazz){
        return getAllFields(clazz, true);
    }

    public static List<Field> getAllFields(Class clazz, boolean includePrivateFields){
        if (includePrivateFields) {
            return Arrays.asList(clazz.getDeclaredFields());
        }
        return Arrays.asList(clazz.getFields());
    }

    public static List<Method> getAllMethods(Class clazz){
        return getAllMethods(clazz, true);
    }

    public static List<Method> getAllMethods(Class clazz, boolean includePrivateMethods){
        if (includePrivateMethods){
            return Arrays.asList(clazz.getDeclaredMethods());
        }
        return Arrays.asList(clazz.getMethods());
    }

    public static Object getFieldValue(Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        return getFieldValue(object, getField(object.getClass(), fieldName));
    }

    public static Object getFieldValue(Object object, Field field) throws IllegalAccessException {
        boolean oldAccessValue = field.isAccessible();
        field.setAccessible(true);
        Object value = field.get(object);
        field.setAccessible(oldAccessValue);
        return value;
    }

    public static void setFieldValue(Object object, Field field, Object newValueOfField) throws IllegalAccessException {
        boolean oldAccessValue = field.isAccessible();
        field.setAccessible(true);
        field.set(object, newValueOfField);
        field.setAccessible(oldAccessValue);
    }

    public static void setFieldValue(Object object, String fieldName, Object newValueOfField) throws NoSuchFieldException, IllegalAccessException {
        setFieldValue(object, getField(object.getClass(), fieldName), newValueOfField);
    }

    public static Field getField(Class clazz, String fieldName) throws NoSuchFieldException {
        return clazz.getField(fieldName);
    }

    public static Method getMethod(Class clazz, String methodName) throws NoSuchMethodException {
        return getMethod(clazz, methodName, null);
    }

    public static Method getMethod(Class clazz, String methodName, Class... parameterTypes) throws NoSuchMethodException {
            return clazz.getMethod(methodName, parameterTypes);
    }

    public static Class[] getClassesFromArray(Object... objects){
        return (Class[]) Arrays.stream(objects).map(o -> (o.getClass())).toArray();
    }
}
