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

package com.robertsmieja.test.utils.junit.pojos;

public class PojoWithInnerClass {
    public static Class<InnerPojo> getInnerClass() {
        return InnerPojo.class;
    }

    public InnerPojo createInnerValue() {
        InnerPojo value = new InnerPojo();
        value.setStringValue("value");
        return value;
    }

    public InnerPojo createInnerDifferentValue() {
        InnerPojo value = new InnerPojo();
        value.setStringValue("differentValue");
        return value;
    }

    //Non-static inner class creates a synthetic filed
    class InnerPojo {
        String stringValue;

        public String getStringValue() {
            return stringValue;
        }

        public void setStringValue(String stringValue) {
            this.stringValue = stringValue;
        }
    }
}
