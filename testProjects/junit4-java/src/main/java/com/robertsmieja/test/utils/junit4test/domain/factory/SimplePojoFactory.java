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

package com.robertsmieja.test.utils.junit4test.domain.domain.factory;

import com.robertsmieja.test.utils.junit4test.domain.domain.SimplePojo;

/**
 * A set of convenience methods to instantiate instances of {@link SimplePojo}
 * for tests
 */
public class SimplePojoFactory {
    private SimplePojoFactory() {
    }

    public static SimplePojo createValue() {
        SimplePojo pojo = new SimplePojo();
        pojo.setIntegerValue(99);
        pojo.setLongValue(22L);
        pojo.setStringValue("CoolString");
        return pojo;
    }

    public static SimplePojo createDifferentValue() {
        SimplePojo pojo = new SimplePojo();
        pojo.setIntegerValue(22);
        pojo.setLongValue(99L);
        pojo.setStringValue("LessCoolString");
        return pojo;
    }
}
