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

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GenericObjectFactory {

    final static Map<Class<?>, Pair<?,?>> classToValuesMap = new ConcurrentHashMap<>();
    {{
        classToValuesMap.put(Boolean.class, new ImmutablePair<>(false, true));
        classToValuesMap.put(Byte.class, new ImmutablePair<>(0x11, 0xCC));
        classToValuesMap.put(Character.class, new ImmutablePair<>('b', 'd'));
        classToValuesMap.put(Double.class, new ImmutablePair<>(-2.22F, 2.22F));
        classToValuesMap.put(Float.class, new ImmutablePair<>(-1.1F, 1.1F ));
        classToValuesMap.put(Integer.class, new ImmutablePair<>(-100, 100));
        classToValuesMap.put(Long.class, new ImmutablePair<>(-1000L, 1000L));
        classToValuesMap.put(Short.class, new ImmutablePair<>(-10, 10));
        classToValuesMap.put(String.class, new ImmutablePair<>("value", "differentValue"));
    }}



}
